package com.zhangjun.cniaoplay.ui.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.zhangjun.cniaoplay.R;
import com.zhangjun.cniaoplay.bean.IndexBean;
import com.zhangjun.cniaoplay.di.component.AppComponent;
import com.zhangjun.cniaoplay.di.component.DaggerRecommendComponet;
import com.zhangjun.cniaoplay.di.module.RecommendModule;
import com.zhangjun.cniaoplay.presenter.RecommendPresenter;
import com.zhangjun.cniaoplay.presenter.contract.AppInfoContract;
import com.zhangjun.cniaoplay.ui.adapter.IndexMutilAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/10.
 */
public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements AppInfoContract.View {

    @Bind(R.id.recycle_view)
    RecyclerView mRecycleView;

    //private RecomendAdapter mAdapter;
    private IndexMutilAdapter mAdapter;

    @Inject
    ProgressDialog mProgressDialog;

    //inject只能声明在activity中
    /*@Inject
    AppInfoContract.Presenter mPresenter;*/

    @Override
    public int setLayout() {
        return R.layout.fragment_recomend;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        //注入
        DaggerRecommendComponet.builder()
                .appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void init() {

        initRecycleView();

        mPresenter.requestDatas();
        //mPresenter.reqesutPermission();//请求权限
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void initRecycleView(){
        //设置布局管理器
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void showNodata() {
        Toast.makeText(getActivity(), "no data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(IndexBean datas) {
        mAdapter = new IndexMutilAdapter(getActivity());
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setData(datas);
    }


   /* public void showResult(List<AppInfo> datas) {
        initRecycleView(datas);
    }
*/

    //实现授权接口方法
    @Override
    public void onRequestPermisssionSuccess() {
        mPresenter.requestDatas();
    }

    @Override
    public void onRequestPermissError() {
        Toast.makeText(getActivity(),"你已拒绝授权",Toast.LENGTH_LONG).show();
    }


    //空，点击重新请求
    @Override
    public void onEmptyViewClick() {
        mPresenter.requestDatas();
    }
}
