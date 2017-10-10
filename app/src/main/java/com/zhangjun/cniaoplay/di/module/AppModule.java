package com.zhangjun.cniaoplay.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/21.
 */

//app级别的module
@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application){
        this.mApplication = application;
    }

    //单例
    @Singleton
    @Provides
    public Application provideApplication(){
        return  mApplication; //application系统级别的不能new ，只能在构造函数中传递过来
    }

    @Singleton
    @Provides
    public Gson provideGson(){
        return  new Gson();
    }

}
