package com.zhangjun.cniaoplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhangjun.cniaoplay.bean.FragmentInfo;
import com.zhangjun.cniaoplay.ui.fragment.CategoryFragment;
import com.zhangjun.cniaoplay.ui.fragment.GamesFragment;
import com.zhangjun.cniaoplay.ui.fragment.TopListFragment;
import com.zhangjun.cniaoplay.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<FragmentInfo> mFragments = new ArrayList<FragmentInfo>(4);


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments(){
        mFragments.add(new FragmentInfo("推荐",RecommendFragment.class));
        mFragments.add(new FragmentInfo("排行",TopListFragment.class));
        mFragments.add(new FragmentInfo("游戏",GamesFragment.class));
        mFragments.add(new FragmentInfo("分类",CategoryFragment.class));
    }


    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment)mFragments.get(position).getFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return  null;
        /*switch (position){
            case 0:
                fragment = new RecommendFragment();
                break;
            case 1:
                fragment = new TopListFragment();
                break;
            case 2:
                fragment = new GamesFragment();
                break;
            case 3:
                fragment = new CategoryFragment();
                break;
        }
        return mFragments.get(position);*/
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
