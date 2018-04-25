package com.hexing.mvpdemo.model;

import com.hexing.mvpdemo.utils.ServerHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author caibinglong
 *         date 2018/4/25.
 *         desc desc
 */

public class LoginBusiness {
    private ServerHelper serverHelper;

    public LoginBusiness() {
        serverHelper = new ServerHelper();
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
