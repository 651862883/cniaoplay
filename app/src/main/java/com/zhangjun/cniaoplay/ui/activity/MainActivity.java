package com.zhangjun.cniaoplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zhangjun.cniaoplay.R;
import com.zhangjun.cniaoplay.bean.User;
import com.zhangjun.cniaoplay.common.Constant;
import com.zhangjun.cniaoplay.common.font.Cniao5Font;
import com.zhangjun.cniaoplay.common.imageloader.GlideCircleTransform;
import com.zhangjun.cniaoplay.common.util.ACache;
import com.zhangjun.cniaoplay.di.component.AppComponent;
import com.zhangjun.cniaoplay.ui.adapter.ViewPagerAdapter;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    @Bind(R.id.tool_bar)
    Toolbar mToolBar;
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;


    private View headerView;
    private ImageView mUserHeadView;
    private TextView mTextUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();
       // ButterKnife.bind(this);
        /*initDrawerLayout();
        initTabLayout();*/
    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        //注册RxBus广播
        RxBus.get().register(this);

        initDrawerLayout();
        initTabLayout();
        initUser();
    }

    private void initTabLayout() {
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);//绑定viewpager
        //mTabLayout.addTab();
    }

    private void initDrawerLayout() {
        headerView = mNavigationView.getHeaderView(0);
        mUserHeadView = (ImageView) headerView.findViewById(R.id.img_avatar);
        mUserHeadView.setImageDrawable(new IconicsDrawable(this, Cniao5Font.Icon.cniao_head).colorRes(R.color.white));
        mTextUserName = (TextView) headerView.findViewById(R.id.txt_username);

        mNavigationView.getMenu().findItem(R.id.menu_app_update).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_loop));
        mNavigationView.getMenu().findItem(R.id.menu_download_manager).setIcon(new IconicsDrawable(this, Cniao5Font.Icon.cniao_download));
        mNavigationView.getMenu().findItem(R.id.menu_app_uninstall).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline));
        mNavigationView.getMenu().findItem(R.id.menu_setting).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_gear_outline));
        mNavigationView.getMenu().findItem(R.id.menu_logout).setIcon(new IconicsDrawable(this, Cniao5Font.Icon.cniao_shutdown));

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_logout:
                        logout();
                        break;
                }
                return false;
            }
        });

        //toolbar初始话menu
        mToolBar.inflateMenu(R.menu.toolbar_menu);

        //增加toggle标识
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);
        drawerToggle.syncState();
        mDrawerLayout.addDrawerListener(drawerToggle);
    }

    //退出登录
    private void logout() {
        ACache aCache = ACache.get(this);
        aCache.put(Constant.TOKEN,"");
        aCache.put(Constant.USER,"");

        mUserHeadView.setImageDrawable(new IconicsDrawable(this, Cniao5Font.Icon.cniao_head).colorRes(R.color.white));
        mTextUserName.setText("未登录");
        Toast.makeText(this,"你已经退出登录",Toast.LENGTH_LONG).show();

        //点击头像，登录
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击事件
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

    }

    //广播的消费者
    @Subscribe
    public void getUserBrocast(User user){
        initUserHeadView(user);
    }

    private void initUserHeadView(User user){
        //更新头像
        //ImageLoader.load(user.getLogo_url(),mUserHeadView);
        //圆角头像
        Glide.with(this).load(user.getLogo_url()).transform(new GlideCircleTransform(this)).into(mUserHeadView);
        mTextUserName.setText(user.getUsername());
    }

    private void initUser(){
        Object objectUser = ACache.get(this).getAsObject(Constant.USER);
        if (objectUser == null){
            //点击头像，登录
            headerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击事件
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
            });
        }else {
            User user = (User) objectUser;
            initUserHeadView(user);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        RxBus.get().unregister(this);

    }

}
