package com.zhangjun.cniaoplay.ui.fragment;

import com.zhangjun.cniaoplay.di.component.AppComponent;
import com.zhangjun.cniaoplay.di.component.DaggerAppInfoComponet;
import com.zhangjun.cniaoplay.di.module.AppInfoModule;
import com.zhangjun.cniaoplay.presenter.AppInfoPresenter;
import com.zhangjun.cniaoplay.ui.adapter.AppInfoAdapter;

/**
 * Created by Administrator on 2017/3/10.
 */
public class GamesFragment extends BaseAppInfoFragment {

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponet.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this))
                .build().injectGamesFragment(this);
       /* DaggerTopListComponet.builder().appComponent(appComponent).topListModule(new AppInfoModule(this))
                .build().injectGamesFragment(this);*/
    }

    @Override
    AppInfoAdapter buildAdater() {
        return AppInfoAdapter.builder().showPostion(false).showReief(true).showCategoryName(true).build();
    }

    @Override
    int type() {
        return AppInfoPresenter.GAME_LIST;
    }
}
