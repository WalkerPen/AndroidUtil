package com.pen.androidutil.libs.animator;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.annotation.Size;
import android.view.View;

/**
 * Created by Pen on 2017/5/3.
 */

public class AnimatorHelper {

    public static Builder alpha(View target, float... values) {
        return new Builder(target, Property.ALPHA, values);
    }

    public static Builder scaleX(View target, float... values) {
        return new Builder(target, Property.SCALE_X, values);
    }

    public static Builder scaleY(View target, float... values) {
        return new Builder(target, Property.SCALE_Y, values);
    }

    public static Builder scale(final View target, float... values) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                target.setScaleX(value);
                target.setScaleY(value);
            }
        });
        return new Builder(valueAnimator);
    }

    public static Builder translateX(View target, float... values) {
        return new Builder(target, Property.TRANSLATE_X, values);
    }

    public static Builder translateY(View target, float... values) {
        return new Builder(target, Property.TRANSLATE_Y, values);
    }

    public static Builder rotationX(View target, float... values) {
        return new Builder(target, Property.ROTATION_X, values);
    }

    public static Builder rotationY(View target, float... values) {
        return new Builder(target, Property.ROTATION_Y, values);
    }

    public static Builder rotation(View target, float... values) {
        return new Builder(target, Property.ROTATION, values);
    }

    public static Builder x(View target, float... values) {
        return new Builder(target, Property.X, values);
    }

    public static Builder y(View target, float... values) {
        return new Builder(target, Property.Y, values);
    }

    public static Builder width(final View target, int... values) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                target.getLayoutParams().width = value;
                target.requestLayout();
            }
        });
        return new Builder(valueAnimator);
    }

    public static Builder height(final View target, int... values) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                target.getLayoutParams().height = value;
                target.requestLayout();
            }
        });
        return new Builder(valueAnimator);
    }

    public static Builder valueInt(final Interface.OnIntValueUpdateLinstener linstener, int... values) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                linstener.onValueUpdate(value);
            }
        });
        return new Builder(valueAnimator);
    }

    public static Builder valueFloat(final Interface.OnFloatValueUpdateLinstener linstener, float... values) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                linstener.onValueUpdate(value);
            }
        });
        return new Builder(valueAnimator);
    }

    public static Builder backgroundColor(View target, int... values) {
        ObjectAnimator animator = ObjectAnimator.ofInt(target, Property.BACKGROUND_COLOR, values);
        animator.setEvaluator(new ArgbEvaluator());
        return new Builder(animator);
    }

    public static Builder color(final Interface.OnColorUpdateLinstener linstener, int... values) {
        if (values.length == 0) {
            values = new int[]{0};
        }
        ValueAnimator valueAnimator = ValueAnimator.ofInt(values);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                linstener.onColorUpdate(value);
            }
        });
        return new Builder(valueAnimator);
    }
}
