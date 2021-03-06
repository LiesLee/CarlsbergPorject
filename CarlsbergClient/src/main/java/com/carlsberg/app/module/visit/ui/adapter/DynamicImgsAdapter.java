package com.carlsberg.app.module.visit.ui.adapter;

import android.content.Intent;
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
import com.views.ImageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiesLee on 16/10/20.
 */
public class DynamicImgsAdapter extends RecyclerView.Adapter<DynamicImgsAdapter.Holder> {

    List<PhotoListBean.Image> imgs = new ArrayList<>();
    BaseActivity baseActivity;

    public DynamicImgsAdapter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(baseActivity).inflate(R.layout.publish_dynamic_imgs_item, null));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        holder.iv_remove_imgs.setVisibility(View.GONE);
        GlideUtil.loadImage(baseActivity ,imgs.get(position).getImage_url(), holder.iv_publish_dynamic_imgs);
        holder.iv_publish_dynamic_imgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> imgsUrl = new ArrayList<String>();
                for (int i=0; i < imgs.size(); i++){
                    imgsUrl.add(imgs.get(i).getImage_url());
                }
                Intent intent = new Intent(baseActivity, ImageActivity.class);
                intent.putStringArrayListExtra("imgUrls", imgsUrl);
                intent.putExtra("pagerPosition", position);
                baseActivity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imgs == null ? 0 : imgs.size();
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

}
