package com.zhangjun.cniaoplay.presenter;

import com.zhangjun.cniaoplay.bean.IndexBean;
import com.zhangjun.cniaoplay.common.rx.RxErrorHandler;
import com.zhangjun.cniaoplay.common.rx.RxHttpReponseCompat;
import com.zhangjun.cniaoplay.common.rx.subscriber.ProgressSubscriber;
import com.zhangjun.cniaoplay.data.AppInfoModel;
import com.zhangjun.cniaoplay.presenter.contract.AppInfoContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/3/17.
 * 按功能划分的
 */
public class RecommendPresenter extends BasePresenter<AppInfoModel,AppInfoContract.View> {

/*    private  AppInfoContract.View mView;

    private AppInfoModel mModle;*/

    private RxErrorHandler mErrorHandler;

    @Inject
    public RecommendPresenter(AppInfoModel model, AppInfoContract.View view) {
        super(model, view);
    }

    /*@Inject
    public RecommendPresenter(AppInfoModel model, AppInfoContract.View view, RxErrorHandler errorHandler) {
        super(model, view);
        mErrorHandler = errorHandler;
    }*/

    //初始化RecommendPresenter
/*    public RecommendPresenter(AppInfoContract.View view,AppInfoModel model){
        mView = view;
        //mModle = new AppInfoModel();
        mModle = model;
    }*/

    /**
     * 请求权限
     * */
/*    public void reqesutPermission(){
        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean){
                            mView.onRequestPermisssionSuccess();
                        }else {
                            mView.onRequestPermissError();
                        }
                    }
                });
    }*/

    public void requestDatas() {
        //mView.showloading();

       /*
       挪到了 BasePresenter 中
       Activity activity = null;
        if (mView instanceof Fragment){
            activity = ((Fragment)mView).getActivity();
        }else {
            activity = (Activity) mView;
        }*/


        //rxjava实现
        //订阅
        mModel.index().compose(RxHttpReponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressSubscriber<IndexBean>(mContext,mView) {
                    @Override
                    public void onNext(IndexBean indexBean) {
                        mView.showResult(indexBean);
                    }
                });


        /*RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .flatMap(new Func1<Boolean, Observable<PageBean<AppInfo>>>() {
                    @Override
                    public Observable<PageBean<AppInfo>> call(Boolean aBoolean) {
                        if (aBoolean){
                            return mModel.getApps().compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult());
                        }else {
                            mView.onRequestPermissError();
                            return  Observable.empty();
                        }
                    }

                }).subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mContext,mView) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null){
                            mView.showResult(appInfoPageBean.getDatas());
                        }else {
                            mView.showNodata();
                        }
                    }
                });*/

       /* mModel.getApps()
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mContext,mView) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null){
                            mView.showResult(appInfoPageBean.getDatas());
                        }else {
                            mView.showNodata();
                        }
                    }
                });*/


       /* mModel.getApps()
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressDialogSubscriber<PageBean<AppInfo>>(mContext) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null){
                            mView.showResult(appInfoPageBean.getDatas());
                        }else {
                            mView.showNodata();
                        }
                    }
                });*/

        /*mModel.getApps()
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressDialogSubscriber<PageBean<AppInfo>>(mView,mErrorHandler) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null){
                            mView.showResult(appInfoPageBean.getDatas());
                        }else {
                            mView.showNodata();
                        }
                    }
                });*/


        /*mModel.getApps()
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressDialogSubscriber<PageBean<AppInfo>>(activity,mErrorHandler) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null){
                            mView.showResult(appInfoPageBean.getDatas());
                        }else {
                            mView.showNodata();
                        }
                    }

                    @Override
                    protected boolean isShowDialog() {
                        return false;
                    }
                });*/

        /*mModel.getApps()
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ErrorHandlerSubscriber<PageBean<AppInfo>>(mErrorHandler) {

                    @Override
                    public void onStart() {
                        mView.showloading();
                    }

                    @Override
                    public void onCompleted() {
                        mView.dismissLoading();
                    }

                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null){
                            mView.showResult(appInfoPageBean.getDatas());
                        }else {
                            mView.showNodata();
                        }
                        mView.dismissLoading();
                    }
                });*/

       /* mModel.getApps()
                //.subscribeOn(Schedulers.io()) //getapps要访问网络，切换到子线程

               // .observeOn(AndroidSchedulers.mainThread())//展示数据放到主线程
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())

                .subscribe(new Subscriber<PageBean<AppInfo>>() {

                    @Override
                    public void onStart() {
                        mView.showloading();
                    }

                    @Override
                    public void onCompleted() {
                       mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoading();
                        // handler error
                    }

                    @Override
                    public void onNext(PageBean<AppInfo> response) {
                        if (response != null){
                            mView.showResult(response.getDatas());
                        }else {
                            mView.showNodata();
                        }
                        mView.dismissLoading();
                    }
                });*/

       /* mModel.getApps2(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if (response != null){
                    mView.showResult(response.body().getDatas());
                }else {
                    mView.showNodata();
                }
                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
               mView.dismissLoading();
                mView.showError(t.getMessage());
            }
        });*/
    }
}
