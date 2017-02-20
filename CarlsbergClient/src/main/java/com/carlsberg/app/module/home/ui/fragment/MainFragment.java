package com.carlsberg.app.module.home.ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.carlsberg.app.R;
import com.carlsberg.app.module.home.ui.adapter.LatelyVisitedAdapter;
import com.carlsberg.app.module.visit.ui.activity.StoreVisitActivity;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseFragment;
import com.common.base.ui.BaseView;
import com.views.util.RefreshUtil;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by LiesLee on 17/2/14.
 */
@ActivityFragmentInject(contentViewId = R.layout.fra_main)
public class MainFragment extends BaseFragment<BasePresenterImpl> implements BaseView {

    @Bind(R.id.pcfl_pull_to_refresh)
    PtrClassicFrameLayout pcfl_pull_to_refresh;
    @Bind(R.id.rv_list)
    RecyclerView rv_list;

    View header;
    ImageView iv_more;
    TextView tv_name;
    TextView tv_type;

    Handler handler;
    LinearLayoutManager mLinearLayoutManager;
    LatelyVisitedAdapter mAdapter;

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
        header = LayoutInflater.from(getContext()).inflate(R.layout.header_mian_fra, (ViewGroup) rv_list.getParent(), false);
        iv_more = (ImageView) header.findViewById(R.id.iv_more);
        tv_name = (TextView) header.findViewById(R.id.tv_name);
        tv_type = (TextView) header.findViewById(R.id.tv_type);


        mLinearLayoutManager = new LinearLayoutManager(baseActivity);
        rv_list.setLayoutManager(mLinearLayoutManager);

        mAdapter = new LatelyVisitedAdapter(baseActivity, null);
        mAdapter.addHeaderView(header);
        rv_list.setAdapter(mAdapter);

        RefreshUtil.autoRefresh(pcfl_pull_to_refresh);

        iv_more.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_more :
                startActivity(new Intent(baseActivity, StoreVisitActivity.class));
                break;

            default:
                break;
        }
    }
}
