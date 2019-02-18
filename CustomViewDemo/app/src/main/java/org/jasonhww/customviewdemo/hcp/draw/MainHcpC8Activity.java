package org.jasonhww.customviewdemo.hcp.draw;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import org.jasonhww.customviewdemo.R;
import org.jasonhww.customviewdemo.hcp.draw.chap8.FancyFlipView;

public class MainHcpC8Activity extends Activity {
    FancyFlipView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hcp_c8_activity_main_draw);
        view = findViewById(R.id.view);


        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 45);
        bottomFlipAnimator.setDuration(1500);

        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 180);
        flipRotationAnimator.setDuration(4000);

        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", -45);
        topFlipAnimator.setDuration(1500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator);
        animatorSet.setStartDelay(1000);
        animatorSet.start();


    }

}
