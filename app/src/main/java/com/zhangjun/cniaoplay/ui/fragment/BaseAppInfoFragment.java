package com.zhangjun.cniaoplay.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhangjun.cniaoplay.R;
import com.zhangjun.cniaoplay.bean.AppInfo;
import com.zhangjun.cniaoplay.bean.PageBean;
import com.zhangjun.cniaoplay.presenter.AppInfoPresenter;
import com.zhangjun.cniaoplay.presenter.contract.AppInfoContract;
import com.zhangjun.cniaoplay.ui.adapter.AppInfoAdapter;
import com.zhangjun.cniaoplay.ui.widget.DividerItemDecoration;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/9/8.
 */
public abstract class BaseAppInfoFragment extends ProgressFragment<AppInfoPresenter> implements AppInfoContract.AppInfoView,BaseQuickAdapter.RequestLoadMoreListener {
    @Bind(R.id.recycle_view)
    RecyclerView mRecyclerView;

    int page = 0;

    private AppInfoAdapter mAdapter;

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }


    @Override
    public void init() {
        mPresenter.requestData(type(),page);
        initRecyclerView();
    }

    private void initRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration itemDrecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDrecoration);
        //mAdapter = new AppInfoAdapter();
        mAdapter = buildAdater();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //加载更多
                mPresenter.requestData(type(),page);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    abstract AppInfoAdapter buildAdater();
    abstract int type();

    @Override
    public void showResult(PageBean<AppInfo> pageBean) {
        mAdapter.addData(pageBean.getDatas());
        if (pageBean.isHasMore()){
            page++;
        }
        mAdapter.setEnableLoadMore(pageBean.isHasMore());//允许加载更多
    }

    @Override
    public void onLoadMoreComplete() {
        //加载更多完成
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.requestData(type(),page);
    }
}
