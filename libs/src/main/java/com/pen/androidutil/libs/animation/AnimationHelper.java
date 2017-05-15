package com.pen.androidutil.libs.animation;

import android.support.annotation.IntDef;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by Pen on 2017/5/8.
 */

public class AnimationHelper {

    @IntDef({Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_PARENT})
    @Target(ElementType.PARAMETER)
    public @interface PivotType{}

    @IntDef({Animation.RESTART, Animation.REVERSE})
    @Target(ElementType.PARAMETER)
    public @interface RepeatMode{}

    public interface OnAnimationStartListener{
        void onStart();
    }

    public interface OnAnimationEndListener{
        void onEnd();
    }

    public interface OnAnimationRepeatListener{
        void onRepeat();
    }

    public static Builder scale(float from, float to) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(from, to, from, to, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        return new Builder(scaleAnimation);
    }

    public static Builder scale(float from, float to, @AnimationHelper.PivotType int pivotType, float pivotValue) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(from, to, from, to, pivotType, pivotValue, pivotType, pivotValue);
        return new Builder(scaleAnimation);
    }

    public static Builder scaleCenter(float from, float to) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(from, to, from, to,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        return new Builder(scaleAnimation);
    }

    public static Builder scale(float fromX, float toX, float fromY, float toY, @AnimationHelper.PivotType int pivotXType,
                                float pivotXValue, @AnimationHelper.PivotType int pivotYType, float pivotYValue) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
        return new Builder(scaleAnimation);
    }

    public static Builder alpha(float fromAlpha, float toAlpha) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
        return new Builder(alphaAnimation);
    }

    public static Builder rotate(float fromDegress, float toDegress) {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegress, toDegress);
        return new Builder(rotateAnimation);
    }

    public static Builder rotateCenter(float fromDegress, float toDegress) {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegress, toDegress, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        return new Builder(rotateAnimation);
    }

    public static Builder rotate(float fromDegress, float toDegress, @AnimationHelper.PivotType int pivotXType,
                                 float pivotXValue, @AnimationHelper.PivotType int pivotYType, float pivotYValue) {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegress, toDegress, pivotXType,
                pivotXValue, pivotYType, pivotYValue);
        return new Builder(rotateAnimation);
    }

    public static Builder translateX(@AnimationHelper.PivotType int valueType, float fromXValue, float toXValue) {
        TranslateAnimation translateAnimation = new TranslateAnimation(valueType, fromXValue,
                valueType, toXValue, valueType, 0, valueType, 0);
        return new Builder(translateAnimation);
    }

    public static Builder translateY(@AnimationHelper.PivotType int valueType, float fromYValue, float toYValue) {
        TranslateAnimation translateAnimation = new TranslateAnimation(valueType, 0,
                valueType, 0, valueType, fromYValue, valueType, toYValue);
        return new Builder(translateAnimation);
    }

}
