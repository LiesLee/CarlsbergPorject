package com.carlsberg.app.module.visit.ui.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.carlsberg.app.R;
import com.carlsberg.app.bean.visit.RegionInfo;

import java.util.List;

/**
 * Created by rrsh on 16/11/25.
 */

public class SelectAreaPopup extends PopupWindow {

    private View mainView;
    private PopupCallBack mListener;


    public SelectAreaPopup(Activity context, PopupCallBack popupListener, final List<RegionInfo> data) {
        super(context);
        mListener = popupListener;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainView = inflater.inflate(R.layout.dialog_select_area, null);
        final LinearLayout ll_list = (LinearLayout) mainView.findViewById(R.id.ll_list);
        LinearLayout ll_all = (LinearLayout) mainView.findViewById(R.id.ll_all);


        //设置SelectPicPopupWindow的View
        this.setContentView(mainView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popwindow_anim_style);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x66000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mainView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int tHeight = mainView.findViewById(R.id.ll_all).getTop();
                int dHeight = ll_list.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y > tHeight) {
                        dismiss();
                    }

                    if (y < dHeight) {
                        dismiss();
                    }
                }

                return true;
            }
        });

        /**
         * 点击 选择自己
         */
        ll_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.callback("0","全部"); //默认 0 为全部 ,店员为自己
                dismiss();
            }
        });

        for (int i = 0; i < data.size(); i++) {
            final int finalI = i;
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_area, null);
            TextView tv = (TextView) linearLayout.findViewById(R.id.tv_name);
            tv.setText(data.get(i).getName());
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.callback(data.get(finalI).getType(),data.get(finalI).getName());
                    dismiss();
                }
            });
            ll_list.addView(linearLayout);
        }


    }


    public interface PopupCallBack {
        void callback(String Id, String name);
    }
}
