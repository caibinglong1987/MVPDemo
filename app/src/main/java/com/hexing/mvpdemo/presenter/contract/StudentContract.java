package com.hexing.mvpdemo.presenter.contract;

import com.hexing.mvpdemo.bean.StudentBean;
import com.hexing.mvpdemo.presenter.BasePresenter;
import com.hexing.mvpdemo.view.BaseView;

import java.util.List;

/**
 * @author by HEC271
 *         on 2017/12/28.
 */

public interface StudentContract {
    interface Presenter extends BasePresenter {
        void loadStudent(int pageIndex, int pageSize);
    }

    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void showStudent(List<StudentBean> stuList);
    }
}
