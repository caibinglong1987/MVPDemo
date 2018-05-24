package com.hexing.mvpdemo.model.bean;

import java.util.List;

/**
 * @author by HEC271
 *         on 2017/12/29.
 */
public class CommonListResult<T> extends BaseBean {
    public List<T> data;
    public List<T> results;

    public boolean hasData() {
        return data != null && data.size() > 0;
    }
}
