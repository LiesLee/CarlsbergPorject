package com.carlsberg.app.module.visit.ui.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.visit.VisitRespone;
import com.carlsberg.app.common.Constant;
import com.carlsberg.app.module.home.ui.adapter.LatelyVisitedAdapter;
import com.carlsberg.app.module.visit.persenter.TodayVisitPresenter;
import com.carlsberg.app.module.visit.view.TodayVisitView;
import com.carlsberg.app.utils.DialogHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseFragment;
import com.common.base.ui.BaseView;
import com.views.util.RefreshUtil;

import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;


/**
 * Created by LiesLee on 17/2/17.
 */
@ActivityFragmentInject(contentViewId = R.layout.fra_visit_child)
public class VisitChildFragment extends BaseFragment<TodayVisitPresenter> implements TodayVisitView {
    @Bind(R.id.pcfl_pull_to_refresh)
    PtrClassicFrameLayout pcfl_pull_to_refresh;
    @Bind(R.id.rv_list)
    RecyclerView rv_list;
    private Handler handler;
    private LinearLayoutManager mLinearLayoutManager;
    private LatelyVisitedAdapter mAdapter;
    private String name;
    /** 0最近拜访、 1今日拜访 */
    private int showType = 0;
    private String search_text;
    private int status_type = 0;
    private String status_name;
    int pageNumber = 1;
    List<VisitRespone.StatusType> typeList;

    @Override
    protected void initView(View fragmentRootView) {
        mPresenter = new TodayVisitPresenter(this);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });
        //initialize Refresh layout
        RefreshUtil.init_material_pull(baseActivity, pcfl_pull_to_refresh, new RefreshUtil.PtrRefreshListener() {
            @Override
            public void OnRefresh(final PtrFrameLayout frame) {
                pageNumber = 1;
                mPresenter.storePlan(showType, TextUtils.isEmpty(search_text) ? "" : search_text, status_type, pageNumber);
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(baseActivity);
        rv_list.setLayoutManager(mLinearLayoutManager);

        mAdapter = new LatelyVisitedAdapter(baseActivity, null);
        rv_list.setAdapter(mAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter != null && pcfl_pull_to_refresh != null){
            RefreshUtil.autoRefresh(pcfl_pull_to_refresh);
        }
    }


    @Override
    public void hideProgress(int type) {
        super.hideProgress(type);
        if (type == Constant.PROGRESS_TYPE_LIST) {
            pcfl_pull_to_refresh.refreshComplete();
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    @Override
    public void loadListSeccessed(VisitRespone data) {
        if(pageNumber == 1){
            mAdapter.setData(data.getLists());
            if(data.getLists() != null && data.getLists().size() > 5){
                mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        if (mAdapter.getData() != null && mAdapter.getData().size() > 0) {
                            ++pageNumber;
                            mPresenter.storePlan(showType, TextUtils.isEmpty(search_text) ? "" : search_text, status_type, pageNumber);
                        } else {
                            //mAdapter.loadComplete();
                        }

                    }
                });
            }

            if(data.getStatus_type()!=null){
                typeList = data.getStatus_type();
            }
        }else{
            if (data.getLists() == null || data.getLists().size() == 0) {
                --pageNumber;
                if (pageNumber < 1) {
                    pageNumber = 1;
                }
            }
            mAdapter.addNewData(data.getLists());
        }
    }

    public void load(){
        if(pcfl_pull_to_refresh.isRefreshing() || mAdapter.isLoading()){
            DialogHelper.showTipsDialog(baseActivity, "数据正在加载中，请稍后~", "稍后再试", null);
        }else{
            pageNumber = 1;
            mPresenter.storePlan(showType, search_text, status_type, pageNumber);
        }

    }

    public List<VisitRespone.StatusType> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<VisitRespone.StatusType> typeList) {
        this.typeList = typeList;
    }

    public String getSearch_text() {
        return search_text;
    }

    public void setSearch_text(String search_text) {
        this.search_text = search_text;
    }

    public int getStatus_type() {
        return status_type;
    }

    public void setStatus_type(int status_type) {
        this.status_type = status_type;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
