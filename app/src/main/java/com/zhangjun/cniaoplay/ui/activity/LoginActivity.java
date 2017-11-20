package com.zhangjun.cniaoplay.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.zhangjun.cniaoplay.R;
import com.zhangjun.cniaoplay.bean.LoginBean;
import com.zhangjun.cniaoplay.di.component.AppComponent;
import com.zhangjun.cniaoplay.di.component.DaggerLoginComponet;
import com.zhangjun.cniaoplay.di.module.LoginModule;
import com.zhangjun.cniaoplay.presenter.LoginPresenter;
import com.zhangjun.cniaoplay.presenter.contract.LoginContract;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {


    @Bind(R.id.tool_bar)
    Toolbar mToolBar;

    @Bind(R.id.txt_mobi)
    EditText mTxtMobi;

    @Bind(R.id.txt_password)
    EditText mTxtPassword;

    @Bind(R.id.btn_login)
    Button mBtnLogin;

    @Bind(R.id.view_password_wrapper)
    TextInputLayout mViewPasswordWrapper;

    @Bind(R.id.view_mobi_wrapper)
    TextInputLayout mViewMobiWrapper;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponet.builder().appComponent(appComponent).loginModule(new LoginModule(this))
                .build().inject(this);
    }

    @Override
    public void init() {
        initView();
    }

    private void initView() {
        //RXbinding 类似于事件绑定
        Observable<CharSequence> obMobi = RxTextView.textChanges(mTxtMobi);
        Observable<CharSequence> obPassword = RxTextView.textChanges(mTxtPassword);

        Observable.combineLatest(obMobi, obPassword, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence mobi, CharSequence pwd) {
                return isPhoneValid(mobi.toString()) && isPasswordValid(pwd.toString());
            }
        }).subscribe(new Action1<Boolean>() {
            //订阅它
            @Override
            public void call(Boolean aBoolean) {
                RxView.enabled(mBtnLogin).call(aBoolean);//RxBinding设置某个控件是否可用
            }
        });

        //btn点击事件
        RxView.clicks(mBtnLogin).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                //订阅
                mPresenter.login(mTxtMobi.getText().toString().trim(),mTxtPassword.getText().toString().trim());
            }
        });

    }

    private boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }


    @OnClick(R.id.btn_login)
    public void onClick() {
    }


    @Override
    public void checkPhoneError() {
        mViewMobiWrapper.setError("手机号码输入错误");
    }

    @Override
    public void checkPhoneSuccess() {
        mViewMobiWrapper.setError("");
        mViewMobiWrapper.setErrorEnabled(false);
    }

    @Override
    public void loginSuccess(LoginBean bean) {
        //Toast.makeText(this,"登录成功！",Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void showloading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(String msg) {

    }


  /*  public void onClick() {

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean){

                        }else {

                        }
                    }
                });


       *//* //检查
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            //没有授权
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},1000);//授权
        }else {
            //已经授权
            String imei = DeviceUtils.getIMEI(this);
            Toast.makeText(this,"imei="+imei,Toast.LENGTH_LONG).show();
        }*//*
    }

    //授权回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //授权成功
                String imei = DeviceUtils.getIMEI(this);
                Toast.makeText(this,"imei="+imei,Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,"用户授权失败",Toast.LENGTH_LONG).show();
            }
        }
    }*/
}
