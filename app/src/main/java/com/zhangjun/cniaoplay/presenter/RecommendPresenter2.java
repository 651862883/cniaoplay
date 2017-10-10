package com.zhangjun.cniaoplay.presenter;

import com.zhangjun.cniaoplay.data.AppInfoModel;
import com.zhangjun.cniaoplay.presenter.contract.AppInfoContract;
import com.zhangjun.cniaoplay.bean.AppInfo;
import com.zhangjun.cniaoplay.bean.PageBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/17.
 * 按功能划分的
 */
public class RecommendPresenter2  {

    private  AppInfoContract.View mView;

    private AppInfoModel mModle;

    public RecommendPresenter2(AppInfoContract.View view){
        mView = view;
        //mModle = new AppInfoModel();
    }


    public void requestDatas() {
        mView.showloading();
        mModle.getApps2(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                /*if (response != null){
                    mView.showResult(response.body().getDatas());
                }else {
                    mView.showNodata();
                }
                mView.dismissLoading();*/
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
               mView.dismissLoading();
                mView.showError(t.getMessage());
            }
        });
    }
}
