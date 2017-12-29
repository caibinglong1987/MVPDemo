package com.base.web.okhttp;

import android.util.Log;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author by long
 *         on 16-3-2.
 */
public abstract class AbstractHttpManager {

    protected boolean isDebug = true;
    protected final String TAG = AbstractHttpManager.class.getName();

    /**
     * 模型设置
     */
    public interface Method {
        int GET = 0;
        int POST = 1;
    }

    public String getRequest(String url, Map<String, String> params) {
        return null;
    }


    public String postRequest(String url, Map<String, String> params) {
        return null;
    }

    public abstract void request(int method, String url, Map<String, String> params, HttpBusinessCallback callback);

    public abstract void getRequest(String url, HttpBusinessCallback callback);

    public abstract void postJsonRequest(String url, JSONObject requestJson, int hashCodeTag, final HttpBusinessCallback callback);

    public abstract void postJsonRequest(String url, JSONObject requestJson, int hashCodeTag, int timeout, final HttpBusinessCallback callback);

    protected String restructureURL(int method, String url, Map<String, String> params) {
        if (method == Method.GET && params != null) {
            url = url + "?" + encodeParameters(params);
        }
        printLog(TAG, url);
        return url;
    }

    /**
     * 参数的键和值进行组装
     *
     * @param params 参数
     * @return 结果
     */
    private String encodeParameters(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        StringBuilder encodedParams = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            encodedParams.append(entry.getKey());
            encodedParams.append('=');
            encodedParams.append(entry.getValue());
            encodedParams.append('&');
        }
        String result = encodedParams.toString();
        printLog(TAG, result);
        return result.substring(0, result.length() - 1);
    }

    private void printLog(String tag, String log) {
        if (isDebug) {
            Log.e(tag, log);
        }
    }
}
