package com.zhangjun.cniaoplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zhangjun.cniaoplay.R;
import com.zhangjun.cniaoplay.bean.Banner;
import com.zhangjun.cniaoplay.bean.IndexBean;
import com.zhangjun.cniaoplay.common.imageloader.ImageLoader;
import com.zhangjun.cniaoplay.ui.widget.BannerLayout;
import com.zhangjun.cniaoplay.ui.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/1.
 */
public class IndexMutilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public static final int TYPE_BANNER=1;
    public static final int TYPE_ICON=2;
    public static final int TYPE_APPS=3;
    public static final int TYPE_GAMES=4;

    private LayoutInflater mInflater;
    private IndexBean mIndexBean;
    private Context mContext;

    public IndexMutilAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setData(IndexBean indexBean){
        this.mIndexBean = indexBean;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_BANNER;
        }else  if (position == 1){
            return TYPE_ICON;
        }else if (position == 2){
            return TYPE_APPS;
        }else if (position == 3){
            return TYPE_GAMES;
        }
        return 0;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType ==TYPE_BANNER){
            return new BannerViewHolder(mInflater.inflate(R.layout.template_banner,parent,false));
        }else if (viewType == TYPE_ICON){
            return new NavIconViewHolder(mInflater.inflate(R.layout.template_nav_icon,parent,false));
        }else if (viewType == TYPE_APPS){
            //嵌套使用的时候，第二个参数为空
            return new AppViewHolder(mInflater.inflate(R.layout.template_recyleview_with_title,null,false),TYPE_APPS);
        }else if (viewType == TYPE_GAMES){
            return new AppViewHolder(mInflater.inflate(R.layout.template_recyleview_with_title,null,false),TYPE_GAMES);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //显示数据
        if (position == 0){
            //banner
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;

            final List<Banner> banners =  mIndexBean.getBanners();
            List<String> urls = new ArrayList<String>(banners.size());
            for (Banner banner:banners){
                urls.add(banner.getThumbnail());
            }
            bannerViewHolder.mBanner.setViewUrls(urls);
            bannerViewHolder.mBanner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {
                   // banners.get(position);
                }
            });
        }else if (position == 1){
            //nav
            NavIconViewHolder navIconViewHolder = (NavIconViewHolder) holder;
            navIconViewHolder.mLayoutHotApp.setOnClickListener(this);
            navIconViewHolder.mLayoutHotGame.setOnClickListener(this);
            navIconViewHolder.mLayoutHotSubject.setOnClickListener(this);
        }else {
            AppViewHolder appViewHolder = (AppViewHolder) holder;

            //AppInfoAdapter appInfoAdatper = new AppInfoAdapter();
            AppInfoAdapter appInfoAdatper = AppInfoAdapter.builder().showPostion(false).showReief(true).showCategoryName(false).build();

            if (appViewHolder.type == TYPE_APPS){
                appInfoAdatper.addData(mIndexBean.getRecommendApps());
                appViewHolder.mText.setText("热门应用");
            }else {
                appInfoAdatper.addData(mIndexBean.getRecommendGames());
                appViewHolder.mText.setText("热门游戏");
            }
            appViewHolder.mRecyclerView.setAdapter(appInfoAdatper);
            appViewHolder.mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void onClick(View v) {

    }

    class  BannerViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.banner)
        BannerLayout mBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mBanner.setImageLoader(new ImgLoad());

        }
    }

    class  NavIconViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.layout_hot_app)
        LinearLayout mLayoutHotApp;
        @Bind(R.id.layout_hot_game)
        LinearLayout mLayoutHotGame;
        @Bind(R.id.layout_hot_subject)
        LinearLayout mLayoutHotSubject;

        NavIconViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class AppViewHolder extends  RecyclerView.ViewHolder{
        @Bind(R.id.text)
        TextView mText;
        @Bind(R.id.recycler_view)
        RecyclerView mRecyclerView;
        int type;
        public AppViewHolder(View itemView,int type) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.type = type;
            initRecyclerView();
        }

        private void initRecyclerView() {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext){
                @Override
                public boolean canScrollVertically() {
                    return false;//原来还可以这么操作，666，不用固定布局了
                }
            });
            DividerItemDecoration itemDrecoration = new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL_LIST);
            mRecyclerView.addItemDecoration(itemDrecoration);
        }
    }



    class  ImgLoad implements BannerLayout.ImageLoader{
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoader.load(path,imageView);
        }
    }

}
