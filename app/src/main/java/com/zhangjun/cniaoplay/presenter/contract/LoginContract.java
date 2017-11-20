package com.zhangjun.cniaoplay.presenter.contract;

import com.zhangjun.cniaoplay.bean.BaseBean;
import com.zhangjun.cniaoplay.bean.LoginBean;
import com.zhangjun.cniaoplay.ui.BaseView;

import rx.Observable;

/**
 *
 * Created by Administrator on 2017-11-13.
 */
public interface LoginContract {

   public  interface  ILoginModel{
        public Observable<BaseBean<LoginBean>> login(String phone, String pwd);
   }

    public  interface LoginView extends BaseView{
        public void checkPhoneError();
        void  checkPhoneSuccess();
        void  loginSuccess(LoginBean bean);
       // void  loginError(String msg); 错误toast弹出，没有业务操作
    }

}
