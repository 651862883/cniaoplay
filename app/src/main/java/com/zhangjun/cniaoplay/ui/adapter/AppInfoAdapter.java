package com.zhangjun.cniaoplay.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangjun.cniaoplay.R;
import com.zhangjun.cniaoplay.bean.AppInfo;
import com.zhangjun.cniaoplay.common.imageloader.ImageLoader;

/**
 * Created by Administrator on 2017/9/6.
 */
public class AppInfoAdapter extends BaseQuickAdapter<AppInfo,BaseViewHolder> {

    private Builder mBuilder;

    private AppInfoAdapter(Builder builder) {
        super(R.layout.template_appinfo);
        this.mBuilder = builder;
        openLoadAnimation();//开启动画
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
        ImageLoader.load(baseImgUrl+item.getIcon(),(ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name,item.getDisplayName())
                .setText(R.id.txt_brief,item.getBriefShow());
        TextView txtViewPostion = helper.getView(R.id.txt_position);
        txtViewPostion.setVisibility(mBuilder.isShowPostion? View.VISIBLE:View.GONE);
        txtViewPostion.setText(item.getPosition()+1+".");

        TextView txtViewCategory = helper.getView(R.id.txt_category);
        txtViewCategory.setVisibility(mBuilder.isShowCategoryName? View.VISIBLE:View.GONE);
        txtViewCategory.setText(item.getLevel1CategoryName());

        TextView txtViewBrief = helper.getView(R.id.txt_brief);
        txtViewBrief.setVisibility(mBuilder.isShowReief? View.VISIBLE:View.GONE);
        txtViewBrief.setText(item.getBriefShow());
    }

    public static  Builder builder(){
        return  new Builder();
    }

    //建造这设计模式
    public  static  class Builder{
        private boolean isShowPostion;
        private boolean isShowCategoryName;
        private boolean isShowReief;

        public Builder showPostion(boolean b){
            this.isShowPostion = b;
            return this;
        }

        public Builder showCategoryName(boolean b){
            this.isShowCategoryName = b;
            return this;
        }

        public Builder showReief(boolean b){
            this.isShowReief = b;
            return this;
        }

        public AppInfoAdapter build(){
            return  new AppInfoAdapter(this);
        }
    }

}
