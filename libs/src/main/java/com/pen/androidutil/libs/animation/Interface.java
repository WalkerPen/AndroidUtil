package com.pen.androidutil.libs.animation;

import android.support.annotation.IntDef;
import android.view.animation.Animation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by Pen on 2017/5/12.
 */

public interface Interface {

    @IntDef({Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_PARENT})
    @Target(ElementType.PARAMETER)
    public @interface PivotType{}

    @IntDef({Animation.RESTART, Animation.REVERSE})
    @Target(ElementType.PARAMETER)
    public @interface RepeatMode{}
}
