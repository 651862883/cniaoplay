package com.zhangjun.cniaoplay.common.rx.subscriber;

import android.content.Context;

import com.zhangjun.cniaoplay.common.util.ProgressDialogHandler;

/**
 * Created by Administrator on 2017/8/30.
 */
public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener{

    //private Context mContext;
    //private ProgressDialog mProgressDialog;
    //private BaseView mBaseView;

    private ProgressDialogHandler mProgressDialogHandler;

   public ProgressDialogSubscriber(Context context){
        super(context);
        mProgressDialogHandler = new ProgressDialogHandler(mContext,true,this);
    }

     /*public ProgressDialogSubscriber(BaseView view, RxErrorHandler rxErrorHandler){
         super(rxErrorHandler);
         mBaseView = view;
    }*/

    @Override
    public void onStart() {
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.showProgressDialog();
        }


    }

    @Override
    public void onCompleted() {
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }
    }

    protected boolean isShowProgressDialog(){
        return true;
    }

   /* private void initProgressDialog(){
        if (mProgressDialog == null){
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("loading.....");
        }
    }*/

    private  void showProgressDialog(){
        //initProgressDialog();
       // mProgressDialog.show();
       // mBaseView.showloading();
        mProgressDialogHandler.showProgressDialog();;
    }

    private void dismissProgressDialog(){
       /* if (mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }*/
        //mBaseView.dismissLoading();

    }

    @Override
    public void onCancelProgress() {
        unsubscribe();
    }
}
