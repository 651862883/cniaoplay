package com.zhangjun.cniaoplay.di.module;

import com.zhangjun.cniaoplay.data.AppInfoModel;
import com.zhangjun.cniaoplay.data.http.ApiService;
import com.zhangjun.cniaoplay.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/21.
 */

//提供对象相当于new A()
@Module
public class AppInfoModule {

    private AppInfoContract.AppInfoView mView;
    public AppInfoModule(AppInfoContract.AppInfoView view){
        mView = view;
    }

    //提供实例化方法,已经转到构造函数中
   /* @Provides
    public AppInfoContract.Presenter providePresenter(AppInfoContract.View view,AppInfoModel model){
        return  new RecommendPresenter(view,model);
    }*/

    @Provides
    public AppInfoContract.AppInfoView provideView() {
        return mView;
    }

    //获取数据库访问层代码
    @Provides
    public AppInfoModel provideModel(ApiService apiService){
        return  new AppInfoModel(apiService);
    }


}
