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

    private LoginBusiness business;

    public LoginPresenter(LoginView loginView) {
        attachView(loginView);
        business = new LoginBusiness();
    }

    @Override
    public void attachView(LoginView view) {
        super.attachView(view);
    }

    public void login(String username, String password) {
        mvpView.get().showLoading();
        business.login(username, password, new ServerHelper.DataLoadListener() {
            @Override
            public void failure(Exception e) {
                mvpView.get().hideLoading();
            }

            @Override
            public void success(String result) {
                mvpView.get().hideLoading();
                mvpView.get().setData(result);
            }
        });
    }

}
