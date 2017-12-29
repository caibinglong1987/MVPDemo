package com.hexing.mvpdemo.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author by HEC271
 *         on 2017/12/28.
 */

public class BaseBean implements Serializable {
    @SerializedName("error")
    private String error;

    @SerializedName("code")
    private String code;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
