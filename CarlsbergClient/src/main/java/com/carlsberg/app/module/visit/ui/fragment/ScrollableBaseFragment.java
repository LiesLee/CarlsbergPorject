package com.carlsberg.app.module.visit.ui.fragment;

import com.common.base.presenter.BasePresenter;
import com.common.base.ui.BaseFragment;
import com.common.base.ui.BaseView;

import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.OnFlingOverListener;

/**
 * Created by LiesLee on 2017/2/18.
 */

public abstract class ScrollableBaseFragment<T extends BasePresenter> extends BaseFragment<T> implements BaseView, CanScrollVerticallyDelegate, OnFlingOverListener {
    public abstract CharSequence getTitle();
}
