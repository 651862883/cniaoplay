package com.zhangjun.cniaoplay.di.component;

import com.zhangjun.cniaoplay.di.FragmentScope;
import com.zhangjun.cniaoplay.di.module.RecommendModule;
import com.zhangjun.cniaoplay.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/21.
 */

//连接器,关联module和fragment
@FragmentScope//当引用的component为singleton的时候，当前Component一定要是singleton并且scope不能比引用的Component的级别高
@Component(modules = {RecommendModule.class},dependencies = AppComponent.class)
public interface RecommendComponet {

    //关联fragment(container)
    void inject(RecommendFragment fragment);
}
