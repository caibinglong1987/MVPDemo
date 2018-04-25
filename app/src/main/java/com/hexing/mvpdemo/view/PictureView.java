package com.hexing.mvpdemo.view;

import com.hexing.libhexbase.view.BaseView;
import com.hexing.mvpdemo.bean.PictureBean;

import java.util.List;

/**
 * @author caibinglong
 *         date 2018/4/25.
 *         desc desc
 */

public interface PictureView extends BaseView {
    void showData(List<PictureBean> list);
}
