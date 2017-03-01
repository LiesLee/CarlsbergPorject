package com.carlsberg.app.module.visit.view;

import com.carlsberg.app.bean.visit.VisitStoreResponse;
import com.common.base.ui.BaseView;

/**
 * Created by LiesLee on 17/3/1.
 */

public interface UploadPicturesView extends BaseView{
    void loadPicturesDone(VisitStoreResponse data);
}
