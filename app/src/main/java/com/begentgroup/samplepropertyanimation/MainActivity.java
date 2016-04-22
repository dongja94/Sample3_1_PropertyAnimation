package com.begentgroup.samplepropertyanimation;

import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextSwitcher textSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        textSwitcher = (TextSwitcher)findViewById(R.id.textSwitcher);

        Button btn = (Button)findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofInt(textView,  "textColor",Color.RED, Color.BLUE);
                animator.setEvaluator(new ArgbEvaluator());
                animator.setDuration(3000);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.REVERSE);

//                animator.setTarget(textView);

//                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        int color = (Integer)animation.getAnimatedValue();
//                        textView.setTextColor(color);
//                    }
//                });

                animator.start();
            }
        });

        btn = (Button)findViewById(R.id.btn_size);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min = getResources().getDimensionPixelSize(R.dimen.min_size);
                int max = getResources().getDimensionPixelSize(R.dimen.max_size);
                ValueAnimator animator = ValueAnimator.ofFloat(min, max);
                animator.setEvaluator(new FloatEvaluator());
                animator.setDuration(3000);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.REVERSE);

                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float size = (Float)animation.getAnimatedValue();
                        textView.setTextSize(size);
                    }
                });

                animator.start();

            }
        });

        btn = (Button)findViewById(R.id.btn_change);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView)textSwitcher.getNextView();
                tv.setText("" + count++);
                textSwitcher.showNext();
            }
        });
    }

    int count = 0;
}
