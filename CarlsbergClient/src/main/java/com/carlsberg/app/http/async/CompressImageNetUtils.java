package com.carlsberg.app.http.async;

import android.text.TextUtils;

import com.carlsberg.app.http.HttpConstants;
import com.carlsberg.app.http.protocol.BaseProtocol;
import com.common.base.ui.BaseActivity;
import com.common.utils.image_compress.PicCompress;
import com.loopj.android.http.RequestParams;
import com.socks.library.KLog;
import com.views.util.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片压缩上传
 * Created by LiesLee on 2016/7/7 0007.
 */
public class CompressImageNetUtils {

    public interface HandlerPicArrayCompressBack {
        public void callBack(boolean isSuccess, List<String> imagesArray);
    }

    public interface HandlerPicSingleCompressBack {
        public void callBack(boolean isSuccess, String imagePath);
    }

    /**
     * @param baseAct
     * @param arrayImage
     * @param type  上传类型:
     *                  avatar:个人头像上传1张,
     *                  evaluate:店铺评论相片上传可多张,
     *                  clubshare:分享动态图片上传可多张,
     * @param callBack   压缩多张图片 返回上传图片返回的List<String>
     */
    public static void getCompressImge(final BaseActivity baseAct, ArrayList<String> arrayImage, final String type, final HandlerPicSingleCompressBack callBack) {

        PicCompress.CompressImage(arrayImage, baseAct, new PicCompress.HandlePicArrayCompressCallBack() {
            @Override
            public void callBack(boolean isSuccess, List<String> picpaths) {
                if (isSuccess) {
                    ManagerHttpClient httpClient = ManagerHttpClient.getsInstance(baseAct);

                    Map<String, String> map = new HashMap<>();
                    map.put("uptype", type);

                    RequestParams params = new RequestParams(BaseProtocol.createPatams1(map));

                    //File[] images = new File[picpaths.size()];
                    try{
                        for (int i = 0; i < picpaths.size(); i++) {
                            //images[i] = new File(picpaths.get(i));
                            params.put("upfile["+i+"]", new File(picpaths.get(i)));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
//                    try {
//                        params.put("upfile", images);
//                    } catch (FileNotFoundException e1) {
//                        e1.printStackTrace();
//                    }

                    httpClient.post("/image/upload", params, new ManagerResponseHandler<String>(baseAct) {
                        @Override
                        public void onSuccess(final int code, final String msg, final String data) {
                            baseAct.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(code == HttpConstants.REQUEST_SUCESS){
                                        if (!TextUtils.isEmpty(data)) {
                                            callBack.callBack(true, data);
                                        } else {
                                            callBack.callBack(false, "");
                                            ToastUtil.showShortToast(baseAct, "上传返回，请稍候再试");
                                        }
                                    }else{
                                        callBack.callBack(false, "");
                                        ToastUtil.showShortToast(baseAct, msg);
                                        KLog.e("msg:"+msg, ", code: " + code);
                                    }

                                }
                            });
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            callBack.callBack(false, null);
                            ToastUtil.showShortToast(baseAct, "网络异常，请稍候再试");
                        }
                    });
                } else {
                    callBack.callBack(false, "");
                    KLog.e("压缩失败");
                }
            }
        });
    }

//    public static void getCompressImge(final BaseActivity baseAct, String picPath, final HandlerPicSingleCompressBack callBack) {
//
//        PicCompress.CompressImage(picPath, baseAct, new PicCompress.HandlePicCompressCallBack() {
//            @Override
//            public void callBack(boolean isSuccess, String picpath) {
//                if (isSuccess) {
//                    ManagerHttpClient httpClient = ManagerHttpClient.getsInstance(baseAct);
//                    RequestParams params = new RequestParams();
//                    try {
//                        params.put("fileName", new File(picpath));
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    params.put("type", "head");
//                    httpClient.post("/rrsh-web-user/upload/uploadUser/v2", params, new ManagerResponseHandler<String>(baseAct, String.class) {
//                        @Override
//                        public void onSuccess(int code, String msg, String data) {
//                            if (code == 200) {
//                                JSONObject object = JSON.parseObject(data);
//                                String mPicUrl = object.getJSONArray("url").getString(0);
//                                callBack.callBack(true, mPicUrl);
//                            } else {
//                                callBack.callBack(false, "");
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Throwable throwable) {
//                            callBack.callBack(false, "");
//                        }
//                    });
//                }
//            }
//        });
//    }
}





