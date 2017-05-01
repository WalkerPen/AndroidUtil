package com.pen.androidutil.libs.animation;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Pen on 2017/4/29.
 */

public class AnimationFactory {

    public static int POINT_ABSOLUTE = Animation.ABSOLUTE;
    public static int POINT_RELATIVE_TO_SELF = Animation.RELATIVE_TO_SELF;
    public static int POINT_RELATIVE_TO_PARENT = Animation.RELATIVE_TO_PARENT;

    public static int REPEATMODE_RESTART = Animation.RESTART;
    public static int REPEATMODE_REVERSE = Animation.REVERSE;

    public static SetBuilder set() {
        return new SetBuilder();
    }

    public static ScaleBuilder scale() {
//        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 100, 0, 100);
        return new ScaleBuilder();
    }

    public static AlphaBuilder alpha() {
        return new AlphaBuilder();
    }

    public static RotateBuilder rotate() {
        return new RotateBuilder();
    }

    public static TranslateBuilder translate() {
        return new TranslateBuilder();
    }

}
