package com.zhangjun.cniaoplay.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.zhangjun.cniaoplay.common.exception.ApiException;
import com.zhangjun.cniaoplay.common.exception.BaseException;
import com.zhangjun.cniaoplay.common.exception.ErrorMessageFactory;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Administrator on 2017/8/30.
 */
public class RxErrorHandler {

    private Context mContext;

    public RxErrorHandler(Context context){
        mContext = context;
    }

    public BaseException handlerError(Throwable e){
        BaseException exception = new BaseException();
        //错误处理
        if (e instanceof ApiException){
            exception.setCode(((ApiException)e).getCode());
        }else if (e instanceof SocketException){
            exception.setCode(BaseException.SOCKET_ERROR);
        }else if (e instanceof HttpException){
            exception.setCode(BaseException.SOCKET_ERROR);
        }else if (e instanceof SocketTimeoutException){
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        }else {
            exception.setCode(BaseException.UNKNOWN_ERROR);
        }
        exception.setDisplayMessage(ErrorMessageFactory.create(mContext,exception.getCode()));
        return  exception;
    }

    public  void showErrorMessage(BaseException e){
        Toast.makeText(mContext,e.getDisplayMessage(),Toast.LENGTH_LONG).show();
    }

}
