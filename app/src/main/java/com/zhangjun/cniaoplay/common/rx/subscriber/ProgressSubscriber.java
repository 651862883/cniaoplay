package com.zhangjun.cniaoplay.common.rx.subscriber;

import android.content.Context;

import com.zhangjun.cniaoplay.common.exception.BaseException;
import com.zhangjun.cniaoplay.ui.BaseView;

/**
 * Created by Administrator on 2017/8/30.
 */
public abstract class ProgressSubscriber<T> extends ErrorHandlerSubscriber<T> {

   private BaseView mView;
   public ProgressSubscriber(Context context, BaseView baseView){
        super(context);
       this.mView = baseView;
    }

    @Override
    public void onStart() {
        if(isShowProgressDialog()){
            mView.showloading();
        }


    }

    @Override
    public void onCompleted() {
        if(isShowProgressDialog()){
            mView.dismissLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        if(isShowProgressDialog()){
            BaseException exception = mErrorHandler.handlerError(e);
            mView.showError(exception.getDisplayMessage());
        }
    }

    protected boolean isShowProgressDialog(){
        return true;
    }




}
