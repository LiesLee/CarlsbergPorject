package com.carlsberg.app.module.visit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carlsberg.app.R;
import com.carlsberg.app.module.visit.ui.adapter.BaseListAdapter;
import com.carlsberg.app.module.visit.ui.adapter.ViewPagerAdapter;
import com.carlsberg.app.module.visit.ui.fragment.ScrollableBaseFragment;
import com.carlsberg.app.module.visit.ui.fragment.StoreInfoShowFragment;
import com.carlsberg.app.module.visit.ui.fragment.StorePictureFragment;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseView;
import com.views.util.RefreshUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.LinearListView;
import ru.noties.scrollable.OnFlingOverListener;
import ru.noties.scrollable.OnScrollChangedListener;
import ru.noties.scrollable.ScrollableLayout;

/**
 * Created by LiesLee on 2017/2/18.
 */
@ActivityFragmentInject(contentViewId = R.layout.act_store_visit, toolbarTitle = R.string.app_name)
public class StoreVisitActivity extends BaseActivity<BasePresenterImpl> implements BaseView {
    private static final String ARG_LAST_SCROLL_Y = "arg.LastScrollY";

    @Bind(R.id.pcfl_pull_to_refresh)
    PtrClassicFrameLayout pcfl_pull_to_refresh;

    @Bind(R.id.scrollable_layout)
    ScrollableLayout mScrollableLayout;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.view_pager)
    ViewPager view_pager;
    @Bind(R.id.header)
    LinearLayout header;

    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_type)
    TextView tv_type;
    @Bind(R.id.tv_status)
    TextView tv_status;

    @Bind(R.id.ll_store_info)
    LinearListView ll_store_info;


    @Bind(R.id.tv_sign_in)
    TextView tv_sign_in;
    @Bind(R.id.tv_data_collect)
    TextView tv_data_collect;
    @Bind(R.id.tv_clock_off)
    TextView tv_clock_off;
    @Bind(R.id.tv_take_a_photo)
    TextView tv_take_a_photo;

    ViewPagerAdapter adapter;
    Handler handler;


    @Override
    protected void initView() {
        handler = new Handler();

        mScrollableLayout.setDraggableView(tabs);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), getFragments());
        view_pager.setAdapter(adapter);
        tabs.setupWithViewPager(view_pager);

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

        tv_sign_in.setOnClickListener(this);
        tv_data_collect.setOnClickListener(this);
        tv_clock_off.setOnClickListener(this);
        tv_take_a_photo.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init ScrollableLayout
        mScrollableLayout.setCanScrollVerticallyDelegate(new CanScrollVerticallyDelegate() {
            @Override
            public boolean canScrollVertically(int direction) {
                return adapter.canScrollVertically(view_pager.getCurrentItem(), direction);
            }
        });
        mScrollableLayout.setOnFlingOverListener(new OnFlingOverListener() {
            @Override
            public void onFlingOver(int y, long duration) {
                adapter.getItem(view_pager.getCurrentItem()).onFlingOver(y, duration);
            }
        });
        mScrollableLayout.setOnScrollChangedListener(new OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int y, int oldY, int maxY) {

                final float tabsTranslationY;
                if (y < maxY) {
                    tabsTranslationY = .0F;
                } else {
                    tabsTranslationY = y - maxY;
                }

                tabs.setTranslationY(tabsTranslationY);

                header.setTranslationY(y / 2);
            }
        });

        if (savedInstanceState != null) {
            final int y = savedInstanceState.getInt(ARG_LAST_SCROLL_Y);
            mScrollableLayout.post(new Runnable() {
                @Override
                public void run() {
                    mScrollableLayout.scrollTo(0, y);
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_LAST_SCROLL_Y, mScrollableLayout.getScrollY());
        super.onSaveInstanceState(outState);
    }


    private List<ScrollableBaseFragment> getFragments() {
        List<ScrollableBaseFragment> list = new ArrayList<>();
        list.add(new StorePictureFragment());
        StoreInfoShowFragment f1 = new StoreInfoShowFragment();
        f1.setTitle("门店上座率情况");
        StoreInfoShowFragment f2 = new StoreInfoShowFragment();
        f2.setTitle("促销员表现评分");

        Collections.addAll(list, f1, f2);

        return list;
    }

    @Override
    public void initData() {
        BaseListAdapter adapter = new BaseListAdapter(baseActivity, 4);
        ll_store_info.setAdapter(adapter);
    }

    @Override
    protected void onClickBackFinishBefore() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_in :

                break;

            case R.id.tv_data_collect :
                startActivity(new Intent(baseActivity, DataAddActivity.class));
                break;

            case R.id.tv_clock_off :

                break;

            case R.id.tv_take_a_photo :
                startActivity(new Intent(baseActivity, UploadPicturesActivity.class));
                break;

            default:
                break;
        }
    }


}
