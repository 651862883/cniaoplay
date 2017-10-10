package com.zhangjun.cniaoplay.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zhangjun.cniaoplay.R;

public class SvgTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg_test);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"iconfont.ttf");
        TextView textView = (TextView) findViewById(R.id.text_icon);
        textView.setTypeface(typeface);
    }
}
