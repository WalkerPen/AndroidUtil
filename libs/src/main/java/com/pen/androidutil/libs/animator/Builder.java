package com.pen.androidutil.libs.animator;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pen on 2017/5/3.
 */

public class Builder {

    protected long mDuration;
    protected Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    protected long mStartDelay;
    protected int mRepeatMode = ValueAnimator.RESTART;
    protected int mRepeatCount;
    private List<ObjectAnimator> mObjectAnimators;


    public Builder(View target, String property, float... values) {
        mObjectAnimators = new ArrayList<>();
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, property, values);
        mObjectAnimators.add(animator);
    }

    public Builder(ObjectAnimator objectAnimator) {
        mObjectAnimators = new ArrayList<>();
        mObjectAnimators.add(objectAnimator);
    }

    public Builder duration(long duration) {
        mDuration = duration;
        return this;
    }

    public Builder repeatMode(@RepeatMode int repeatMode) {
        mRepeatMode = repeatMode;
        return this;
    }

    public Builder repeatCount(int repeatCount) {
        mRepeatCount = repeatCount;
        return this;
    }

    public Builder startDelay(long startDelay) {
        mStartDelay = startDelay;
        return this;
    }

    public Builder interpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
        return this;
    }

    public Builder translationX(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.TRANSLATE_X, values);
        mObjectAnimators.add(animator);
        return this;
    }

    public Builder translationY(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.TRANSLATE_Y, values);
        mObjectAnimators.add(animator);
        return this;
    }

    public Builder alpha(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.ALPHA, values);
        mObjectAnimators.add(animator);
        return this;
    }

    public Builder scaleX(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.SCALE_X, values);
        mObjectAnimators.add(animator);
        return this;
    }

    public Builder scaleY(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.SCALE_Y, values);
        mObjectAnimators.add(animator);
        return this;
    }

    public Builder rotationX(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.ROTATION_X, values);
        mObjectAnimators.add(animator);
        return this;
    }

    public Builder rotationY(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.ROTATION_Y, values);
        mObjectAnimators.add(animator);
        return this;
    }

    public Builder rotation(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.ROTATION, values);
        mObjectAnimators.add(animator);
        return this;
    }

    public Builder backgroundColor(View target, int... values) {
        ObjectAnimator animator = ObjectAnimator.ofInt(target, Property.BACKGROUND_COLOR, values);
        animator.setEvaluator(new ArgbEvaluator());
        mObjectAnimators.add(animator);
        return this;
    }


    public AnimatorSet build() {
        AnimatorSet set = new AnimatorSet();
        AnimatorSet.Builder builder = null;
        for(int i = 0; i < mObjectAnimators.size(); i ++) {
            ObjectAnimator animator = mObjectAnimators.get(i);
            animator.setDuration(mDuration);
            animator.setStartDelay(mStartDelay);
            animator.setRepeatMode(mRepeatMode);
            animator.setRepeatCount(mRepeatCount);
            animator.setInterpolator(mInterpolator);

            if(i == 0) {
                builder = set.play(animator);
            }else {
                builder.with(animator);
            }
        }
        return set;
    }

    public AnimatorSet start() {
        AnimatorSet set = build();
        set.start();
        return set;
    }

    @IntDef({ValueAnimator.RESTART, ValueAnimator.REVERSE})
    @Target(ElementType.PARAMETER)
    public @interface RepeatMode {}
}
