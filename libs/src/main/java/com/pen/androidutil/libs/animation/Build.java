package com.pen.androidutil.libs.animation;

import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pen on 2017/5/12.
 */

public class Build {

    private List<Animation> mAnimations = new ArrayList<>();

    public Build(Animation animation) {
        mAnimations.add(animation);
    }
}
