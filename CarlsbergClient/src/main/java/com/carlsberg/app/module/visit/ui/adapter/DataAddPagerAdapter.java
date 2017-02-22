package com.carlsberg.app.module.visit.ui.adapter;

import android.support.v4.app.FragmentManager;

import com.carlsberg.app.module.visit.ui.fragment.DataAddShowFragment;
import com.carlsberg.app.module.visit.ui.fragment.ScrollableBaseFragment;

import java.util.List;

import ru.noties.scrollable.FragmentPagerAdapterExt;

public class DataAddPagerAdapter extends FragmentPagerAdapterExt {
    private final List<DataAddShowFragment> mFragments;

    public DataAddPagerAdapter(FragmentManager fm, List<DataAddShowFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public DataAddShowFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments != null ? mFragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
