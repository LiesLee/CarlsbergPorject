package com.carlsberg.app.module.my.view;

import com.carlsberg.app.bean.common.User;
import com.common.base.ui.BaseView;

/**
 * Created by LiesLee on 17/2/23.
 */

public interface LoginView extends BaseView {

    void loginSucceed(User data);
}
