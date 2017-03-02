package com.carlsberg.app.module.visit.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.common.Test;
import com.carlsberg.app.bean.visit.TaskCollect;
import com.carlsberg.app.module.visit.ui.adapter.BaseListAdapter;
import com.carlsberg.app.module.visit.ui.adapter.DataAddAdapter;
import com.carlsberg.app.module.visit.ui.adapter.StorePicturesAdapter;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseFragment;
import com.common.base.ui.BaseView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import ru.noties.scrollable.LinearListView;

/**
 * Created by LiesLee on 2017/2/18.
 */
@ActivityFragmentInject(contentViewId = R.layout.fra_data_add_show)
public class DataAddShowFragment extends BaseFragment<BasePresenterImpl> implements BaseView{
    @Bind(R.id.rv_list)
    RecyclerView rv_list;

    private List<TaskCollect> task_collect;

    String title;
    private LinearLayoutManager mLinearLayoutManager;
    private DataAddAdapter mAdapter;

    @Override
    protected void initView(View fragmentRootView) {
        mLinearLayoutManager = new LinearLayoutManager(baseActivity);
        rv_list.setLayoutManager(mLinearLayoutManager);
//        List<Test> list = new ArrayList<>();
//        Test t1 = new Test();
//        Test t2 = new Test();
//        Test t3 = new Test();
//        Test t4 = new Test();
//        Test t5 = new Test();
//        Test t6 = new Test();
//        Test t7 = new Test();
//        Test t8 = new Test();
//        Test t9 = new Test();
//        Test t10 = new Test();
//
//        t1.setType(1);
//        t2.setType(1);
//        t3.setType(1);
//        t4.setType(1);
//        t5.setType(1);
//        t6.setType(1);
//        t7.setType(1);
//        t8.setType(2);
//        t9.setType(2);
//        t10.setType(3);
//
//        Collections.addAll(list, t1,t2,t3,t4,t5,t6,t7,t8,t9,t10);
        mAdapter = new DataAddAdapter(baseActivity, null);
        rv_list.setAdapter(mAdapter);

    }

    @Override
    public void initData() {
        if(task_collect!=null){
            refreshData(task_collect);
        }
    }

    public void refreshData(List<TaskCollect> task_collect){
        this.task_collect = task_collect;
        if(mAdapter==null){
            return;
        }
        mAdapter.setData(task_collect);
    }


    public CharSequence getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TaskCollect> getTask_collect() {
        return task_collect;
    }

    public void setTask_collect(List<TaskCollect> task_collect) {
        this.task_collect = task_collect;
    }

    public DataAddAdapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(DataAddAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }
}
