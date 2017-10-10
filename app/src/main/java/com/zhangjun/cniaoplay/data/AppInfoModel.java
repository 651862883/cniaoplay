package com.zhangjun.cniaoplay.data;

import com.zhangjun.cniaoplay.bean.AppInfo;
import com.zhangjun.cniaoplay.bean.BaseBean;
import com.zhangjun.cniaoplay.bean.IndexBean;
import com.zhangjun.cniaoplay.bean.PageBean;
import com.zhangjun.cniaoplay.data.http.ApiService;

import retrofit2.Callback;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/17.
 * model层，访问数据库
 */
public class AppInfoModel {

    private ApiService mApiService;

    public AppInfoModel(ApiService apiService){
        mApiService = apiService;
    }

    public void getApps2(Callback<PageBean<AppInfo>> callback){
       // HttpManager manager = new HttpManager();
       // ApiService service = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        mApiService.getApps2("{'page':0}").enqueue(callback);
    }

    /**
     * rxJava 方式来实现
     * @return
     */
    public Observable<BaseBean<PageBean<AppInfo>>> getApps(){
        // HttpManager manager = new HttpManager();
        // ApiService service = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        // mApiService.getApps("{'page':0}").enqueue(callback);
        return mApiService.getApps("{'page':0}");
    }

    public Observable<BaseBean<IndexBean>> index(){
        return mApiService.index();
    }

    public  Observable<BaseBean<PageBean<AppInfo>>> topList(int page){
        return  mApiService.topList(page);
    }


    public  Observable<BaseBean<PageBean<AppInfo>>> gameList(int page){
        return  mApiService.games(page);
    }


}
