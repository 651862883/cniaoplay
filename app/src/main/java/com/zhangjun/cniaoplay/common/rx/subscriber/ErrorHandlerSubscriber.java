package com.zhangjun.cniaoplay.common.rx.subscriber;

import android.content.Context;

import com.zhangjun.cniaoplay.common.exception.BaseException;
import com.zhangjun.cniaoplay.common.rx.RxErrorHandler;

/**
 * Created by Administrator on 2017/8/23.
 */
public abstract class ErrorHandlerSubscriber<T> extends  DefultSubScriber<T> {
    protected Context mContext;
    protected RxErrorHandler mErrorHandler;
    public ErrorHandlerSubscriber(Context  context){
        this.mContext = context;
        mErrorHandler = new RxErrorHandler(mContext);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        BaseException exception = mErrorHandler.handlerError(e);
        mErrorHandler.showErrorMessage(exception);
    }
}
