package com.carlsberg.app.module.visit.ui.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.carlsberg.app.R;
import com.carlsberg.app.module.home.ui.adapter.LatelyVisitedAdapter;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseFragment;
import com.common.base.ui.BaseView;
import com.views.util.RefreshUtil;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;


/**
 * Created by LiesLee on 17/2/17.
 */
@ActivityFragmentInject(contentViewId = R.layout.fra_visit_child)
public class VisitChildFragment extends BaseFragment<BasePresenterImpl> implements BaseView {
    @Bind(R.id.pcfl_pull_to_refresh)
    PtrClassicFrameLayout pcfl_pull_to_refresh;
    @Bind(R.id.rv_list)
    RecyclerView rv_list;
    private Handler handler;
    private LinearLayoutManager mLinearLayoutManager;
    private LatelyVisitedAdapter mAdapter;
    private String name;

    @Override
    protected void initView(View fragmentRootView) {
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
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pcfl_pull_to_refresh.refreshComplete();
                    }
                }, 3000);
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(baseActivity);
        rv_list.setLayoutManager(mLinearLayoutManager);

        mAdapter = new LatelyVisitedAdapter(baseActivity, null);
        rv_list.setAdapter(mAdapter);

        RefreshUtil.autoRefresh(pcfl_pull_to_refresh);
    }

    @Override
    public void initData() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
