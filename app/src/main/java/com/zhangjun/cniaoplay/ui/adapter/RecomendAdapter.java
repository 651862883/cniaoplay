package com.zhangjun.cniaoplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhangjun.cniaoplay.R;
import com.zhangjun.cniaoplay.bean.AppInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/16.
 */
public class RecomendAdapter extends RecyclerView.Adapter<RecomendAdapter.ViewHolder> {



    private List<AppInfo> mDatas;

    private LayoutInflater mLayoutInflater;

    private  Context mContext;

    public RecomendAdapter(Context context, List<AppInfo> datas) {
        this.mDatas = datas;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.template_recomend_app, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo appInfo = mDatas.get(position);
        //holder.
        String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
        Picasso.with(mContext).load(baseImgUrl+appInfo.getIcon()).into(holder.mImgIcon);
        holder.mTextTitle.setText(appInfo.getDisplayName());
        holder.mTextSize.setText((appInfo.getApkSize()/1024/1024)+"");

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_icon)
        ImageView mImgIcon;
        @Bind(R.id.text_title)
        TextView mTextTitle;
        @Bind(R.id.text_size)
        TextView mTextSize;
        @Bind(R.id.btn_dl)
        Button mBtnDl;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
