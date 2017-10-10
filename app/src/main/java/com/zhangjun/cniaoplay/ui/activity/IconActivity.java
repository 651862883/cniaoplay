package com.zhangjun.cniaoplay.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.zhangjun.cniaoplay.R;
import com.zhangjun.cniaoplay.typeface.Cniao5Font;

/**
 * 好处1.减少app体积，瘦身
 * 2.任意屏幕的适配
 * 3.非常灵活，可以更改任意的颜色
 * */
public class IconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon);

       Drawable drawable = new IconicsDrawable(this)
                .icon(Cniao5Font.Icon.ico_delete)
                .color(Color.RED)
                .sizeDp(24);

        ((ImageView) findViewById(R.id.imageView)).setImageDrawable(drawable);

    }
}
