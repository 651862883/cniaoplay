package com.zhangjun.cniaoplay;

import android.app.Application;
import android.content.Context;

import com.mikepenz.iconics.Iconics;
import com.zhangjun.cniaoplay.di.component.AppComponent;
import com.zhangjun.cniaoplay.di.component.DaggerAppComponent;
import com.zhangjun.cniaoplay.di.module.AppModule;
import com.zhangjun.cniaoplay.di.module.HttpModule;
import com.zhangjun.cniaoplay.typeface.Cniao5Font;

/**
 * Created by Administrator on 2017/3/16.
 */
public class AppApplication extends Application{

    private AppComponent mAppComponent;

    public static  Application get(Context context){
        return (Application)context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //only required if you add a custom or generic font on your own
        Iconics.init(getApplicationContext());

        //register custom fonts like this (or also provide a font definition file)
        Iconics.registerFont(new Cniao5Font());

        //实例化
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .httpModule(new HttpModule()).build();
    }
}
