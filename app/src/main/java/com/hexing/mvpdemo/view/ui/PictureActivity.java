package com.hexing.mvpdemo.view.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.hexing.libhexbase.activity.HexMVPBaseActivity;
import com.hexing.mvpdemo.R;
import com.hexing.mvpdemo.adapter.PictureAdapter;
import com.hexing.mvpdemo.bean.PictureBean;
import com.hexing.mvpdemo.presenter.PicturePresenter;
import com.hexing.mvpdemo.view.LoadingDialog;
import com.hexing.mvpdemo.view.PictureView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caibinglong
 *         date 2018/4/25.
 *         desc desc
 */

public class PictureActivity extends HexMVPBaseActivity<PicturePresenter> implements PictureView, SwipeRefreshLayout.OnRefreshListener {
    private PictureAdapter adapter;
    private List<PictureBean> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_beauty);
        initView();
    }

    private void initView() {
        refreshLayout = findViewById(R.id.tabSwipeRefresh);
        recyclerView = findViewById(R.id.tabRecyler);
        adapter = new PictureAdapter(dataList, getApplicationContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //设置下拉刷新
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        refreshLayout.setOnRefreshListener(this);
        //第一次进入界面时候加载进度条
        refreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        recyclerView.setLayoutManager(linearLayoutManager);


        //设置布局管理器

        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));
        //设置adapter
        recyclerView.setAdapter(adapter);
        mvpPresenter.getPictureList(20, 0);
    }

    @Override
    protected PicturePresenter createPresenter() {
        return new PicturePresenter(this);
    }

    @Override
    public void showData(List<PictureBean> list) {
        dataList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        LoadingDialog.showSysLoadingDialog(this, "Loading", true);
    }

    @Override
    public void hideLoading() {
        LoadingDialog.cancelLoadingDialog();
    }

    @Override
    public void onRefresh() {

    }
}
