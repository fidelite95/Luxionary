package com.luan.luxionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.*;
import android.widget.*;

public class MainEng extends AppCompatActivity {
    // Data from DB
    String strNick, strPw, strName, strEmail, strAvatar;

    TextView tvTitle1, tvTitle2;
    ImageView imgAvatar;
    ImageButton btnEng1, btnEng2, btnEng3, btnEng4;
    TextView tvEng1, tvEng2, tvEng3, tvEng4;
    LinearLayout layoutProfile, llEng1, llEng2, llEng3, llEng4;

    Animation aniTouch;
    Animation aniTitle1, aniTitle2, aniAvatar;
    Animation aniLayoutProfile, aniLayout1, aniLayout2, aniLayout3, aniLayout4;

    // Sidebar
    private DrawerLayout drawerLayout;
    private View drawerView;
    TextView tvNickname, tvEmail;
    ImageView btnClose;

    // Footer
    ImageButton btnSidebar, btnHome, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_eng);

        // Data from SQLite
        Intent getData = getIntent();
        strNick = getData.getStringExtra("nick");
        strPw = getData.getStringExtra("pw");
        strName = getData.getStringExtra("name");
        strEmail = getData.getStringExtra("email");
        strAvatar = getData.getStringExtra("avatar");

        // Sidebar
        tvNickname = (TextView) findViewById(R.id.tvNickname);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawer);
        drawerLayout.setDrawerListener(drawerListener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        btnClose = (ImageView) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        // Title
        tvTitle1 = (TextView) findViewById(R.id.tvTitle1);
        tvTitle1.setOnClickListener(mClickListener);
        tvTitle2 = (TextView) findViewById(R.id.tvTitle2);
        aniTitle1 = AnimationUtils.loadAnimation(MainEng.this, R.anim.descend);
        aniTitle2 = AnimationUtils.loadAnimation(MainEng.this, R.anim.descend);
        aniTitle2.setStartOffset(400);
        tvTitle1.startAnimation(aniTitle1);
        tvTitle2.startAnimation(aniTitle2);
        layoutProfile = (LinearLayout) findViewById(R.id.layoutProfile);
        aniLayoutProfile = AnimationUtils.loadAnimation(MainEng.this, R.anim.fadein);
        aniLayoutProfile.setStartOffset(600);
        layoutProfile.startAnimation(aniLayoutProfile);

        // Avatar
        imgAvatar = (ImageView) findViewById(R.id.imgAvatar);
        if (strAvatar == null) {
            imgAvatar.setImageResource(R.drawable.avt_male1);
        } else {
            switch (strAvatar) {
                case "male1":
                    imgAvatar.setImageResource(R.drawable.avt_male1);
                    break;
                case "male2":
                    imgAvatar.setImageResource(R.drawable.avt_male2);
                    break;
                case "male3":
                    imgAvatar.setImageResource(R.drawable.avt_male3);
                    break;
                case "male4":
                    imgAvatar.setImageResource(R.drawable.avt_male4);
                    break;
                case "female1":
                    imgAvatar.setImageResource(R.drawable.avt_female1);
                    break;
                case "female2":
                    imgAvatar.setImageResource(R.drawable.avt_female2);
                    break;
                case "female3":
                    imgAvatar.setImageResource(R.drawable.avt_female3);
                    break;
                case "female4":
                    imgAvatar.setImageResource(R.drawable.avt_female4);
                    break;
            }
        }
        aniAvatar = AnimationUtils.loadAnimation(MainEng.this, R.anim.fadein);
        aniAvatar.setStartOffset(800);
        imgAvatar.startAnimation(aniAvatar);
        imgAvatar.setOnClickListener(mClickListener);

        // Buttons
        btnEng1 = (ImageButton) findViewById(R.id.btnEng1);
        btnEng2 = (ImageButton) findViewById(R.id.btnEng2);
        btnEng3 = (ImageButton) findViewById(R.id.btnEng3);
        btnEng4 = (ImageButton) findViewById(R.id.btnEng4);
        btnEng1.setOnClickListener(mClickListener);
        btnEng2.setOnClickListener(mClickListener);
        btnEng3.setOnClickListener(mClickListener);
        btnEng4.setOnClickListener(mClickListener);
        tvEng1 = (TextView) findViewById(R.id.tvEng1);
        tvEng2 = (TextView) findViewById(R.id.tvEng2);
        tvEng3 = (TextView) findViewById(R.id.tvEng3);
        tvEng4 = (TextView) findViewById(R.id.tvEng4);

        // Layout Animation
        llEng1 = (LinearLayout) findViewById(R.id.llEng1);
        llEng2 = (LinearLayout) findViewById(R.id.llEng2);
        llEng3 = (LinearLayout) findViewById(R.id.llEng3);
        llEng4 = (LinearLayout) findViewById(R.id.llEng4);
        aniLayout1 = AnimationUtils.loadAnimation(MainEng.this, R.anim.descend_fast);
        aniLayout1.setStartOffset(200);
        aniLayout2 = AnimationUtils.loadAnimation(MainEng.this, R.anim.descend_fast);
        aniLayout2.setStartOffset(400);
        aniLayout3 = AnimationUtils.loadAnimation(MainEng.this, R.anim.descend_fast);
        aniLayout3.setStartOffset(600);
        aniLayout4 = AnimationUtils.loadAnimation(MainEng.this, R.anim.descend_fast);
        aniLayout4.setStartOffset(800);
        llEng1.startAnimation(aniLayout1);
        llEng2.startAnimation(aniLayout2);
        llEng3.startAnimation(aniLayout3);
        llEng4.startAnimation(aniLayout4);

        // Touch Animation
        aniTouch = AnimationUtils.loadAnimation(MainEng.this, R.anim.scale);

        // Footer
        btnSidebar = (ImageButton) findViewById(R.id.btnSidebar);
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnUpdate = (ImageButton) findViewById(R.id.btnUpdate);
        btnSidebar.setOnClickListener(mClickListener);
        btnHome.setOnClickListener(mClickListener);
        btnUpdate.setOnClickListener(mClickListener);
    }

    // Back Button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(MainEng.this, MainActivity.class);
        intentBack.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentBack.putExtra("nick", strNick);
        intentBack.putExtra("pw", strPw);
        intentBack.putExtra("name", strName);
        intentBack.putExtra("email", strEmail);
        intentBack.putExtra("avatar", strAvatar);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Sidebar
    DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
            if (strNick == null) {
                tvNickname.setText("제이슨");
            } else {
                tvNickname.setText(strNick);
            }
            if (strEmail == null) {
                tvEmail.setText("luxionary@gmail.com");
            } else {
                tvEmail.setText(strEmail);
            }
        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    // Main Layout
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // Title Animation
                case R.id.tvTitle1:
                    engTitle();
                    break;
                case R.id.imgAvatar:
                    imgAvatar.startAnimation(aniTouch);
                    break;
                case R.id.btnEng1:
                    btnEng1.startAnimation(aniTouch);
                    pageMono();
                    break;
                case R.id.btnEng2:
                    btnEng2.startAnimation(aniTouch);
                    break;
                case R.id.btnEng3:
                    btnEng3.startAnimation(aniTouch);
                    break;
                case R.id.btnEng4:
                    btnEng4.startAnimation(aniTouch);
                    break;
                case R.id.btnSidebar:
                    drawerLayout.openDrawer(drawerView);
                    break;
                case R.id.btnHome:
                    Intent intentHome = new Intent(MainEng.this, MainActivity.class);
                    intentHome.putExtra("nick", strNick);
                    intentHome.putExtra("pw", strPw);
                    intentHome.putExtra("name", strName);
                    intentHome.putExtra("email", strEmail);
                    intentHome.putExtra("avatar", strAvatar);
                    startActivity(intentHome);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.btnUpdate:
                    break;
            }
        }
    };

    public void pageMono() {
        Intent intentLang101 = new Intent(MainEng.this, MonoEng.class);
        intentLang101.putExtra("nick", strNick);
        intentLang101.putExtra("pw", strPw);
        intentLang101.putExtra("name", strName);
        intentLang101.putExtra("email", strEmail);
        intentLang101.putExtra("avatar", strAvatar);
        startActivity(intentLang101);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Title Animation
    private void engTitle() {
        Integer engDark = Color.parseColor("#B83756");
        Integer engMain = Color.parseColor("#FF6388");
        Integer engLight = Color.parseColor("#FF93AD");

        ValueAnimator colorAni1 = ValueAnimator.ofObject(new ArgbEvaluator(), engMain, engDark);
        colorAni1.setDuration(100);
        colorAni1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvTitle1.setTextColor((Integer)animator.getAnimatedValue());
            }
        });

        ValueAnimator colorAni2 = ValueAnimator.ofObject(new ArgbEvaluator(), engDark, engMain);
        colorAni2.setDuration(100);
        colorAni2.setStartDelay(100);
        colorAni2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvTitle1.setTextColor((Integer)animator.getAnimatedValue());
            }
        });

        ValueAnimator colorAni3 = ValueAnimator.ofObject(new ArgbEvaluator(), engMain, engLight);
        colorAni3.setDuration(100);
        colorAni3.setStartDelay(200);
        colorAni3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvTitle1.setTextColor((Integer)animator.getAnimatedValue());
            }
        });

        ValueAnimator colorAni4 = ValueAnimator.ofObject(new ArgbEvaluator(), engLight, engMain);
        colorAni4.setDuration(100);
        colorAni4.setStartDelay(300);
        colorAni4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvTitle1.setTextColor((Integer)animator.getAnimatedValue());
            }
        });

        colorAni1.start();
        colorAni2.start();
        colorAni3.start();
        colorAni4.start();
    }

}