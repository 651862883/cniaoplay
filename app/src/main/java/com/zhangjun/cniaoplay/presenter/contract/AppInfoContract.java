package com.zhangjun.cniaoplay.presenter.contract;

import com.zhangjun.cniaoplay.bean.AppInfo;
import com.zhangjun.cniaoplay.bean.IndexBean;
import com.zhangjun.cniaoplay.bean.PageBean;
import com.zhangjun.cniaoplay.ui.BaseView;

/**
 * Created by Administrator on 2017/3/17.
 */
public interface AppInfoContract {

    interface View extends BaseView{
        void showNodata();

        //void showResult(List<AppInfo> datas);
        void showResult(IndexBean datas);
        void onRequestPermisssionSuccess();
        void onRequestPermissError();

    };

    interface AppInfoView extends BaseView{
        void showResult(PageBean<AppInfo> page);

        void onLoadMoreComplete();//加载更多
    }

   /* interface  Presenter extends BasePresenter{
        public void requestDatas();
    }*/

}
