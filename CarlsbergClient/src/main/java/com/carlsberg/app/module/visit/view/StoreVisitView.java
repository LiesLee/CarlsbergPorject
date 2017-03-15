package com.carlsberg.app.module.visit.view;

import com.carlsberg.app.bean.visit.VisitStoreResponse;
import com.common.base.ui.BaseView;

/**
 * Created by LiesLee on 17/2/28.
 */

public interface StoreVisitView extends BaseView {
    void loadDataDone(VisitStoreResponse data);
    void taskSignSuccessed();
    void loadError_307(String errorJson);
}
