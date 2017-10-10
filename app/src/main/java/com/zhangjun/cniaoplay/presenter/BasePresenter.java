package com.zhangjun.cniaoplay.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.zhangjun.cniaoplay.ui.BaseView;

/**
 * Created by Administrator on 2017/3/17.
 */
public class BasePresenter<M,V extends BaseView> {
    protected M mModel;
    protected V mView;

    protected Context mContext;

    public BasePresenter(M m,V v){
        this.mModel = m;
        this.mView=v;
        initContext();
    }

    private void initContext(){
        if (mView instanceof Fragment){
            mContext = ((Fragment)mView).getActivity();
        }else {
            mContext = (Activity) mView;
        }
    }
}
