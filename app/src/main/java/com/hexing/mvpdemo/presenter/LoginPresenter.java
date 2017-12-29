package com.hexing.mvpdemo.presenter;

import com.hexing.mvpdemo.bean.UserInfoBean;
import com.hexing.mvpdemo.model.LoginBusiness;
import com.hexing.mvpdemo.presenter.contract.LoginContract;

/**
 * @author by HEC271
 *         on 2017/12/29.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View loginView;
    private LoginBusiness loginBusiness;

    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
        this.loginView.setPresenter(this);
        loginBusiness = new LoginBusiness(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void login(String username, String password) {
        this.loginView.showProgress();
        loginBusiness.login(username, password);
    }

    public void showUserInfo(UserInfoBean bean) {
        this.loginView.showUserInfo(bean);
        this.loginView.hideProgress();
    }

    public void showError(int resourceId){
        this.loginView.hideProgress();
        this.loginView.showError(resourceId);
    }
}
