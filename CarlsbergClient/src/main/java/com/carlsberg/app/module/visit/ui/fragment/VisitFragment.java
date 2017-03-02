package com.carlsberg.app.module.visit.ui.fragment;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.carlsberg.app.R;
import com.carlsberg.app.bean.visit.RegionInfo;
import com.carlsberg.app.bean.visit.VisitRespone;
import com.carlsberg.app.module.visit.persenter.VisitFragmentPresenter;
import com.carlsberg.app.module.visit.ui.adapter.VisitChildFragmentAdapter;
import com.carlsberg.app.module.visit.ui.popup.SelectAreaPopup;
import com.carlsberg.app.module.visit.view.VisitFragmentView;
import com.carlsberg.app.utils.DialogHelper;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseFragment;
import com.common.base.ui.BaseView;
import com.views.util.RefreshUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;

/**
 * Created by LiesLee on 17/2/14.
 */
@ActivityFragmentInject(contentViewId = R.layout.fra_visit)
public class VisitFragment extends BaseFragment<VisitFragmentPresenter> implements VisitFragmentView {
    @Bind(R.id.et_search)
    EditText et_search;
    @Bind(R.id.tv_area)
    TextView tv_area;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private SelectAreaPopup popup;
    private int mPagePosition;

    VisitChildFragmentAdapter mAdapter;

    Handler handler;

    @Override
    protected void initView(View fragmentRootView) {
        mPresenter = new VisitFragmentPresenter(this);
        handler = new Handler();
        et_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 先隐藏键盘
                    ((InputMethodManager) baseActivity.getSystemService(BaseActivity.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(baseActivity
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    //接下来在这里做你自己想要做的事，实现自己的业务。

                    et_search.clearFocus();
                    String str = et_search.getText().toString();

                    if(mAdapter==null || mAdapter.getFragments() == null || mAdapter.getFragments().size() == 0){
                        return false;
                    }
                    mAdapter.getFragments().get(viewPager.getCurrentItem()).setSearch_text(str);

                    mAdapter.getFragments().get(viewPager.getCurrentItem()).load();

                }

                return false;
            }
        });



        mAdapter = new VisitChildFragmentAdapter(getChildFragmentManager(), baseActivity);
        List<VisitChildFragment> list = new ArrayList<>();
        VisitChildFragment c1 = new VisitChildFragment();
        VisitChildFragment c2 = new VisitChildFragment();
        c1.setName("今日拜访");
        c1.setShowType(1);
        c2.setName("最近拜访");
        c2.setShowType(0);
        Collections.addAll(list, c1, c2);
        mAdapter.setFragments(list);
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(mAdapter.getCount());
        tabLayout.setupWithViewPager(viewPager);

        tv_area.setOnClickListener(this);
        et_search.setOnClickListener(this);

        et_search.setCursorVisible(false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(mAdapter==null || mAdapter.getFragments() == null || mAdapter.getFragments().size() == 0){
                    return;
                }

                String str = mAdapter.getFragments().get(position).getStatus_name();
                tv_area.setText(TextUtils.isEmpty(str) ? "全部" : str);
                et_search.setText(mAdapter.getFragments().get(position).getSearch_text());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void initData() {


    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_area:
                if(mAdapter==null || mAdapter.getFragments() == null || mAdapter.getFragments().size() == 0){
                    break;
                }

                List<VisitRespone.StatusType> typeList = mAdapter.getFragments().get(viewPager.getCurrentItem()).getTypeList();
                if(typeList != null && typeList.size() > 0){
                    popup = new SelectAreaPopup(baseActivity, new SelectAreaPopup.PopupCallBack() {
                        @Override
                        public void callback(VisitRespone.StatusType statusType) {
                            mAdapter.getFragments().get(viewPager.getCurrentItem()).setStatus_type(statusType.getStatus_type());
                            mAdapter.getFragments().get(viewPager.getCurrentItem()).setStatus_name(statusType.getStatus_title());
                            tv_area.setText(statusType.getStatus_title());
                        }
                    }, typeList);
                    popup.showAsDropDown(tv_area);

                }else{
                    DialogHelper.showTipsDialog(baseActivity, "数据还没加载到哦~\n请稍后或下拉重新加载!", "好的", null);
                }
                break;
            case R.id.et_search:
                et_search.setCursorVisible(true);
                break;

            default:
                break;
        }
    }

    /**
     * 选中页面
     * @param position
     */
    public void currentPage(int position){
        if(mAdapter != null && viewPager!=null){
            viewPager.setCurrentItem(position);
        }
    }

}
