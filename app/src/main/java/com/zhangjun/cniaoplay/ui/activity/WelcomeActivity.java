package com.zhangjun.cniaoplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;
import com.zhangjun.cniaoplay.R;
import com.zhangjun.cniaoplay.common.Constant;
import com.zhangjun.cniaoplay.common.util.ACache;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @Bind(R.id.pathView)
    PathView mPathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        mPathView.getPathAnimator()
                .delay(100).
                duration(5000)
                .interpolator(new AccelerateDecelerateInterpolator())
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
                        jump();
                    }
                })
                .start();

    }

    private void jump() {
        String isShowGuide= ACache.get(this).getAsString(Constant.IS_SHOW_GUIDE);
        if (TextUtils.isEmpty(isShowGuide)){
            startActivity(new Intent(this,GuideActivity.class));
        }else {
            startActivity(new Intent(this,MainActivity.class));
        }

        this.finish();
    }
}
