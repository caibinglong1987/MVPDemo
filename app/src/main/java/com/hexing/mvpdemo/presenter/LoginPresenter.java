package com.hexing.mvpdemo.presenter;

import com.hexing.libhexbase.activity.BasePresenter;
import com.hexing.mvpdemo.utils.ServerHelper;
import com.hexing.mvpdemo.view.LoginView;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author by HEC271
 *         on 2017/12/29.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    private ServerHelper serverHelper;

    public LoginPresenter(LoginView loginView) {
        attachView(loginView);
    }

    public void login(String username, String password) {
        if (getView() != null) {
            getView().showLoading();
        }
        serverHelper = new ServerHelper();
        login(username, password, new ServerHelper.DataLoadListener() {
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

    public void login(String username, String password, ServerHelper.DataLoadListener listener) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            serverHelper.login(jsonObject);
            serverHelper.setListener(listener);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
