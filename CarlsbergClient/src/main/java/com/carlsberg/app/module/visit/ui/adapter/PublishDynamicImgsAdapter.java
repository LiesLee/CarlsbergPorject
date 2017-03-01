package com.carlsberg.app.module.visit.ui.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.visit.PhotoListBean;
import com.carlsberg.app.utils.GlideUtil;
import com.carlsberg.app.utils.UIHelper;
import com.common.base.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiesLee on 16/10/20.
 */
public class PublishDynamicImgsAdapter extends RecyclerView.Adapter<PublishDynamicImgsAdapter.Holder> {

    private UIHelper.TakePictureSavePathCallBack callBack;
    List<PhotoListBean.Image> imgs = new ArrayList<>();
    BaseActivity baseActivity;
    private int photo_type;

    public PublishDynamicImgsAdapter(BaseActivity baseActivity, UIHelper.TakePictureSavePathCallBack callBack) {
        this.baseActivity = baseActivity;
        this.callBack = callBack;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(baseActivity).inflate(R.layout.publish_dynamic_imgs_item, null));
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        if(imgs.size() < 4 && position == imgs.size()){
            holder.iv_publish_dynamic_imgs.setImageResource(R.mipmap.icon_add_dynamic_imgs);
            holder.iv_publish_dynamic_imgs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //选择图片
                    UIHelper.takePicture(baseActivity, PublishDynamicImgsAdapter.this, callBack);
                }
            });
            holder.iv_remove_imgs.setVisibility(View.GONE);

        }else{
            GlideUtil.loadImage(baseActivity,imgs.get(position).getImage_url(), holder.iv_publish_dynamic_imgs);
            holder.iv_remove_imgs.setVisibility(View.VISIBLE);
            holder.iv_remove_imgs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onImgDelete(holder.getAdapterPosition(), PublishDynamicImgsAdapter.this);
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        int size = 1;
        if(imgs != null){
            if(imgs.size() >= 4){
                size = imgs.size();
            }else{
                size = size + imgs.size();
            }
        }
        return size;
    }


    public List<PhotoListBean.Image> getImgs() {
        return imgs;
    }

    public void setImgs(List<PhotoListBean.Image> imgs) {
        this.imgs = imgs;
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public ImageView iv_remove_imgs;
        public ImageView iv_publish_dynamic_imgs;

        public Holder(View itemView) {
            super(itemView);
            iv_remove_imgs = (ImageView) itemView.findViewById(R.id.iv_remove_imgs);
            iv_publish_dynamic_imgs = (ImageView) itemView.findViewById(R.id.iv_publish_dynamic_imgs);
        }

    }

    public int getPhoto_type() {
        return photo_type;
    }

    public void setPhoto_type(int photo_type) {
        this.photo_type = photo_type;
    }
}
