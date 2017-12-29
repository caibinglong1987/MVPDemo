package com.hexing.mvpdemo.presenter;

import com.google.gson.reflect.TypeToken;
import com.hexing.mvpdemo.bean.CommonListResult;
import com.hexing.mvpdemo.bean.StudentBean;
import com.hexing.mvpdemo.presenter.contract.StudentContract;
import com.hexing.mvpdemo.utils.JsonUtil;
import com.hexing.mvpdemo.utils.ServerHelper;


/**
 * @author by HEC271
 *         on 2017/12/28.
 */

public class StudentPresenter implements StudentContract.Presenter, ServerHelper.DataLoadListener {

    private StudentContract.View uiView;
    private ServerHelper serverHelper;

    public StudentPresenter(StudentContract.View view) {
        this.uiView = view;
        this.uiView.setPresenter(this);
        serverHelper = new ServerHelper();
        serverHelper.setListener(this);
    }

    @Override
    public void loadStudent(int pageIndex, int pageSize) {
        uiView.showProgress();
        serverHelper.getStudent(pageIndex, pageSize);
    }

    @Override
    public void start() {

    }

    @Override
    public void failure(Exception e) {
        uiView.hideProgress();
    }

    @Override
    public void success(String result) {
        uiView.hideProgress();
        CommonListResult<StudentBean> listResult = JsonUtil.fromJson(result, new TypeToken<CommonListResult<StudentBean>>() {
        }.getType());
        if (listResult != null) {
            uiView.showStudent(listResult.results);
        }
    }
}
