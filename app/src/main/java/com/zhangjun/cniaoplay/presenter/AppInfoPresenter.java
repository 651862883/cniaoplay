package com.zhangjun.cniaoplay.presenter;

import com.zhangjun.cniaoplay.bean.AppInfo;
import com.zhangjun.cniaoplay.bean.BaseBean;
import com.zhangjun.cniaoplay.bean.PageBean;
import com.zhangjun.cniaoplay.common.rx.RxHttpReponseCompat;
import com.zhangjun.cniaoplay.common.rx.subscriber.ErrorHandlerSubscriber;
import com.zhangjun.cniaoplay.common.rx.subscriber.ProgressSubscriber;
import com.zhangjun.cniaoplay.data.AppInfoModel;
import com.zhangjun.cniaoplay.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/9/6.
 */
public class AppInfoPresenter extends  BasePresenter<AppInfoModel,AppInfoContract.AppInfoView> {

    public static  final int TOP_LIST=1;
    public static final int GAME_LIST=2;


    @Inject
    public AppInfoPresenter(AppInfoModel appInfoModel, AppInfoContract.AppInfoView topListView) {
        super(appInfoModel, topListView);
    }

    public void requestData(int type,int page){

        Subscriber subscriber = null;

        if (page == 0){
            //第一页
            subscriber = new ProgressSubscriber<PageBean<AppInfo>>(mContext,mView) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }
            };
        }else {
            //加载更多
            subscriber = new ErrorHandlerSubscriber<PageBean<AppInfo>>(mContext) {

                @Override
                public void onCompleted() {
                    mView.onLoadMoreComplete();
                }

                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }
            };
        }

        Observable observable = getObsevable(type,page);
        observable.compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);


        /*mModel.topList(page).compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);*/


       /* mModel.topList(page).compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mContext,mView) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        mView.showResult(appInfoPageBean);
                    }
                });*/
    }


    private Observable<BaseBean<PageBean<AppInfo>>>  getObsevable(int type,int page){
        switch (type){
            case TOP_LIST:
                return mModel.topList(page);
            case GAME_LIST:
                return mModel.gameList(page);
        }
        return  Observable.empty();
    }

}
