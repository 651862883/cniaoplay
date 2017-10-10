package com.zhangjun.cniaoplay.di.component;

import com.zhangjun.cniaoplay.di.FragmentScope;
import com.zhangjun.cniaoplay.di.module.AppInfoModule;
import com.zhangjun.cniaoplay.ui.fragment.GamesFragment;
import com.zhangjun.cniaoplay.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/21.
 */

//连接器,关联module和fragment
@FragmentScope//当引用的component为singleton的时候，当前Component一定要是singleton并且scope不能比引用的Component的级别高
@Component(modules = {AppInfoModule.class},dependencies = AppComponent.class)
public interface AppInfoComponet {

    //关联fragment(container)
    void injectTopListFragment(TopListFragment fragment);

    void injectGamesFragment(GamesFragment fragment);
}
