package com.hexing.mvpdemo.presenter;

import com.hexing.libhexbase.activity.BasePresenter;
import com.hexing.mvpdemo.model.LoginBusiness;
import com.hexing.mvpdemo.utils.ServerHelper;
import com.hexing.mvpdemo.view.LoginView;


/**
 * @author by HEC271
 *         on 2017/12/29.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    public void setBusiness(LoginBusiness business) {
        this.business = business;
    }

    private LoginBusiness business;

    public LoginPresenter(LoginView loginView) {
        attachView(loginView);
        business = new LoginBusiness();
    }

    public void login(String username, String password) {
        if (getView() != null) {
            getView().showLoading();
        }
        business.login(username, password, new ServerHelper.DataLoadListener() {
            @Override
            public void failure(Exception e) {
                if (getView() != null) {
                    getView().hideLoading();
                }
            }

            @Override
            public void success(String result) {
                if (getView() != null) {
                    getView().showLoading();
                    getView().setData(result);
                }
            }
        });
    }

}
