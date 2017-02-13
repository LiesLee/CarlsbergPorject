package com.common.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.common.base.ui.BaseView;
import com.common.callback.RequestCallback;
import com.common.utils.NetUtil;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.net.SocketTimeoutException;
import java.util.LinkedList;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by LiesLee on 2016/7/26.
 * Email: LiesLee@foxmail.com
 */
public class HttpSubscibe {
    /**
     * 订阅网络请求
     * @param context
     * @param subscribeOnScheduler //执行beforeRequest所在的线程
     * @param observable    请求体（被订阅的事件）
     * @param requestCallback
     * @param <T>
     * @return  订阅者
     */
    public static <T> Subscription subscibe (final Context context, Scheduler subscribeOnScheduler, Observable<HttpResult<T>> observable, CompositeSubscription cancelUnsubscribes, final BaseView baseView, final RequestCallback<T> requestCallback){
        if(NetUtil.isConnected(context)){
            Subscription subscription = observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .doOnSubscribe(new Action0() {
                        @Override
                        public void call() {
                            if(baseView == null) return;
                            if(requestCallback == null) return;
                            requestCallback.beforeRequest();
                        }
                    }).subscribeOn(subscribeOnScheduler)
                    //.map(new HttpResultFunc<T>(requestCallback))
                    .subscribe(new Subscriber<HttpResult<T>>() {
                        @Override
                        public void onCompleted() {
                            if(baseView == null) return;
                            if(requestCallback == null) return;
                            requestCallback.requestComplete();
                        }

                        @Override
                        public void onError(Throwable e) {
                            if(baseView == null) return;
                            if(requestCallback == null) return;
                            if (e instanceof HttpException){             //HTTP错误
                                HttpException httpException = (HttpException) e;
                                KLog.e(e.getLocalizedMessage() + "\n" + e);
                                requestCallback.requestError(httpException.code() , e.getLocalizedMessage() + "\n" + e);
                            }else if(e instanceof SocketTimeoutException) {
                                KLog.e("请求超时：\n" + e);
                                requestCallback.requestError(0 , "似乎网络不太给力哦~");
                            }else{
                                KLog.e(e.getLocalizedMessage() + "\n" + e);
                                requestCallback.requestError(0 , "似乎链接不上哦，请稍后再试~");
                            }
                        }

                        @Override
                        public void onNext(HttpResult<T> t) {
                            if(baseView == null) return;
                            if(requestCallback == null) return;
                            try {
                                //这里不做空判断,因为部分检查接口请求成功返回200,但数据依然是空的
                                if (t != null) {
                                    if (t.getStatus() == 200) {
                                        //数据返回
                                        requestCallback.requestSuccess(t.getData());
                                    } else if(t.getStatus() == 207 || t.getStatus() == 202 || t.getStatus() == 208){
                                        requestCallback.requestError(0, null);
                                    }else{
                                        //请求错误回调
                                        requestCallback.requestError(t.getStatus(), t.getMsg());
                                    }
                                } else {
                                    requestCallback.requestError(0, "数据获取失败");
                                }
                            } catch (Exception e) {
                                Log.e("requestCallback", "数据回调后处理错误: " + e.toString());
                                e.printStackTrace();
                                MobclickAgent.reportError(context, e);
                                requestCallback.requestError(0, "数据回调后处理错误");
                            }
                        }
                    });
            //后台请求放到SubscriptionManager,关闭dialog需要取消的放到cancelUnsubscribes
            if(subscription!=null){
                if (cancelUnsubscribes != null) {
                    cancelUnsubscribes.add(subscription);
                }else{
                    SubscriptionManager.getInstance().add(baseView, subscription);
                }
            }

            return subscription;
        }else{
            requestCallback.requestError(0, "网络连接异常, 请检查网络状态");
        }
        return null;
    }
}
