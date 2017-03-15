package com.carlsberg.app.module.visit.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.visit.VisitStoreResponse;
import com.carlsberg.app.common.Constant;
import com.carlsberg.app.module.visit.persenter.StoreVisitPresenter;
import com.carlsberg.app.module.visit.ui.adapter.BaseListAdapter;
import com.carlsberg.app.module.visit.ui.adapter.ViewPagerAdapter;
import com.carlsberg.app.module.visit.ui.fragment.ScrollableBaseFragment;
import com.carlsberg.app.module.visit.ui.fragment.StoreInfoShowFragment;
import com.carlsberg.app.module.visit.ui.fragment.StorePictureFragment;
import com.carlsberg.app.module.visit.view.StoreVisitView;
import com.carlsberg.app.utils.DialogHelper;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseView;
import com.common.http.HttpResult;
import com.common.utils.FastJsonUtil;
import com.views.util.RefreshUtil;
import com.views.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

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
public class StoreVisitActivity extends BaseActivity<StoreVisitPresenter> implements StoreVisitView {
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

    @Bind(R.id.ll_task_btn)
    LinearLayout ll_task_btn;

    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_type)
    TextView tv_type;
    @Bind(R.id.tv_status)
    TextView tv_status;


    @Bind(R.id.tv_sign_in)
    TextView tv_sign_in;
    @Bind(R.id.tv_data_collect)
    TextView tv_data_collect;
    @Bind(R.id.tv_clock_off)
    TextView tv_clock_off;
    @Bind(R.id.tv_take_a_photo)
    TextView tv_take_a_photo;

    @Bind(R.id.tv_area)
    TextView tv_area;
    @Bind(R.id.tv_store_type)
    TextView tv_store_type;
    @Bind(R.id.tv_nature)
    TextView tv_nature;
    @Bind(R.id.tv_store_address)
    TextView tv_store_address;
    @Bind(R.id.tv_checkin_date)
    TextView tv_checkin_date;
    @Bind(R.id.tv_goaway_date)
    TextView tv_goaway_date;

    ViewPagerAdapter adapter;
    Handler handler;
    private String store_id;
    private String store_name;
    private String task_id;


    @Override
    protected void initView() {
        mPresenter = new StoreVisitPresenter(this);
        store_id = getIntent().getStringExtra("store_id");
        task_id = getIntent().getStringExtra("task_id");
        store_name = getIntent().getStringExtra("store_name");
        if (TextUtils.isEmpty(store_id) || TextUtils.isEmpty(task_id)) {
            finish();
            ToastUtil.showShortToast(baseActivity, "ID数据为空");
            return;
        }

        tv_title.setText(store_name);

        handler = new Handler();

        mScrollableLayout.setDraggableView(tabs);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), getFragments());
        view_pager.setAdapter(adapter);
        tabs.setupWithViewPager(view_pager);

        //initialize Refresh layout
        RefreshUtil.init_material_pull(baseActivity, pcfl_pull_to_refresh, new RefreshUtil.PtrRefreshListener() {
            @Override
            public void OnRefresh(final PtrFrameLayout frame) {
                mPresenter.storeView(store_id, task_id);
            }
        });

        tv_sign_in.setOnClickListener(this);
        tv_data_collect.setOnClickListener(this);
        tv_clock_off.setOnClickListener(this);
        tv_take_a_photo.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null && pcfl_pull_to_refresh != null) {
            RefreshUtil.autoRefresh(pcfl_pull_to_refresh);
        }
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
        f1.setShowType(0);
        f1.setTitle("门店上座率情况");
        StoreInfoShowFragment f2 = new StoreInfoShowFragment();
        f2.setShowType(1);
        f2.setTitle("促销员表现评分");

        Collections.addAll(list, f1, f2);

        return list;
    }

    @Override
    public void initData() {
    }

    @Override
    protected void onClickBackFinishBefore() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_in:
                mPresenter.taskSign(store_id, task_id, "checkin");
                break;

            case R.id.tv_data_collect:
                Intent intentD = new Intent(baseActivity, DataAddActivity.class);
                intentD.putExtra("store_id", store_id);
                intentD.putExtra("task_id", task_id);
                intentD.putExtra("store_name", store_name);
                startActivity(intentD);
                break;

            case R.id.tv_clock_off:
                mPresenter.taskSign(store_id, task_id, "goaway");
                break;

            case R.id.tv_take_a_photo:
                Intent intent = new Intent(baseActivity, UploadPicturesActivity.class);
                intent.putExtra("store_id", store_id);
                intent.putExtra("task_id", task_id);
                intent.putExtra("store_name", store_name);
                startActivity(intent);
                break;

            default:
                break;
        }
    }


    @Override
    public void hideProgress(int type) {
        super.hideProgress(type);
        if (type == Constant.PROGRESS_TYPE_LIST) {
            pcfl_pull_to_refresh.refreshComplete();
        }
    }


    @Override
    public void loadDataDone(VisitStoreResponse data) {
        if (data != null) {
            task_id = data.getStore_task().getTask_id() + "";
            store_name = data.getStore_task().getStore_name();
            tv_title.setText(store_name);
            //头部文字排版
            tv_name.setText(data.getStore_task().getStore_name());
            tv_type.setText(data.getStore_task().getFlag_plan_title());
            tv_status.setText(data.getStore_task().getTask_status_title());

            if (data.getStore_task().getTask_status() == 0) {
                tv_status.setTextColor(getResources().getColor(R.color.blue));
            } else if (data.getStore_task().getTask_status() == 1) {
                tv_status.setTextColor(getResources().getColor(R.color.green));
            } else {
                tv_status.setTextColor(getResources().getColor(R.color.red));
            }
            if (data.getStore_task().getFlag_plan() == 0) {
                tv_type.setBackgroundResource(R.color.black_54);
            } else {
                tv_type.setBackgroundResource(R.color.red);
            }

            tv_area.setText("所属区域：" + data.getStore_task().getArea_title() + " - " + data.getStore_task().getRegion_title());
            tv_store_type.setText("门店性质：" + data.getStore_task().getType_title());
            tv_nature.setText("门店类型：" + data.getStore_task().getNature_title());
            tv_store_address.setText("门店地址：" + data.getStore_task().getStore_addr());

            tv_checkin_date.setText("入店时间：" + data.getStore_task().getCheckin_date());
            tv_goaway_date.setText("离店时间：" + data.getStore_task().getGoaway_date());

            //按钮适配
            if (data.getTask_button() != null && data.getTask_button().size() > 0) {
                ll_task_btn.setVisibility(View.VISIBLE);
                for (VisitStoreResponse.TaskButton taskButton : data.getTask_button()) {
                    switch (taskButton.getButton_type()) {
                        case "checkin":
                            tv_sign_in.setVisibility(taskButton.getIs_open() == 0 ? View.GONE : View.VISIBLE);
                            tv_sign_in.setText(taskButton.getButton_title());
                            break;
                        case "collect":
                            tv_data_collect.setVisibility(taskButton.getIs_open() == 0 ? View.GONE : View.VISIBLE);
                            tv_data_collect.setText(taskButton.getButton_title());
                            break;
                        case "photo":
                            tv_take_a_photo.setVisibility(taskButton.getIs_open() == 0 ? View.GONE : View.VISIBLE);
                            tv_take_a_photo.setText(taskButton.getButton_title());
                            break;
                        case "goaway":
                            tv_clock_off.setVisibility(taskButton.getIs_open() == 0 ? View.GONE : View.VISIBLE);
                            tv_clock_off.setText(taskButton.getButton_title());
                            break;

                        default:
                            break;
                    }
                }
            } else {
                ll_task_btn.setVisibility(View.GONE);
            }

            //viewpager数据
            StorePictureFragment f1 = (StorePictureFragment) adapter.getmFragments().get(0);
            f1.refreshPictures(data.getTask_photo());
            StoreInfoShowFragment f2 = (StoreInfoShowFragment) adapter.getmFragments().get(1);
            f2.refreshData(data.getTask_collect());
            StoreInfoShowFragment f3 = (StoreInfoShowFragment) adapter.getmFragments().get(2);
            f3.refreshData_2(data.getTask_score());

            if(data.getTask_photo() == null || data.getTask_collect() == null || data.getTask_score() == null) return;

            if (data.getTask_photo().size() == 0) {
                view_pager.setCurrentItem(0);
            } else if (data.getTask_collect().size() == 0) {
                view_pager.setCurrentItem(1);
            } else if (data.getTask_score().size() == 0) {
                view_pager.setCurrentItem(2);
            } else {
                view_pager.setCurrentItem(0);
            }

        }
    }

    /**
     * 打卡成功
     */
    @Override
    public void taskSignSuccessed() {
        showShortToast("操作成功");
        mPresenter.storeView(store_id, task_id);
    }

    @Override
    public void loadError_307(String errorJson) {
        try {
            JSONObject jsonObject = new JSONObject(errorJson);
            if(jsonObject.has("msg") && jsonObject.has("data") && jsonObject.getJSONObject("data")!=null){
                String msg = jsonObject.getString("msg");
                JSONObject jEntity = jsonObject.getJSONObject("data");
                final String store_id = jEntity.getString("store_id");
                final String task_id = jEntity.getString("task_id");
                if(!TextUtils.isEmpty(store_id) && !TextUtils.isEmpty(task_id)){
                    DialogHelper.show2btnDialog(baseActivity, msg, "取消", "立即拜访", false, new DialogHelper.DialogOnclickCallback() {
                        @Override
                        public void onButtonClick(Dialog dialog) {
                            finish();
                        }
                    }, new DialogHelper.DialogOnclickCallback() {
                        @Override
                        public void onButtonClick(Dialog dialog) {
                            StoreVisitActivity.this.store_id = store_id;
                            StoreVisitActivity.this.task_id = task_id;
                            if (mPresenter != null && pcfl_pull_to_refresh != null) {
                                RefreshUtil.autoRefresh(pcfl_pull_to_refresh);
                            }

                        }
                    }).setCancelable(false);
                }else{
                    toast("307数据解析错误");
                    finish();
                }

            }else{
                toast("307数据解析错误");
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            toast("307数据解析错误");
            finish();
        }
    }


}
