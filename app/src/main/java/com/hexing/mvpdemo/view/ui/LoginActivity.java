package com.hexing.mvpdemo.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hexing.mvpdemo.R;
import com.hexing.mvpdemo.bean.UserInfoBean;
import com.hexing.mvpdemo.presenter.LoginPresenter;
import com.hexing.mvpdemo.presenter.contract.LoginContract;
import com.hexing.mvpdemo.view.LoadingDialog;

/**
 * @author by HEC271
 *         on 2017/12/29.
 */

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {
    private LoginContract.Presenter presenter;
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
        //this.presenter.login("", "");
        new LoginPresenter(this);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgress() {
        LoadingDialog.showSysLoadingDialog(this, "");
    }

    @Override
    public void hideProgress() {
        LoadingDialog.cancelLoadingDialog();
    }

    @Override
    public void showUserInfo(UserInfoBean userInfoBean) {

    }

    @Override
    public void showError(int resourcesId) {
        Toast.makeText(this, resourcesId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvLogin) {
            this.presenter.login(etUsername.getText().toString(), etPassword.getText().toString());
        }
    }
}
