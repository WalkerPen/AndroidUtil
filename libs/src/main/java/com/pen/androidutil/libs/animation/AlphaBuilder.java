package com.pen.androidutil.libs.animation;

import android.view.View;
import android.view.animation.AlphaAnimation;

/**
 * Created by Pen on 2017/4/30.
 */

public class AlphaBuilder extends Builder<AlphaBuilder> {
    private float fromAlpha = 1f;
    private float toAlpha = 1f;

    public AlphaBuilder fromAlpha(float fromAlpha) {
        this.fromAlpha = fromAlpha;
        return this;
    }

    public AlphaBuilder toAlpha(float toAlpha) {
        this.toAlpha = toAlpha;
        return this;
    }

    public AlphaAnimation build() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
        super.build(alphaAnimation);
        return alphaAnimation;
    }

    public AlphaAnimation start(final View... views) {
        final AlphaAnimation alphaAnimation = build();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < views.length; i++) {
                    views[i].startAnimation(alphaAnimation);
                }
            }
        }, delayed);
        return alphaAnimation;
    }
}
