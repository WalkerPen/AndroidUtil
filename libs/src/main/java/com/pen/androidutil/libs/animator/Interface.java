package com.pen.androidutil.libs.animator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by Pen on 2017/5/4.
 */

public interface Interface {
    @IntDef({ValueAnimator.RESTART, ValueAnimator.REVERSE})
    @Target(ElementType.PARAMETER)
    public @interface RepeatMode {}

    public interface OnIntValueUpdateLinstener{
        void onValueUpdate(int value);
    }

    public interface OnFloatValueUpdateLinstener{
        void onValueUpdate(float value);
    }

    public interface OnColorUpdateLinstener{
        void onColorUpdate(int color);
    }

    public interface OnAnimatorStartListener{
        void onStart(Animator animation);
    }

    public interface OnAnimatorEndListener{
        void onEnd(Animator animation);
    }

    public interface OnAnimatorCancelListener{
        void onCancel(Animator animation);
    }

    public interface OnAnimatorRepeatListener{
        void onRepeat(Animator animation);
    }
}
