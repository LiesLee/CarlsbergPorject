package com.carlsberg.app.module.home.view;

import com.carlsberg.app.bean.home.HomeDataResponse;
import com.common.base.ui.BaseView;

/**
 * Created by LiesLee on 17/2/27.
 */

public interface MainFragmentView extends BaseView {
    void loadListDone(HomeDataResponse dataResponse);
}
