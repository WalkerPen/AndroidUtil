package com.pen.androidutil.libs.animation;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Pen on 2017/5/12.
 */

public class Builder {


    private AnimationSet set;

    private OnAnimationStartListener mOnAnimationStartListener;

    private OnAnimationEndListener mOnAnimationEndListener;

    private OnAnimationRepeatListener mOnAnimationRepeatListener;

    public Builder(Animation animation) {
        set = new AnimationSet(true);
        set.addAnimation(animation);
    }

    public Builder duration(int duration) {
        set.setDuration(duration);
        return this;
    }

    public Builder startOffSet(int startOffSet) {
        set.setStartOffset(startOffSet);
        return this;
    }

    public Builder interpolator(Interpolator interpolator) {
        set.setInterpolator(interpolator);
        return this;
    }

    public Builder repeatMode(@AnimationHelper.RepeatMode int repeatMode) {
        set.setRepeatMode(repeatMode);
        return this;
    }

    public Builder repeatCount(int repeatCount) {
        set.setRepeatCount(repeatCount);
        return this;
    }

    public Builder fillAfter() {
        set.setFillAfter(true);
        return this;
    }

    public Builder scale(float from, float to) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(from, to, from, to, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        set.addAnimation(scaleAnimation);
        return this;
    }

    public Builder scale(float from, float to, @AnimationHelper.PivotType int pivotType, float pivotValue) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(from, to, from, to, pivotType, pivotValue, pivotType, pivotValue);
        set.addAnimation(scaleAnimation);
        return this;
    }

    public Builder scaleCenter(float from, float to) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(from, to, from, to,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(scaleAnimation);
        return this;
    }

    public Builder scale(float fromX, float toX, float fromY, float toY, @AnimationHelper.PivotType int pivotXType,
                         float pivotXValue, @AnimationHelper.PivotType int pivotYType, float pivotYValue) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
        set.addAnimation(scaleAnimation);
        return this;
    }

    public Builder alpha(float fromAlpha, float toAlpha) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
        set.addAnimation(alphaAnimation);
        return this;
    }

    public Builder rotate(float fromDegress, float toDegress) {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegress, toDegress);
        set.addAnimation(rotateAnimation);
        return this;
    }

    public Builder rotateCenter(float fromDegress, float toDegress) {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegress, toDegress, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(rotateAnimation);
        return this;
    }

    public Builder rotate(float fromDegress, float toDegress, @AnimationHelper.PivotType int pivotXType,
                          float pivotXValue, @AnimationHelper.PivotType int pivotYType, float pivotYValue) {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegress, toDegress, pivotXType,
                pivotXValue, pivotYType, pivotYValue);
        set.addAnimation(rotateAnimation);
        return this;
    }

    public Builder translateX(@AnimationHelper.PivotType int valueType, float fromXValue, float toXValue) {
        TranslateAnimation translateAnimation = new TranslateAnimation(valueType, fromXValue,
                valueType, toXValue, valueType, 0, valueType, 0);
        set.addAnimation(translateAnimation);
        return this;
    }

    public Builder translateY(@AnimationHelper.PivotType int valueType, float fromYValue, float toYValue) {
        TranslateAnimation translateAnimation = new TranslateAnimation(valueType, 0,
                valueType, 0, valueType, fromYValue, valueType, toYValue);
        set.addAnimation(translateAnimation);
        return this;
    }

    public Builder onStart(OnAnimationStartListener onAnimationStartListener) {
        mOnAnimationStartListener = onAnimationStartListener;
        return this;
    }

    public Builder onEnd(OnAnimationEndListener onAnimationEndListener) {
        mOnAnimationEndListener = onAnimationEndListener;
        return this;
    }

    public Builder onRepeat(OnAnimationRepeatListener animationRepeatListener) {
        mOnAnimationRepeatListener = animationRepeatListener;
        return this;
    }

    public AnimationSet build() {
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(mOnAnimationStartListener != null) {
                    mOnAnimationStartListener.onStart();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(mOnAnimationEndListener != null) {
                    mOnAnimationEndListener.onEnd();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if(mOnAnimationRepeatListener != null) {
                    mOnAnimationRepeatListener.onRepeat();
                }
            }
        });
        return set;
    }

    public AnimationSet start(View... views) {
        build();
        for(int i = 0; i < views.length; i ++) {
            views[i].startAnimation(set);
        }
        return set;
    }
}
