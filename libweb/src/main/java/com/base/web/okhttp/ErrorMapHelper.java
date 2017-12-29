package com.base.web.okhttp;

import java.util.Map;

import okhttp3.Call;

/**
 * @author by HEC271
 *         on 2017/12/29.
 */

public class ErrorMapHelper {
    public static final String KEY_CALL = "key_call";
    public static final String KEY_EXCEPTION = "key_exception";

    public static Call getCallFromErrorMap(Map<String, ?> errorMap) {
        if (errorMap == null) {
            return null;
        }
        return (Call) errorMap.get(KEY_CALL);
    }

    public static Exception getExceptionFromErrorMap(Map<String, ?> errorMap) {
        if (errorMap == null) {
            return null;
        }
        return (Exception) errorMap.get(KEY_EXCEPTION);
    }
}
