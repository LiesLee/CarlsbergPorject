package com.carlsberg.app.module.visit.view;

import com.carlsberg.app.bean.visit.CollectViewResponse;
import com.common.base.ui.BaseView;

/**
 * Created by LiesLee on 17/3/2.
 */

public interface DataAddView extends BaseView {
    void collectView(CollectViewResponse data);

    void saveSuccessed();
}
