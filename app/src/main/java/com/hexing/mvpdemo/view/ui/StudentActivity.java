package com.hexing.mvpdemo.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hexing.mvpdemo.R;
import com.hexing.mvpdemo.fragment.StudentFragment;
import com.hexing.mvpdemo.presenter.StudentPresenter;
import com.hexing.mvpdemo.utils.ActivityUtils;


/**
 * @author by HEC271
 *         on 2017/12/28.
 */

public class StudentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);

        StudentFragment fragment  = StudentFragment.newInstance();

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment, R.id.container);

        //设置presenter
        new StudentPresenter(fragment);
    }


}
