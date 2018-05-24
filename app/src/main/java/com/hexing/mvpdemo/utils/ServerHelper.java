package com.hexing.mvpdemo.utils;

import android.util.Log;

import com.cblong.http.HexOkHexHttpManager;
import com.cblong.http.callback.AbsHttpCallback;

import org.json.JSONObject;

/**
 * @author by HEC271
 *         on 2017/12/28.
 */

public class ServerHelper extends AbsHttpCallback {
    private DataLoadListener listener;

    public void getStudent(int pageSize, int pageIndex) {
        String url = "http://gank.io/api/data/福利/" + pageSize + "/" + pageIndex;
        HexOkHexHttpManager.getInstance().getRequest(url, null);
    }

    /**
     * 模拟登录
     * @param json json
     */
    public void login(JSONObject json) {
        String url = "http://gank.io/api/data/福利/50/5";
        HexOkHexHttpManager.getInstance().postRequest(url, json);
    }

    @Override
    public void onSuccess(String response) {
        Log.i("test", "数据获取成功 " + response);
        if (listener != null) {
            listener.success(response);
        }
    }

    @Override
    public void onFailed(final Exception ex) {
        Log.e("失败", ex.getMessage());
        if (listener != null) {
            listener.failure(ex);
        }
    }

    public interface DataLoadListener {
        void failure(Exception e);

        void success(String result);
    }

    public void setListener(DataLoadListener listener) {
        this.listener = listener;
    }
}
