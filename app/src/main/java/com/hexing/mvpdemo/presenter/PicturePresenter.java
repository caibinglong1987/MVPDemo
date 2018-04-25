package com.hexing.mvpdemo.presenter;

import com.google.gson.reflect.TypeToken;
import com.hexing.libhexbase.activity.BasePresenter;
import com.hexing.libhexbase.tools.GJsonUtil;
import com.hexing.mvpdemo.bean.CommonListResult;
import com.hexing.mvpdemo.bean.PictureBean;
import com.hexing.mvpdemo.model.PictureBusiness;
import com.hexing.mvpdemo.utils.ServerHelper;
import com.hexing.mvpdemo.view.PictureView;


/**
 * @author caibinglong
 *         date 2018/4/25.
 *         desc desc
 */

public class PicturePresenter extends BasePresenter<PictureView> {
    private PictureBusiness business;

    public PicturePresenter(PictureView view) {
        attachView(view);
        business = new PictureBusiness();
    }

    @Override
    public void attachView(PictureView view) {
        super.attachView(view);
    }

    public void getPictureList(int pageSize, int pageIndex) {
        if (getView() != null) {
            getView().showLoading();
        }
        business.getPictureList(pageSize, pageIndex, new ServerHelper.DataLoadListener() {
            @Override
            public void failure(Exception e) {
                if (getView() != null) {
                    getView().hideLoading();
                }
            }

            @Override
            public void success(String result) {
                if (getView() != null) {
                    getView().hideLoading();
                    CommonListResult<PictureBean> list = GJsonUtil.fromJson(result, new TypeToken<CommonListResult<PictureBean>>() {
                    }.getType());
                    getView().showData(list.results);
                }
            }
        });
    }
}
