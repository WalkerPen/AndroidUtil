package com.pen.androidutil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.pen.androidutil.libs.animation.AnimationHelper;
import com.pen.androidutil.libs.animator.AnimatorHelper;
import com.pen.androidutil.libs.animator.Interface;

public class MainActivity extends AppCompatActivity {

    private TextView mTvScale;
    private TextView mTvScale2;
    private TextView mTvScale3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTvScale = (TextView) findViewById(R.id.tv_scale);
        mTvScale2 = (TextView) findViewById(R.id.tv_scale2);
        mTvScale3 = (TextView) findViewById(R.id.tv_scale3);
        mTvScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScale();
//                animator();
            }
        });
    }

    private void animator() {
        AnimatorHelper
                .valueFloat(new Interface.OnFloatValueUpdateLinstener() {
                    @Override
                    public void onValueUpdate(float value) {
                        mTvScale.setScaleX(value);
                        mTvScale.setScaleY(value);
                        Log.d("位置X", mTvScale.getTranslationX() + "");
                        Log.d("位置Y", mTvScale.getTranslationY() + "");
                    }
                }, 0.5f, 1f)
                .duration(500)
                .start();


    }

    private void startScale() {
        AnimationHelper
                .translateX(Animation.RELATIVE_TO_SELF, 0, 1f)
                .translateY(Animation.RELATIVE_TO_SELF, 0, 1f)
                .alpha(0.5f, 1)
                .scaleCenter(0.5f, 1)
                .duration(500)
                .fillAfter()
                .start(mTvScale, mTvScale2, mTvScale3);

    }

}
