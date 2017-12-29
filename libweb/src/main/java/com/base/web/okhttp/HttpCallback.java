package com.base.web.okhttp;

import java.util.Map;

/**
 * @author by long
 *         on 16-3-2.
 */
public interface HttpCallback {

    /****
     * 失败处理
     ***/
    void onFailure(Map<String, ?> errorMap);

    void onFailure(Exception ex);

    /***
     * 成功返回
     ***/
    void onSucceed(String response);
}
