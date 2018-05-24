package com.hexing.mvpdemo.presenter;

import com.google.gson.reflect.TypeToken;
import com.hexing.libhexbase.activity.BasePresenter;
import com.hexing.libhexbase.thread.HexThreadManager;
import com.hexing.libhexbase.tools.GJsonUtil;
import com.hexing.mvpdemo.model.bean.CommonListResult;
import com.hexing.mvpdemo.model.bean.PictureBean;
import com.hexing.mvpdemo.model.PictureBusiness;
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

    public void getPictureList(final int pageSize, final int pageIndex) {
        HexThreadManager.getThreadPollProxy().execute(new Runnable() {
            @Override
            public void run() {
                String result = business.getPictureList(pageSize, pageIndex);
                if (getView() != null) {
                    final CommonListResult<PictureBean> list = GJsonUtil.fromJson(result, new TypeToken<CommonListResult<PictureBean>>() {
                    }.getType());
                    HexThreadManager.runTaskOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            getView().showData(list.results);
                        }
                    });
                }
            }
        });

    }
}
