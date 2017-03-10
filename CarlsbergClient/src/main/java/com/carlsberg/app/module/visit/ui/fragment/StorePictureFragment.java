package com.carlsberg.app.module.visit.ui.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.visit.PhotoListBean;
import com.carlsberg.app.module.home.ui.adapter.LatelyVisitedAdapter;
import com.carlsberg.app.module.visit.ui.adapter.DynamicImgsAdapter;
import com.carlsberg.app.module.visit.ui.adapter.PublishDynamicImgsAdapter;
import com.carlsberg.app.module.visit.ui.adapter.StorePicturesAdapter;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseFragment;
import com.common.base.ui.BaseView;
import com.views.util.RefreshUtil;

import java.util.List;

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

    @Bind(R.id.rv_list)
    RecyclerView rv_list;
    @Bind(R.id.ll_empty_tips)
    LinearLayout ll_empty_tips;

    Handler handler;
    LinearLayoutManager mLinearLayoutManager;

    StorePicturesAdapter storePicturesAdapter;
    private List<PhotoListBean> picList;


    @Override
    protected void initView(View fragmentRootView) {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(baseActivity);
        rv_list.setLayoutManager(mLinearLayoutManager);
        storePicturesAdapter = new StorePicturesAdapter(baseActivity, null);
        rv_list.setAdapter(storePicturesAdapter);

    }

    @Override
    public void initData() {
        if(picList!=null){
            refreshPictures(picList);
        }
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

    public void refreshPictures(List<PhotoListBean> picList){
        if(storePicturesAdapter==null) return;

        this.picList = picList;

        if(picList!=null && picList.size() > 0){
            for(int i = 0; i < picList.size() ; i++){
                DynamicImgsAdapter imgsAdapter = new DynamicImgsAdapter(baseActivity);
                imgsAdapter.setImgs(picList.get(i).getLists());
                picList.get(i).setImgsAdapter_2(imgsAdapter);
            }
            storePicturesAdapter.setData(picList);
        }else {
            storePicturesAdapter.setData(null);
        }

        if(ll_empty_tips != null) ll_empty_tips.setVisibility(storePicturesAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);


    }


}
