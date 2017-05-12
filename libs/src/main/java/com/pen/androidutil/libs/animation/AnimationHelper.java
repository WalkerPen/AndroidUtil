package com.pen.androidutil.libs.animation;

import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/**
 * Created by Pen on 2017/5/8.
 */

public class AnimationHelper {

    public static Build scale(float from, float to, @Interface.PivotType int pivotType, float pivotX, float pivotY) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(from, to, from, to, pivotType, pivotX, pivotType, pivotY);
        return new Build(scaleAnimation);
    }

    public static Build scale(float from, float to) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(from, to, from, to);
        return new Build(scaleAnimation);
    }

    public static Build scaleX(float fromX, float toX, int pivotType) {
        return null;
    }
}
