package com.hexing.mvpdemo.model;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.hexing.mvpdemo.R;
import com.hexing.mvpdemo.bean.CommonListResult;
import com.hexing.mvpdemo.bean.StudentBean;
import com.hexing.mvpdemo.bean.UserInfoBean;
import com.hexing.mvpdemo.presenter.LoginPresenter;
import com.hexing.mvpdemo.utils.JsonUtil;
import com.hexing.mvpdemo.utils.ServerHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author by HEC271
 *         登录相关业务
 *         on 2017/12/29.
 */

public class LoginBusiness implements ServerHelper.DataLoadListener {
    private LoginPresenter loginPresenter;
    private ServerHelper serverHelper;

    public LoginBusiness(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
        serverHelper = new ServerHelper();
        serverHelper.setListener(this);
    }

    public void login(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            this.loginPresenter.showError(R.string.login_para_error);
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            serverHelper.login(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public UserInfoBean getUser() {
        return null;
    }

    @Override
    public void failure(Exception e) {
        this.loginPresenter.showError(R.string.login_server_error);
    }

    @Override
    public void success(String result) {
        UserInfoBean userInfoBean = JsonUtil.fromJson(result, UserInfoBean.class);
        if (userInfoBean != null) {
            this.loginPresenter.showUserInfo(userInfoBean);
        }
    }


}
