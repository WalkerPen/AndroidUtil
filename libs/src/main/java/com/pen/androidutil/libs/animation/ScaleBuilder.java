package com.pen.androidutil.libs.animation;

import android.view.View;
import android.view.animation.ScaleAnimation;

/**
 * Created by Pen on 2017/4/29.
 */

public class ScaleBuilder extends Builder<ScaleBuilder>{

    private float fromX = 1f;
    private float toX = 1f;
    private float fromY = 1f;
    private float toY = 1f;
    private int pivotType;
    private float pointX;
    private float pointY;

    public ScaleBuilder fromX(float fromX) {
        this.fromX = fromX;
        return this;
    }

    public ScaleBuilder toX(float toX) {
        this.toX = toX;
        return this;
    }

    public ScaleBuilder fromY(float fromY) {
        this.fromY = fromY;
        return this;
    }

    public ScaleBuilder toY(float toY) {
        this.toY = toY;
        return this;
    }

    public ScaleBuilder pivotType(int pivotType) {
        this.pivotType = pivotType;
        return this;
    }

    public ScaleBuilder point(float pointX, float pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
        return this;
    }

    public ScaleAnimation build() {
        ScaleAnimation scaleAnimation = null;
        scaleAnimation = new ScaleAnimation(fromX, toX, fromY, toY, pivotType, pointX, pivotType, pointY);

        super.build(scaleAnimation);
        return scaleAnimation;
    }

    public ScaleAnimation start(final View... views) {
        final ScaleAnimation scaleAnimation = build();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < views.length; i++) {
                    views[i].startAnimation(scaleAnimation);
                }
            }
        }, delayed);
        return scaleAnimation;
    }


}
