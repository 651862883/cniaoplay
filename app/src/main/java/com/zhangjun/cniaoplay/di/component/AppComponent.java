package com.zhangjun.cniaoplay.di.component;

import android.app.Application;

import com.zhangjun.cniaoplay.common.rx.RxErrorHandler;
import com.zhangjun.cniaoplay.data.http.ApiService;
import com.zhangjun.cniaoplay.di.module.AppModule;
import com.zhangjun.cniaoplay.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/21.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    public ApiService getApiService();

    public Application getApplication();

    public RxErrorHandler getRxErrorHandler();
}
