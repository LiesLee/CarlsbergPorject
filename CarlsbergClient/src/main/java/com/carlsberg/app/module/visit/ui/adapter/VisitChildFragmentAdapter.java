package com.carlsberg.app.module.visit.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.carlsberg.app.module.visit.ui.fragment.VisitChildFragment;
import com.common.base.ui.BaseActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiesLee on 16/11/4.
 */
public class VisitChildFragmentAdapter extends FragmentPagerAdapter {

    private BaseActivity baseActivity;
    List<VisitChildFragment> fragments = new ArrayList<>();

    public VisitChildFragmentAdapter(FragmentManager fm, BaseActivity baseActivity) {
        super(fm);
        this.baseActivity = baseActivity;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    public List<VisitChildFragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<VisitChildFragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getName();
    }
}
