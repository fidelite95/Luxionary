package com.luan.luxionary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class IntroActivity extends AppCompatActivity {

    ImageButton imgIntro;
    Animation ani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        imgIntro = (ImageButton) findViewById(R.id.imgIntro);
        ani = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.ascend);
        ani.setStartOffset(200);
        imgIntro.startAnimation(ani);

        // 이벤트 핸들러를 통해서 몇초 후의 동작을 시킨다.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, StartActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        }, 2000);
    }
}