package com.pen.androidutil.libs.animation;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

/**
 * Created by Pen on 2017/4/30.
 */

public class RotateBuilder extends Builder<RotateBuilder> {

    private float fromDegress;
    private float toDegress;
    private int pivotXType;
    private int pivotYType;
    private float pointX;
    private float pointY;

    public RotateBuilder fromDegress(float fromDegress) {
        this.fromDegress = fromDegress;
        return this;
    }

    public RotateBuilder toDegress(float toDegress) {
        this.toDegress = toDegress;
        return this;
    }

    public RotateBuilder pivotType(int pivotType) {
        this.pivotXType = pivotType;
        this.pivotYType = pivotType;
        return this;
    }

    public RotateBuilder point(float pointX, float pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
        return this;
    }

    public RotateAnimation build() {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegress, toDegress, pivotXType,
                pointX, pivotYType, pointY);
        super.build(rotateAnimation);
        return rotateAnimation;
    }

    public RotateAnimation start(final View... views) {
        final RotateAnimation rotateAnimation = build();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < views.length; i++) {
                    views[i].startAnimation(rotateAnimation);
                }
            }
        }, delayed);
        return rotateAnimation;
    }
}
