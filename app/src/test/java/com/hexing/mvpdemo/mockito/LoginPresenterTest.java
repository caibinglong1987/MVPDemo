package com.hexing.mvpdemo.mockito;

import com.hexing.mvpdemo.model.LoginBusiness;
import com.hexing.mvpdemo.presenter.LoginPresenter;

import org.junit.Test;
import org.mockito.Mockito;



/**
 * @author caibinglong
 *         date 2018/4/25.
 *         desc desc
 */

public class LoginPresenterTest{
    @Test
    public void testLogin() {
        LoginBusiness mockUserManager = Mockito.mock(LoginBusiness.class);
        LoginPresenter loginPresenter = new LoginPresenter(null);
        loginPresenter.setBusiness(mockUserManager);

        loginPresenter.login("xiaochuang", "password");

        Mockito.verify(mockUserManager).login("xiaochuang", "password", null);
    }

    public LoginPresenterTest() {
        super();
    }
}
