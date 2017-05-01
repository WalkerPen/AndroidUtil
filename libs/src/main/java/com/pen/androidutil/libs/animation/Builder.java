package com.pen.androidutil.libs.animation;

import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Interpolator;

import java.util.List;

/**
 * Created by Pen on 2017/4/29.
 */

public class Builder<T extends Builder> {

    protected long duration;
    protected long delayed;
    protected long startOffSet;
    protected Interpolator interpolator;
    protected int repeatMode;
    protected int repeatCount;
    protected boolean fillAfter;
    protected boolean fillBefore;
    protected OnAnimationStartListener onAnimationStartListener;
    protected OnAnimationEndListener onAnimationEndListener;
    protected OnAnimationRepeatListener onAnimationRepeatListener;
    protected Handler handler = new Handler();

    public T duration(long duration) {
        this.duration = duration;
        return (T)this;
    }

    public T delayed(long delayed) {
        this.delayed = delayed;
        return (T)this;
    }

    public T startOffSet(long startOffSet) {
        this.startOffSet = startOffSet;
        return (T)this;
    }

    public T interpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return (T)this;
    }

    public T repeatMode(int repeatMode) {
        this.repeatMode = repeatMode;
        return (T)this;
    }

    public T repeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
        return (T)this;
    }

    public T fillAfter() {
        this.fillAfter = true;
        return (T)this;
    }

    public T onStart(OnAnimationStartListener onAnimationStartListener) {
        this.onAnimationStartListener = onAnimationStartListener;
        return (T)this;
    }

    public T onEnd(OnAnimationEndListener onAnimationEndListener) {
        this.onAnimationEndListener = onAnimationEndListener;
        return (T)this;
    }

    public T onRepeat(OnAnimationRepeatListener onAnimationRepeatListener) {
        this.onAnimationRepeatListener = onAnimationRepeatListener;
        return (T)this;
    }

    protected void build(Animation animation) {
        if(duration != 0) {
            animation.setDuration(duration);
        }
        if(repeatMode != 0) {
            animation.setRepeatMode(repeatMode);
        }
        if(repeatCount != 0) {
            animation.setRepeatCount(repeatCount);
        }
        if(startOffSet != 0) {
            animation.setStartOffset(delayed);
        }
        if(interpolator != null) {
            animation.setInterpolator(interpolator);
        }
        animation.setFillAfter(fillAfter);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(onAnimationStartListener != null) {
                    onAnimationStartListener.onStart();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(onAnimationEndListener != null) {
                    onAnimationEndListener.onEnd();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if(onAnimationRepeatListener != null) {
                    onAnimationRepeatListener.onRepeat();
                }
            }
        });
    }

    public interface OnAnimationStartListener{
        void onStart();
    }

    public interface OnAnimationEndListener{
        void onEnd();
    }

    public interface OnAnimationRepeatListener{
        void onRepeat();
    }
}
