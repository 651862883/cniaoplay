package com.zhangjun.cniaoplay.data.http;

import com.zhangjun.cniaoplay.bean.AppInfo;
import com.zhangjun.cniaoplay.bean.BaseBean;
import com.zhangjun.cniaoplay.bean.IndexBean;
import com.zhangjun.cniaoplay.bean.LoginBean;
import com.zhangjun.cniaoplay.bean.PageBean;
import com.zhangjun.cniaoplay.bean.requestbean.LoginRequestBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ivan on 2016/12/30.
 */

public interface ApiService {
    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    @GET("featured")
    public Call<PageBean<AppInfo>> getApps2(@Query("p") String jsonParam);

    @GET("featured2")
    public Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

   /* @POST("login")
    public Observable<BaseBean> login(@Body LoginRequestBean bean);
*/

    @GET("index")
    public  Observable<BaseBean<IndexBean>> index();



    @GET("toplist")
    public  Observable<BaseBean<PageBean<AppInfo>>> topList(@Query("page") int page);

    @GET("game")
    public  Observable<BaseBean<PageBean<AppInfo>>> games(@Query("page") int page);

    @POST("login")
    public Observable<BaseBean<LoginBean>> login(@Body LoginRequestBean param);

}
