package com.zhangjun.cniaoplay.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangjun.cniaoplay.AppApplication;
import com.zhangjun.cniaoplay.di.component.AppComponent;
import com.zhangjun.cniaoplay.presenter.BasePresenter;
import com.zhangjun.cniaoplay.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/22.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    private AppApplication mApplication;

    private  ProgressDialog mProgressDialog;

    @Inject
    public T mPresenter;

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayout(), container, false);
        ButterKnife.bind(this, mRootView);
        return  mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mApplication = (AppApplication) getActivity().getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public abstract int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void init();

   /* @Override
    public void showloading() {
        if (mProgressDialog == null){
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("loading.....");
        }
        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }*/

    @Override
    public void showloading() {
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    public void dismissLoading() {
    }
}
