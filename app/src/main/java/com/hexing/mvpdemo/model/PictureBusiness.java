package com.hexing.mvpdemo.model;

import com.hexing.mvpdemo.utils.ServerHelper;

/**
 * @author caibinglong
 *         date 2018/4/25.
 *         desc desc
 */

public class PictureBusiness {
    private ServerHelper serverHelper;

    public PictureBusiness() {
        serverHelper = new ServerHelper();
    }

    public String getPictureList(int pageSize, int pageIndex) {
        return serverHelper.getStudent(pageSize, pageIndex);
    }
}
