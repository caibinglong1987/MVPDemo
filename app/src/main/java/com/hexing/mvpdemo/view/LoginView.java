package com.hexing.mvpdemo.view;

import com.hexing.libhexbase.view.BaseView;
import com.hexing.mvpdemo.model.bean.UserInfoBean;

/**
 * @author caibinglong
 *         date 2017/12/26.
 *         desc desc
 */

public interface LoginView extends BaseView{
    void setData(String data);
    void setUser(UserInfoBean user);
}
