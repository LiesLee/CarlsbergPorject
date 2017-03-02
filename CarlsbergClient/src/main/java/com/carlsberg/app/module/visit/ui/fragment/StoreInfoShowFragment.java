package com.carlsberg.app.module.visit.ui.fragment;

import android.view.View;
import android.widget.ScrollView;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.visit.TaskCollect;
import com.carlsberg.app.bean.visit.TaskScore;
import com.carlsberg.app.module.visit.ui.adapter.BaseListAdapter;
import com.carlsberg.app.module.visit.ui.adapter.DataListAdapter;
import com.carlsberg.app.module.visit.ui.adapter.DataListAdapter_2;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseFragment;
import com.common.base.ui.BaseView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.LinearListView;
import ru.noties.scrollable.OnFlingOverListener;

/**
 * Created by LiesLee on 2017/2/18.
 */
@ActivityFragmentInject(contentViewId = R.layout.fra_store_info_show)
public class StoreInfoShowFragment extends ScrollableBaseFragment<BasePresenterImpl> {
    @Bind(R.id.linear_list_view)
    LinearListView linear_list_view;
    @Bind(R.id.scroll_view)
    ScrollView scroll_view;

    List<TaskCollect> data = new ArrayList<>();
    List<TaskScore> data_2 = new ArrayList<>();

    String title;
    int showType = 0;
    private DataListAdapter adapter;
    private DataListAdapter_2 adapter_2;

    @Override
    protected void initView(View fragmentRootView) {
        if (showType == 0) {
            adapter = new DataListAdapter(baseActivity);
            linear_list_view.setAdapter(adapter);
        } else {
            adapter_2 = new DataListAdapter_2(baseActivity);
            linear_list_view.setAdapter(adapter_2);
        }

    }

    @Override
    public void initData() {

        if (showType == 0) {
            if (data != null) {
                refreshData(data);
            }
        } else {
            if (data_2 != null) {
                refreshData_2(data_2);
            }
        }

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

    public void refreshData(List<TaskCollect> data) {
        this.data = data;
        if (adapter != null) {
            adapter.setData(data);
            if (linear_list_view != null) {
                linear_list_view.setAdapter(adapter);
            }
        }

    }

    public void refreshData_2(List<TaskScore> data2) {
        this.data_2 = data2;
        if (adapter_2 != null) {
            adapter_2.setData(data2);
            if (linear_list_view != null) {
                linear_list_view.setAdapter(adapter_2);
            }

        }

    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }
}
