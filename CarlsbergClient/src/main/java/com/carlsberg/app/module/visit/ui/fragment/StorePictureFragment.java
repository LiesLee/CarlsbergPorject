package com.carlsberg.app.module.visit.ui.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.carlsberg.app.R;
import com.carlsberg.app.module.home.ui.adapter.LatelyVisitedAdapter;
import com.carlsberg.app.module.visit.ui.adapter.StorePicturesAdapter;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseFragment;
import com.common.base.ui.BaseView;
import com.views.util.RefreshUtil;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.OnFlingOverListener;

/**
 * Created by LiesLee on 2017/2/18.
 */
@ActivityFragmentInject(contentViewId = R.layout.fra_store_picture)
public class StorePictureFragment extends ScrollableBaseFragment<BasePresenterImpl> {

//    @Bind(R.id.pcfl_pull_to_refresh)
//    PtrClassicFrameLayout pcfl_pull_to_refresh;
    @Bind(R.id.rv_list)
    RecyclerView rv_list;

    Handler handler;
    LinearLayoutManager mLinearLayoutManager;

    StorePicturesAdapter storePicturesAdapter;


    @Override
    protected void initView(View fragmentRootView) {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });
//        //initialize Refresh layout
//        RefreshUtil.init_material_pull(baseActivity, pcfl_pull_to_refresh, new RefreshUtil.PtrRefreshListener() {
//            @Override
//            public void OnRefresh(final PtrFrameLayout frame) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        pcfl_pull_to_refresh.refreshComplete();
//                    }
//                }, 3000);
//            }
//        });

        mLinearLayoutManager = new LinearLayoutManager(baseActivity);
        rv_list.setLayoutManager(mLinearLayoutManager);
        storePicturesAdapter = new StorePicturesAdapter(baseActivity, null);
        rv_list.setAdapter(storePicturesAdapter);

//        RefreshUtil.autoRefresh(pcfl_pull_to_refresh);
    }

    @Override
    public void initData() {

    }

    /**
     * 返回canScrollVertically参数
     * @param direction
     * @return
     */
    @Override
    public boolean canScrollVertically(int direction) {
        return rv_list != null && rv_list.canScrollVertically(direction);
    }

    /**
     * 调用smoothScrollBy参数
     * @param y        the final scroll y (theoretical) for underlining scrolling child
     * @param duration theoretical duration of the scroll event based on velocity value
     */
    @Override
    public void onFlingOver(int y, long duration) {
        if (rv_list != null) {
            rv_list.smoothScrollBy(0, y);
        }
    }

    @Override
    public CharSequence getTitle() {
        return "门店照片";
    }
}
