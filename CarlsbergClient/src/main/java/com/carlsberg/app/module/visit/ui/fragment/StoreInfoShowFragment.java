package com.carlsberg.app.module.visit.ui.fragment;

import android.view.View;
import android.widget.ScrollView;

import com.carlsberg.app.R;
import com.carlsberg.app.module.visit.ui.adapter.BaseListAdapter;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseFragment;
import com.common.base.ui.BaseView;

import butterknife.Bind;
import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.LinearListView;
import ru.noties.scrollable.OnFlingOverListener;

/**
 * Created by LiesLee on 2017/2/18.
 */
@ActivityFragmentInject(contentViewId = R.layout.fra_store_info_show)
public class StoreInfoShowFragment extends ScrollableBaseFragment<BasePresenterImpl>{
    @Bind(R.id.linear_list_view)
    LinearListView linear_list_view;
    @Bind(R.id.scroll_view)
    ScrollView scroll_view;

    String title;

    @Override
    protected void initView(View fragmentRootView) {
        BaseListAdapter adapter = new BaseListAdapter(getActivity(), 30);
        linear_list_view.setAdapter(adapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public boolean canScrollVertically(int direction) {
        return scroll_view != null && scroll_view.canScrollVertically(direction);
    }

    @Override
    public void onFlingOver(int y, long duration) {
        if (scroll_view != null) {
            scroll_view.smoothScrollBy(0, y);
        }
    }

    @Override
    public CharSequence getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
