package com.zhangjun.cniaoplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangjun.cniaoplay.R;
import com.zhangjun.cniaoplay.data.http.ApiService;
import com.zhangjun.cniaoplay.data.http.HttpManager;
import com.zhangjun.cniaoplay.ui.adapter.RecomendAdapter;
import com.zhangjun.cniaoplay.bean.AppInfo;
import com.zhangjun.cniaoplay.bean.PageBean;
import com.zhangjun.cniaoplay.ui.decoration.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/10.
 */
public class RecommendFragment2 extends Fragment   {

    @Bind(R.id.recycle_view)
    RecyclerView mRecycleView;

    private RecomendAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recomend, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void initData(){
        HttpManager manager = new HttpManager();
        ApiService service = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        service.getApps2("{'page':0}").enqueue(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                PageBean<AppInfo> pageBean = response.body();
                List<AppInfo> datas = pageBean.getDatas();
                initRecycleView(datas);
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {

            }
        });

    }

    private void initRecycleView(List<AppInfo> datas){
        //设置布局管理器
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //设置分隔线
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL_LIST));

        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecomendAdapter(getActivity(),datas);
        mRecycleView.setAdapter(mAdapter);

    }

}
