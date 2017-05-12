package com.pen.androidutil;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.pen.androidutil.libs.animation.AnimationFactory;
import com.pen.androidutil.libs.animator.AnimatorHelper;
import com.pen.androidutil.libs.animator.Builder;
import com.pen.androidutil.libs.animator.Interface;

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

    private void animator() {


    }

    private void startScale() {
        ScaleAnimation anim = new ScaleAnimation(1f, 0.5f, 1, 1);
        ScaleAnimation anim2 = new ScaleAnimation(1f, 1f, 1, 0.5f);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(anim);
        set.addAnimation(anim2);
        set.setDuration(500);
        mTvScale.startAnimation(set);
        /*ScaleAnimation animation = AnimationFactory.scale()
                .fromX(1f).toX(0.5f)
                .fromY(1f).toY(0.5f)
                .pivotType(AnimationFactory.POINT_RELATIVE_TO_PARENT)
                .point(0, 0)
                .repeatMode(AnimationFactory.REPEATMODE_REVERSE)
                .duration(1000)
                .start(mTvScale);*/

       /* AlphaAnimation alphaAnimation = AnimationFactory.alpha()
                .fromAlpha(0)
                .toAlpha(1f)
                .build();

        RotateAnimation rotateAnimation = AnimationFactory.rotate()
                .fromDegress(0)
                .toDegress(720)
                .interpolator(new AnticipateInterpolator(2f))
                .pivotType(AnimationFactory.POINT_RELATIVE_TO_SELF)
                .point(0.5f, 0.5f)
                .repeatCount(3)
                .build();

        TranslateAnimation translateAnimation = AnimationFactory.translate()
                .valueType(AnimationFactory.POINT_RELATIVE_TO_SELF)
                .fromX(-1f)
                .toX(1f)
                .build();*/


        /*AnimationSet set = AnimationFactory.set()
                .repeatMode(AnimationFactory.REPEATMODE_RESTART)
                .fillAfter()
                .duration(5000)
                .addAnim(animation)
                .addAnim(alphaAnimation)
                .addAnim(rotateAnimation)
                .addAnim(translateAnimation)
                .start(true, mTvScale, mTvScale2, mTvScale3);*/
    }

}
