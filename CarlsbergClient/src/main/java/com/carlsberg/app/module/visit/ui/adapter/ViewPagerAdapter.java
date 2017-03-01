package com.carlsberg.app.module.visit.ui.adapter;

import android.support.v4.app.FragmentManager;

import com.carlsberg.app.module.visit.ui.fragment.ScrollableBaseFragment;

import java.util.List;

import ru.noties.scrollable.FragmentPagerAdapterExt;

public class ViewPagerAdapter extends FragmentPagerAdapterExt {
    private final List<ScrollableBaseFragment> mFragments;

    public ViewPagerAdapter(FragmentManager fm, List<ScrollableBaseFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public ScrollableBaseFragment getItem(int position) {
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

    public boolean canScrollVertically(int position, int direction) {
        return getItem(position).canScrollVertically(direction);
    }

    public List<ScrollableBaseFragment> getmFragments() {
        return mFragments;
    }
}
