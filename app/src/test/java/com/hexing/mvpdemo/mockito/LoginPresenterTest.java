package com.hexing.mvpdemo.mockito;

import com.hexing.mvpdemo.presenter.LoginPresenter;

import org.junit.Test;


/**
 * @author caibinglong
 *         date 2018/4/25.
 *         desc desc
 */

public class LoginPresenterTest{
    @Test
    public void testLogin() {
        new LoginPresenter(null).login("","");
    }

    public LoginPresenterTest() {
        super();
    }
}
