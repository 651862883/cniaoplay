package com.zhangjun.cniaoplay.di.module;

import android.app.ProgressDialog;

import com.zhangjun.cniaoplay.data.AppInfoModel;
import com.zhangjun.cniaoplay.data.http.ApiService;
import com.zhangjun.cniaoplay.presenter.contract.AppInfoContract;
import com.zhangjun.cniaoplay.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/21.
 */

//提供对象相当于new A()
@Module
public class RecommendModule {

    private  AppInfoContract.View mView;
    public RecommendModule(AppInfoContract.View view){
        mView = view;
    }

    //提供实例化方法,已经转到构造函数中
   /* @Provides
    public AppInfoContract.Presenter providePresenter(AppInfoContract.View view,AppInfoModel model){
        return  new RecommendPresenter(view,model);
    }*/

    @Provides
    public AppInfoContract.View provideView() {
        return mView;
    }

    //获取数据库访问层代码
    @Provides
    public AppInfoModel provideModel(ApiService apiService){
        return  new AppInfoModel(apiService);
    }

    /*@Provides
    public RecomendAdapter providApter(){
        return  null;
        //return  new RecomendAdapter();
    }*/

    @Provides
    public ProgressDialog provideProgressDialog(AppInfoContract.View view){
        return  new ProgressDialog(((RecommendFragment)view).getActivity());
    }



}
