package com.pen.androidutil.libs.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Pen on 2017/4/30.
 */

public class TranslateBuilder extends Builder<TranslateBuilder> {

    private float fromX;
    private float toX ;
    private float fromY;
    private float toY;
    private int fromXType = Animation.ABSOLUTE;
    private int fromYType = Animation.ABSOLUTE;
    private int toXType = Animation.ABSOLUTE;
    private int toYType = Animation.ABSOLUTE;

    public TranslateBuilder fromX(float fromX) {
        this.fromX = fromX;
        return this;
    }

    public TranslateBuilder toX(float toX) {
        this.toX = toX;
        return this;
    }

    public TranslateBuilder fromY(float fromY) {
        this.fromY = fromY;
        return this;
    }

    public TranslateBuilder toY(float toY) {
        this.toY = toY;
        return this;
    }

    public TranslateBuilder valueType(int fromXType, int toXType, int fromYType, int toYType) {
        this.fromXType = fromXType;
        this.toXType = toXType;
        this.fromYType = fromYType;
        this.toYType = toYType;
        return this;
    }

    public TranslateBuilder valueType(int valueType) {
        this.fromXType = valueType;
        this.toXType = valueType;
        this.fromYType = valueType;
        this.toYType = valueType;
        return this;
    }

    public TranslateAnimation build() {
        TranslateAnimation translateAnimation = new TranslateAnimation(fromXType, fromX, toXType,
                toX, fromYType, fromY, toYType, toY);
        super.build(translateAnimation);
        return translateAnimation;
    }

    public TranslateAnimation start(final View... views) {
        final TranslateAnimation translateAnimation = build();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < views.length; i++) {
                    views[i].startAnimation(translateAnimation);
                }
            }
        }, delayed);
        return translateAnimation;
    }
}
