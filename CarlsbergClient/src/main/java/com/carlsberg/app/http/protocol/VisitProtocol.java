package com.carlsberg.app.http.protocol;

import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.bean.common.User;
import com.carlsberg.app.bean.visit.CollectViewResponse;
import com.carlsberg.app.bean.visit.TaskCollect;
import com.carlsberg.app.bean.visit.TaskScore;
import com.carlsberg.app.bean.visit.VisitRespone;
import com.carlsberg.app.bean.visit.VisitStoreResponse;
import com.carlsberg.app.http.manager.RetrofitManager;
import com.common.http.HostType;
import com.common.http.HttpResult;
import com.common.utils.MD5Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LiesLee on 17/2/28.
 */

public class VisitProtocol extends BaseProtocol {
    /**
     * 拜访的列表
     * @param search_text
     * @param status_type
     * @param page
     * @return
     */
    public static Observable<HttpResult<VisitRespone>> storePlan(int showType, String search_text, int status_type, int page){
        Map<String, Object> params = new HashMap<>();
        params.put("search_text", search_text);
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        params.put("status_type", status_type);
        params.put("page", page);
        return RetrofitManager.getInstance(HostType.USER_HOST).getVisitService()
                .storePlan(createPatams(params, showType == 0 ? "recentPlan" : "storePlan"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }
    /**
     * 拜访门店
     * @param store_id
     * @return
     */
    public static Observable<HttpResult<VisitStoreResponse>> storeView(String store_id, String task_id){
        Map<String, Object> params = new HashMap<>();
        params.put("store_id", store_id);
        params.put("task_id", task_id);
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        return RetrofitManager.getInstance(HostType.USER_HOST).getVisitService()
                .storeView(createPatams(params, "storeView"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }
    /**
     * @param store_id
     * @return
     */
    public static Observable<HttpResult<CollectViewResponse>> collectView(String store_id, String task_id){
        Map<String, Object> params = new HashMap<>();
        params.put("store_id", store_id);
        params.put("task_id", task_id);
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        return RetrofitManager.getInstance(HostType.USER_HOST).getVisitService()
                .collectView(createPatams(params, "collectView"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }
    /**
     * 获取图片
     * @param store_id
     * @return
     */
    public static Observable<HttpResult<VisitStoreResponse>> getPhoto(String store_id, String task_id){
        Map<String, Object> params = new HashMap<>();
        params.put("store_id", store_id);
        params.put("task_id", task_id);
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        return RetrofitManager.getInstance(HostType.USER_HOST).getVisitService()
                .storeView(createPatams(params, "getPhoto"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }
    /**
     * 打卡
     * @param store_id
     * @param task_id
     * @param sigin_type checkin ：到店签到  goaway ：离店签到
     * @return
     */
    public static Observable<HttpResult<String>> taskSign(String store_id, String task_id, String sigin_type){
        Map<String, Object> params = new HashMap<>();
        params.put("store_id", store_id);
        params.put("task_id", task_id);
        params.put("sigin_type", sigin_type);
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        return RetrofitManager.getInstance(HostType.USER_HOST).getVisitService()
                .taskSign(createPatams(params, "taskSign"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }

    public static Observable<HttpResult<String>> delPhoto(String store_id, String task_id, String image_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("store_id", store_id);
        params.put("task_id", task_id);
        params.put("image_id", image_id);
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        return RetrofitManager.getInstance(HostType.USER_HOST).getVisitService()
                .taskSign(createPatams(params, "delPhoto"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }

    public static Observable<HttpResult<String>> collectSave(String store_id, String task_id, List<TaskCollect> taskCollects, List<TaskCollect> taskScores) {
        Map<String, Object> params = new HashMap<>();
        params.put("store_id", store_id);
        params.put("task_id", task_id);
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());

        if(taskCollects!=null){
            for(TaskCollect taskCollect : taskCollects){
                params.put(taskCollect.getId_name(), taskCollect.getVal() == null ? "" : taskCollect.getVal());
            }
        }

        if(taskScores != null){
            for (TaskCollect taskScore : taskScores){
                params.put(taskScore.getId_name(), taskScore.getVal() == null ? "" : taskScore.getVal());
            }
        }

        return RetrofitManager.getInstance(HostType.USER_HOST).getVisitService()
                .taskSign(createPatams(params, "collectSave"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }
}
