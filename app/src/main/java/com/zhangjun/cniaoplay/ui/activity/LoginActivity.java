package com.zhangjun.cniaoplay.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhangjun.cniaoplay.R;
import com.zhangjun.cniaoplay.common.util.DeviceUtils;
import com.zhangjun.cniaoplay.di.component.AppComponent;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.button)
    Button mButton;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

    }

    @OnClick(R.id.button)
    public void onClick() {

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


       /* //检查
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            //没有授权
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},1000);//授权
        }else {
            //已经授权
            String imei = DeviceUtils.getIMEI(this);
            Toast.makeText(this,"imei="+imei,Toast.LENGTH_LONG).show();
        }*/
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
    }
}
