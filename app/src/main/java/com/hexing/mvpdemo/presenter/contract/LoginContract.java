package com.hexing.mvpdemo.presenter.contract;

import com.hexing.mvpdemo.bean.UserInfoBean;
import com.hexing.mvpdemo.presenter.BasePresenter;
import com.hexing.mvpdemo.view.BaseView;


/**
 * @author by HEC271
 *         on 2017/12/29.
 */

public interface LoginContract {
    interface Presenter extends BasePresenter {
        void login(String username, String password);
    }

    interface View extends BaseView<Presenter> {
        void showProgress();

        void hideProgress();

        void showUserInfo(UserInfoBean userInfoBean);

        void showError(int resourceId);
    }
}
