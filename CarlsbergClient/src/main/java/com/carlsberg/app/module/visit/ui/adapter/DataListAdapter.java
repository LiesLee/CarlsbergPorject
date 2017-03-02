package com.carlsberg.app.module.visit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.visit.TaskCollect;

import java.util.ArrayList;
import java.util.List;

public class DataListAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    List<TaskCollect> data = new ArrayList<>();

    public DataListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_data_list, parent, false);
        } else {
            view = convertView;
        }

        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
        if(data != null && position < data.size()){
            tv_title.setText(data.get(position).getTitle()+"：");
            tv_content.setText(data.get(position).getData());
        }

        return view;
    }

    public List<TaskCollect> getData() {
        return data;
    }

    public void setData(List<TaskCollect> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
