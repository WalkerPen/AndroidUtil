package com.pen.androidutil.libs.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

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
    private List<ValueAnimator> mAnimators;
    private Interface.OnAnimatorStartListener mStartListener;
    private Interface.OnAnimatorEndListener mEndListener;
    private Interface.OnAnimatorCancelListener mCancelListener;
    private Interface.OnAnimatorRepeatListener mRepeatListener;


    public Builder(View target, String property, float... values) {
        mAnimators = new ArrayList<>();
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, property, values);
        mAnimators.add(animator);
    }

    public Builder(ValueAnimator objectAnimator) {
        mAnimators = new ArrayList<>();
        mAnimators.add(objectAnimator);
    }

    public Builder duration(long duration) {
        mDuration = duration;
        return this;
    }

    public Builder repeatMode(@Interface.RepeatMode int repeatMode) {
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
        mAnimators.add(animator);
        return this;
    }

    public Builder translationY(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.TRANSLATE_Y, values);
        mAnimators.add(animator);
        return this;
    }

    public Builder alpha(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.ALPHA, values);
        mAnimators.add(animator);
        return this;
    }

    public Builder scaleX(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.SCALE_X, values);
        mAnimators.add(animator);
        return this;
    }

    public Builder scaleY(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.SCALE_Y, values);
        mAnimators.add(animator);
        return this;
    }

    public Builder rotationX(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.ROTATION_X, values);
        mAnimators.add(animator);
        return this;
    }

    public Builder rotationY(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.ROTATION_Y, values);
        mAnimators.add(animator);
        return this;
    }

    public Builder rotation(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.ROTATION, values);
        mAnimators.add(animator);
        return this;
    }

    public Builder x(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.X, values);
        mAnimators.add(animator);
        return this;
    }

    public Builder y(View target, float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, Property.Y, values);
        mAnimators.add(animator);
        return this;
    }

    public Builder backgroundColor(View target, int... values) {
        ObjectAnimator animator = ObjectAnimator.ofInt(target, Property.BACKGROUND_COLOR, values);
        animator.setEvaluator(new ArgbEvaluator());
        mAnimators.add(animator);
        return this;
    }

    public Builder height(final View target, int... values) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(values);
        mAnimators.add(valueAnimator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                target.getLayoutParams().height = value;
                target.requestLayout();
            }
        });
        return this;
    }

    public Builder width(final View target, int... values) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(values);
        mAnimators.add(valueAnimator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                target.getLayoutParams().width = value;
                target.requestLayout();
            }
        });
        return this;
    }

    public Builder valueInt(final Interface.OnIntValueUpdateLinstener linstener, int... values) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(values);
        mAnimators.add(valueAnimator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                if(linstener != null) {
                    linstener.onValueUpdate(value);
                }
            }
        });
        return this;
    }

    public Builder valueFloat(final Interface.OnFloatValueUpdateLinstener linstener, float... values) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        mAnimators.add(valueAnimator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                if(linstener != null) {
                    linstener.onValueUpdate(value);
                }
            }
        });
        return this;
    }

    public Builder color(final Interface.OnColorUpdateLinstener linstener, int... values) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(values);
        mAnimators.add(valueAnimator);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                if(linstener != null) {
                    linstener.onColorUpdate(value);
                }
            }
        });
        return this;
    }

    public Builder onStart(Interface.OnAnimatorStartListener startListener) {
        mStartListener = startListener;
        return this;
    }

    public Builder onEnd(Interface.OnAnimatorEndListener endListener) {
        mEndListener = endListener;
        return this;
    }

    public Builder onCancel(Interface.OnAnimatorCancelListener cancelListener) {
        mCancelListener = cancelListener;
        return this;
    }

    public Builder onRepeat(Interface.OnAnimatorRepeatListener repeatListener) {
        mRepeatListener = repeatListener;
        return this;
    }

    public AnimatorSet build() {
        AnimatorSet set = new AnimatorSet();
        AnimatorSet.Builder builder = null;
        for (int i = 0; i < mAnimators.size(); i++) {
            ValueAnimator animator = mAnimators.get(i);
            animator.setDuration(mDuration);
            animator.setStartDelay(mStartDelay);
            animator.setRepeatMode(mRepeatMode);
            animator.setRepeatCount(mRepeatCount);
            animator.setInterpolator(mInterpolator);

            if (i == 0) {
                builder = set.play(animator);
            } else {
                builder.with(animator);
            }
        }
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(mStartListener != null) {
                    mStartListener.onStart(animation);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(mEndListener != null) {
                    mEndListener.onEnd(animation);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                if(mCancelListener != null) {
                    mCancelListener.onCancel(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                if(mRepeatListener != null) {
                    mRepeatListener.onRepeat(animation);
                }
            }
        });
        return set;
    }

    public AnimatorSet start() {
        AnimatorSet set = build();
        set.start();
        return set;
    }


}
