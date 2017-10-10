package com.zhangjun.cniaoplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.zhangjun.cniaoplay.R;
import com.zhangjun.cniaoplay.common.Constant;
import com.zhangjun.cniaoplay.common.util.ACache;
import com.zhangjun.cniaoplay.ui.adapter.GuidFragmentAdapter;
import com.zhangjun.cniaoplay.ui.fragment.GuideFragment;
import com.zhangjun.cniaoplay.ui.widget.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.btn_enter)
    Button mBtnEnter;
    @Bind(R.id.indicator)
    CircleIndicator mIndicator;
    @Bind(R.id.activity_guide)
    RelativeLayout mActivityGuide;

    private GuidFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(GuideFragment.newInstance(R.drawable.guide_1, R.color.color_bg_guide1, R.string.guide_1));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_2, R.color.color_bg_guide2, R.string.guide_2));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_3, R.color.color_bg_guide3, R.string.guide_3));

        mAdapter = new GuidFragmentAdapter(getSupportFragmentManager());
        mAdapter.setFragments(fragments);
        mViewpager.setCurrentItem(0);
        mViewpager.setOffscreenPageLimit(mAdapter.getCount());
        mViewpager.setAdapter(mAdapter);
        mViewpager.addOnPageChangeListener(this);
        mIndicator.setViewPager(mViewpager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mBtnEnter.setVisibility((position == mAdapter.getCount() - 1) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @OnClick(R.id.btn_enter)
    public void onClick() {
        ACache.get(this).put(Constant.IS_SHOW_GUIDE,"0");
        startActivity(new Intent(this,MainActivity.class));
        this.finish();
    }
}
