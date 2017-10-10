package com.zhangjun.cniaoplay.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.zhangjun.cniaoplay.AppApplication;
import com.zhangjun.cniaoplay.di.component.AppComponent;
import com.zhangjun.cniaoplay.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/22.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private AppApplication mApplication;

    @Inject
    T mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //icon
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        ButterKnife.bind(this);
        mApplication = (AppApplication) getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

   /* protected void startActivity(Class cla){
        this.startActivity(new Intent(this,cla));
    }*/

    public abstract int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void init();

}
