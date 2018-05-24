package com.hexing.mvpdemo.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hexing.libhexbase.activity.HexMVPBaseActivity;
import com.hexing.mvpdemo.R;
import com.hexing.mvpdemo.model.bean.UserInfoBean;
import com.hexing.mvpdemo.presenter.LoginPresenter;
import com.hexing.mvpdemo.view.LoginView;

/**
 * @author by HEC271
 *         on 2017/12/29.
 */

public class LoginActivity extends HexMVPBaseActivity<LoginPresenter> implements LoginView, View.OnClickListener {
    private TextView tvLogin;
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvLogin.setOnClickListener(this);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void setData(String data) {

    }

    @Override
    public void setUser(UserInfoBean user) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvLogin) {
            mvpPresenter.login(etUsername.getText().toString(), etPassword.getText().toString());
        }
    }
}
