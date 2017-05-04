package com.pen.androidutil.libs.animator;

import android.animation.ObjectAnimator;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Pen on 2017/5/3.
 */

public class ObjectBuilder extends Builder {


    public ObjectBuilder(View target, String property, float... values) {
        super(target, property, values);
    }
}
