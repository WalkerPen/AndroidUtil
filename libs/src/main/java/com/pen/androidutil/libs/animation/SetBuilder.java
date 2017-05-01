package com.pen.androidutil.libs.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pen on 2017/4/29.
 */

public class SetBuilder extends Builder<SetBuilder>{

    private List<Animation> anims = new ArrayList<>();


    public SetBuilder addAnim(Animation animation) {
        anims.add(animation);
        return this;
    }

    public AnimationSet build(boolean shareInterpolator) {
        AnimationSet set = new AnimationSet(shareInterpolator);
        for(Animation anim : anims) {
            set.addAnimation(anim);
        }
        super.build(set);
        return set;
    }

    public AnimationSet start(boolean shareInterpolator, final View... views) {
        final AnimationSet set = build(shareInterpolator);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < views.length; i++) {
                    views[i].startAnimation(set);
                }
            }
        }, delayed);
        return set;
    }

}
