package com.pen.androidutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.pen.androidutil.libs.animation.AnimationFactory;
import com.pen.androidutil.libs.animation.Builder;

public class MainActivity extends AppCompatActivity {

    private TextView mTvScale;
    private TextView mTvScale2;
    private TextView mTvScale3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTvScale = (TextView) findViewById(R.id.tv_scale);
        mTvScale2 = (TextView) findViewById(R.id.tv_scale2);
        mTvScale3 = (TextView) findViewById(R.id.tv_scale3);
        mTvScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScale();
            }
        });
    }

    private void startScale() {
        ScaleAnimation animation = AnimationFactory.scale()
                .fromX(1f).toX(0.5f)
                .fromY(1f).toY(0.5f)
                .pivotType(AnimationFactory.POINT_RELATIVE_TO_SELF)
                .point(0.5f, 0.5f)
                .repeatMode(AnimationFactory.REPEATMODE_REVERSE)
                .build();

        AlphaAnimation alphaAnimation = AnimationFactory.alpha()
                .fromAlpha(0)
                .toAlpha(1f)
                .build();

        RotateAnimation rotateAnimation = AnimationFactory.rotate()
                .fromDegress(0)
                .toDegress(720)
                .interpolator(new AnticipateInterpolator(2f))
                .pivotType(AnimationFactory.POINT_RELATIVE_TO_SELF)
                .point(0.5f, 0.5f)
                .build();

        TranslateAnimation translateAnimation = AnimationFactory.translate()
                .valueType(AnimationFactory.POINT_RELATIVE_TO_SELF)
                .fromX(-1f)
                .toX(1f)
                .build();


        AnimationSet set = AnimationFactory.set()
                .repeatMode(AnimationFactory.REPEATMODE_RESTART)
                .fillAfter()
                .duration(5000)
                .repeatCount(3)
                /*.addAnim(animation)
                .addAnim(alphaAnimation)*/
                .addAnim(rotateAnimation)
                .addAnim(translateAnimation)
                .start(true, mTvScale, mTvScale2, mTvScale3);
        Interpolator interpolator = set.getInterpolator();
    }

}
