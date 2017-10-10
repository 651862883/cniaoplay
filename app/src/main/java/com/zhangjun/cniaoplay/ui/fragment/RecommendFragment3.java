package com.zhangjun.cniaoplay.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhangjun.cniaoplay.R;
import com.zhangjun.cniaoplay.bean.IndexBean;
import com.zhangjun.cniaoplay.presenter.RecommendPresenter;
import com.zhangjun.cniaoplay.presenter.contract.AppInfoContract;
import com.zhangjun.cniaoplay.ui.adapter.RecomendAdapter;
import com.zhangjun.cniaoplay.bean.AppInfo;
import com.zhangjun.cniaoplay.ui.decoration.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/10.
 */
public class RecommendFragment3 extends Fragment implements AppInfoContract.View {

    @Bind(R.id.recycle_view)
    RecyclerView mRecycleView;

    private RecomendAdapter mAdapter;

    private ProgressDialog mProgressDialog;

    private RecommendPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recomend, container, false);
      /*  ButterKnife.bind(this, view);
        mProgressDialog = new ProgressDialog(getActivity());
        mPresenter = new RecommendPresenter(this);
        initData();*/
        return view;
    }

    private void initData() {
        mPresenter.requestDatas();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void initRecycleView(List<AppInfo> datas){
        //设置布局管理器
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //设置分隔线
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL_LIST));

        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecomendAdapter(getActivity(),datas);
        mRecycleView.setAdapter(mAdapter);

    }


    @Override
    public void showNodata() {
        Toast.makeText(getActivity(), "no data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(IndexBean datas) {

    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), "服务器出错："+msg, Toast.LENGTH_SHORT).show();
    }

    //@Override
    public void showResult(List<AppInfo> datas) {
        initRecycleView(datas);
    }

    @Override
    public void onRequestPermisssionSuccess() {

    }

    @Override
    public void onRequestPermissError() {

    }

    @Override
    public void showloading() {
        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }
}
