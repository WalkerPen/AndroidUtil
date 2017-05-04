package com.pen.androidutil.libs.animator;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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

    public static Builder backgroundColor(View target, int... values) {
        ObjectAnimator animator = ObjectAnimator.ofInt(target, Property.BACKGROUND_COLOR, values);
        animator.setEvaluator(new ArgbEvaluator());
        return new Builder(animator);
    }
}
