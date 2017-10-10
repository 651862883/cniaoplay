package com.zhangjun.cniaoplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public class GuidFragmentAdapter   extends FragmentPagerAdapter {
    List<Fragment> mFragments;

    public void setFragments(List<Fragment> fragments) {
        if (fragments == null){
            mFragments = new ArrayList<Fragment>();
        }else {
            mFragments = fragments;
        }
    }

    public GuidFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
