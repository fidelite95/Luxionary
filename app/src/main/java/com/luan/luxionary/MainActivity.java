package com.luan.luxionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.*;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.*;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.*;
import android.widget.*;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    // Data from DB
    String strNick, strPw, strName, strEmail, strAvatar;

    TextView tvTitleHello, tvTitleName;
    TextView tvBanner1, tvBanner2, tvCenterImg;
    LinearLayout llProfile, llAvatar, llBanner, llCenterImg;
    ImageView imgAvatar;
    Button btnDate, btnTasks;

    LinearLayout llEngBox, llFraBox, llDeuBox, llItaBox, llSpaBox, llRusBox;
    LinearLayout llEngImage, llFraImage, llDeuImage, llItaImage, llSpaImage, llRusImage;
    ImageButton btnEng, btnFra, btnDeu, btnSpa, btnIta, btnRus;
    TextView tvEng, tvFra, tvDeu, tvIta, tvSpa, tvRus;

    Animation aniTouch;
    Animation aniLlProfile, aniTitle1, aniTitle2, aniLlAvatar, aniAvatar;
    Animation aniBtnDate, aniBtnTasks;
    Animation aniLlBanner;
    Animation aniBanner1, aniBanner2, aniLlCenterImg, aniTvCenterImg;

    Animation aniLlEngBox, aniLlFraBox, aniLlDeuBox, aniLlSpaBox, aniLlItaBox, aniLlRusBox;
    Animation aniLlEngImage, aniLlFraImage, aniLlDeuImage, aniLlSpaImage, aniLlItaImage, aniLlRusImage;
    Animation aniBtnEng, aniBtnFra, aniBtnDeu, aniBtnSpa, aniBtnIta, aniBtnRus;
    Animation aniTvEng, aniTvFra, aniTvDeu, aniTvSpa, aniTvIta, aniTvRus;

    // Sound
    MediaPlayer soundEng, soundFra, soundDeu, soundIta, soundSpa, soundRus;

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
        setContentView(R.layout.activity_main);

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
        llProfile = (LinearLayout) findViewById(R.id.llProfile);
        aniLlProfile = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        llProfile.startAnimation(aniLlProfile);
        tvTitleHello = (TextView) findViewById(R.id.tvTitleHello);
        tvTitleName = (TextView) findViewById(R.id.tvTitleName);
        if (strNick == null) {
            tvTitleName.setText("해리슨");
        } else {
            tvTitleName.setText(strNick);
        }
        aniTitle1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniTitle1.setStartOffset(200);
        aniTitle2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniTitle2.setStartOffset(500);
        tvTitleHello.startAnimation(aniTitle1);
        tvTitleName.startAnimation(aniTitle2);

        // Avatar
        llAvatar = (LinearLayout) findViewById(R.id.llAvatar);
        aniLlAvatar = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        llAvatar.startAnimation(aniLlAvatar);
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
        aniAvatar = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniAvatar.setStartOffset(800);
        imgAvatar.startAnimation(aniAvatar);
        imgAvatar.setOnClickListener(mClickListener);

        // Touch Animation
        aniTouch = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);

        // Date & Tasks Section
        btnDate = (Button) findViewById(R.id.btnDate);
        btnTasks = (Button) findViewById(R.id.btnTasks);
        aniBtnDate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend_fast);
        aniBtnTasks = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend_fast);
        aniBtnDate.setStartOffset(400);
        aniBtnTasks.setStartOffset(600);
        btnDate.startAnimation(aniBtnDate);
        btnTasks.startAnimation(aniBtnTasks);
        btnDate.setOnClickListener(mClickListener);
        btnTasks.setOnClickListener(mClickListener);

        // Center Image
        llCenterImg = (LinearLayout) findViewById(R.id.llCenterImg);
        tvCenterImg = (TextView) findViewById(R.id.tvCenterImg);
        aniLlCenterImg = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend_fast);
        aniLlCenterImg.setStartOffset(800);
        aniTvCenterImg = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniTvCenterImg.setStartOffset(1000);
        llCenterImg.startAnimation(aniLlCenterImg);
        tvCenterImg.startAnimation(aniTvCenterImg);
        tvCenterImg.setOnClickListener(mClickListener);

        // Banner
        llBanner = (LinearLayout) findViewById(R.id.llBanner);
        tvBanner1 = (TextView) findViewById(R.id.tvBanner1);
        tvBanner2 = (TextView) findViewById(R.id.tvBanner2);
        aniLlBanner = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlBanner.setStartOffset(600);
        aniBanner1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend);
        aniBanner1.setStartOffset(800);
        aniBanner2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend);
        aniBanner2.setStartOffset(1200);
        llBanner.startAnimation(aniLlBanner);
        tvBanner1.startAnimation(aniBanner1);
        tvBanner2.startAnimation(aniBanner2);

        // Sound
//        soundEng = MediaPlayer.create(MainActivity.this, R.raw.main_eng);
//        soundFra = MediaPlayer.create(MainActivity.this, R.raw.main_fra);
//        soundDeu = MediaPlayer.create(MainActivity.this, R.raw.main_deu);
//        soundIta = MediaPlayer.create(MainActivity.this, R.raw.main_ita);
//        soundSpa = MediaPlayer.create(MainActivity.this, R.raw.main_spa);
//        soundRus = MediaPlayer.create(MainActivity.this, R.raw.main_rus);

        // LinearLayout & TextView
        llEngBox = (LinearLayout) findViewById(R.id.llEngBox);
        llFraBox = (LinearLayout) findViewById(R.id.llFraBox);
        llDeuBox = (LinearLayout) findViewById(R.id.llDeuBox);
        llItaBox = (LinearLayout) findViewById(R.id.llItaBox);
        llSpaBox = (LinearLayout) findViewById(R.id.llSpaBox);
        llRusBox = (LinearLayout) findViewById(R.id.llRusBox);
        llEngImage = (LinearLayout) findViewById(R.id.llEngImage);
        llFraImage = (LinearLayout) findViewById(R.id.llFraImage);
        llDeuImage = (LinearLayout) findViewById(R.id.llDeuImage);
        llItaImage = (LinearLayout) findViewById(R.id.llItaImage);
        llSpaImage = (LinearLayout) findViewById(R.id.llSpaImage);
        llRusImage = (LinearLayout) findViewById(R.id.llRusImage);
        tvEng = (TextView) findViewById(R.id.tvEng);
        tvFra = (TextView) findViewById(R.id.tvFra);
        tvDeu = (TextView) findViewById(R.id.tvDeu);
        tvIta = (TextView) findViewById(R.id.tvIta);
        tvSpa = (TextView) findViewById(R.id.tvSpa);
        tvRus = (TextView) findViewById(R.id.tvRus);

        aniLlEngBox = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlFraBox = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlFraBox.setStartOffset(200);
        aniLlDeuBox = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlDeuBox.setStartOffset(400);
        aniLlItaBox = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlItaBox.setStartOffset(600);
        aniLlSpaBox = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlSpaBox.setStartOffset(800);
        aniLlRusBox = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlRusBox.setStartOffset(1000);
        aniLlEngImage = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlEngImage.setStartOffset(600);
        aniLlFraImage = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlFraImage.setStartOffset(800);
        aniLlDeuImage = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlDeuImage.setStartOffset(1000);
        aniLlItaImage = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlItaImage.setStartOffset(1200);
        aniLlSpaImage = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlSpaImage.setStartOffset(1400);
        aniLlRusImage = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlRusImage.setStartOffset(1600);
        aniTvEng = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniTvEng.setStartOffset(600);
        aniTvFra = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniTvFra.setStartOffset(800);
        aniTvDeu = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniTvDeu.setStartOffset(1000);
        aniTvIta = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniTvIta.setStartOffset(1200);
        aniTvSpa = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniTvSpa.setStartOffset(1400);
        aniTvRus = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniTvRus.setStartOffset(1600);

        llEngBox.startAnimation(aniLlEngBox);
        llFraBox.startAnimation(aniLlFraBox);
        llDeuBox.startAnimation(aniLlDeuBox);
        llItaBox.startAnimation(aniLlItaBox);
        llSpaBox.startAnimation(aniLlSpaBox);
        llRusBox.startAnimation(aniLlRusBox);
        llEngImage.startAnimation(aniLlEngImage);
        llFraImage.startAnimation(aniLlFraImage);
        llDeuImage.startAnimation(aniLlDeuImage);
        llItaImage.startAnimation(aniLlItaImage);
        llSpaImage.startAnimation(aniLlSpaImage);
        llRusImage.startAnimation(aniLlRusImage);
        tvEng.startAnimation(aniTvEng);
        tvFra.startAnimation(aniTvFra);
        tvDeu.startAnimation(aniTvDeu);
        tvIta.startAnimation(aniTvIta);
        tvSpa.startAnimation(aniTvSpa);
        tvRus.startAnimation(aniTvRus);

        // Button
        btnEng = (ImageButton) findViewById(R.id.btnEng);
        btnFra = (ImageButton) findViewById(R.id.btnFra);
        btnDeu = (ImageButton) findViewById(R.id.btnDeu);
        btnSpa = (ImageButton) findViewById(R.id.btnSpa);
        btnIta = (ImageButton) findViewById(R.id.btnIta);
        btnRus = (ImageButton) findViewById(R.id.btnRus);
        aniBtnEng = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnEng.setStartOffset(600);
        aniBtnFra = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnFra.setStartOffset(800);
        aniBtnDeu = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnDeu.setStartOffset(1000);
        aniBtnIta = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnIta.setStartOffset(1200);
        aniBtnSpa = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnSpa.setStartOffset(1400);
        aniBtnRus = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnRus.setStartOffset(1600);
        btnEng.startAnimation(aniBtnEng);
        btnFra.startAnimation(aniBtnFra);
        btnDeu.startAnimation(aniBtnDeu);
        btnSpa.startAnimation(aniBtnSpa);
        btnIta.startAnimation(aniBtnIta);
        btnRus.startAnimation(aniBtnRus);
        btnEng.setOnClickListener(mClickListener);
        btnFra.setOnClickListener(mClickListener);
        btnDeu.setOnClickListener(mClickListener);
        btnSpa.setOnClickListener(mClickListener);
        btnIta.setOnClickListener(mClickListener);
        btnRus.setOnClickListener(mClickListener);

        // Footer
        btnSidebar = (ImageButton) findViewById(R.id.btnSidebar);
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnUpdate = (ImageButton) findViewById(R.id.btnUpdate);
        btnSidebar.setOnClickListener(mClickListener);
        btnHome.setOnClickListener(mClickListener);
        btnUpdate.setOnClickListener(mClickListener);
    }

    // Sidebar
    DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
            if (strNick == null) {
                tvNickname.setText("해리슨");
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
                case R.id.imgAvatar:
                    imgAvatar.startAnimation(aniTouch);
                    dialogAvatar();
                    break;
                case R.id.btnDate:
                    btnDate.startAnimation(aniTouch);
                    Intent intentDate = new Intent(MainActivity.this, DateActivity.class);
                    startActivity(intentDate);
                    intentDate.putExtra("nick", strNick);
                    intentDate.putExtra("pw", strPw);
                    intentDate.putExtra("name", strName);
                    intentDate.putExtra("email", strEmail);
                    intentDate.putExtra("avatar", strAvatar);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.btnTasks:
                    btnTasks.startAnimation(aniTouch);
                    break;
                case R.id.tvCenterImg:
                    llCenterImg.startAnimation(aniTouch);
                    rainbow();
                    break;
                case R.id.btnEng:
                    llEngBox.startAnimation(aniTouch);
                    llEngImage.startAnimation(aniTouch);
                    pageEng();
                    break;
                case R.id.btnFra:
                    llFraBox.startAnimation(aniTouch);
                    llFraImage.startAnimation(aniTouch);
                    pageFra();
                    break;
                case R.id.btnDeu:
                    llDeuBox.startAnimation(aniTouch);
                    llDeuImage.startAnimation(aniTouch);
                    pageDeu();
                    break;
                case R.id.btnIta:
                    llItaBox.startAnimation(aniTouch);
                    llItaImage.startAnimation(aniTouch);
                    pageIta();
                    break;
                case R.id.btnSpa:
                    llSpaBox.startAnimation(aniTouch);
                    llSpaImage.startAnimation(aniTouch);
                    pageSpa();
                    break;
                case R.id.btnRus:
                    llRusBox.startAnimation(aniTouch);
                    llRusImage.startAnimation(aniTouch);
                    pageRus();
                    break;
                case R.id.btnSidebar:
                    drawerLayout.openDrawer(drawerView);
                    break;
                case R.id.btnHome:
                    Intent intentHome = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intentHome);
                    intentHome.putExtra("nick", strNick);
                    intentHome.putExtra("pw", strPw);
                    intentHome.putExtra("name", strName);
                    intentHome.putExtra("email", strEmail);
                    intentHome.putExtra("avatar", strAvatar);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.btnUpdate:
                    break;
            }
        }
    };

    // Text Color Animator
//    private void setThemeAnimation(int fromColor, int toColor) {
//        Integer colorFrom = getResources().getColor(fromColor);
//        Integer colorTo = getResources().getColor(toColor);
//        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
//        colorAnimation.setDuration(200);
//        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animator) {
//                tvCenterImg.setTextColor((Integer)animator.getAnimatedValue());
//            }
//        });
//        colorAnimation.start();
//    }

    // Rainbow Animation
    private void rainbow() {
        Integer white = Color.parseColor("#FFFFFF");
        Integer eng = Color.parseColor("#FF6388");
        Integer fra = Color.parseColor("#0FB8EF");
        Integer deu = Color.parseColor("#FFD36B");
        Integer ita = Color.parseColor("#0EDFA9");
        Integer spa = Color.parseColor("#FF9450");
        Integer rus = Color.parseColor("#9A89FF");

        ValueAnimator colorAni1 = ValueAnimator.ofObject(new ArgbEvaluator(), white, eng);
        colorAni1.setDuration(200);
        colorAni1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvCenterImg.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        ValueAnimator colorAni2 = ValueAnimator.ofObject(new ArgbEvaluator(), eng, fra);
        colorAni2.setDuration(200);
        colorAni2.setStartDelay(200);
        colorAni2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvCenterImg.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        ValueAnimator colorAni3 = ValueAnimator.ofObject(new ArgbEvaluator(), fra, deu);
        colorAni3.setDuration(200);
        colorAni3.setStartDelay(400);
        colorAni3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvCenterImg.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        ValueAnimator colorAni4 = ValueAnimator.ofObject(new ArgbEvaluator(), deu, ita);
        colorAni4.setDuration(200);
        colorAni4.setStartDelay(600);
        colorAni4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvCenterImg.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        ValueAnimator colorAni5 = ValueAnimator.ofObject(new ArgbEvaluator(), ita, spa);
        colorAni5.setDuration(200);
        colorAni5.setStartDelay(800);
        colorAni5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvCenterImg.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        ValueAnimator colorAni6 = ValueAnimator.ofObject(new ArgbEvaluator(), spa, rus);
        colorAni6.setDuration(200);
        colorAni6.setStartDelay(1000);
        colorAni6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvCenterImg.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        ValueAnimator colorAni7 = ValueAnimator.ofObject(new ArgbEvaluator(), rus, white);
        colorAni7.setDuration(200);
        colorAni7.setStartDelay(1200);
        colorAni7.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvCenterImg.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        colorAni1.start();
        colorAni2.start();
        colorAni3.start();
        colorAni4.start();
        colorAni5.start();
        colorAni6.start();
        colorAni7.start();
    }

    private void dialogAvatar() {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.avatar_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_window);
        dialog.setCancelable(false);

        ImageView btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button btnNo = dialog.findViewById(R.id.btn_no);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button btnYes = dialog.findViewById(R.id.btn_yes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                // 현재 아바타 변수 전달하기 (Intent & DB)
                Intent intentAvatar = new Intent(MainActivity.this, AvatarActivity.class);
                intentAvatar.putExtra("nick", strNick);
                intentAvatar.putExtra("pw", strPw);
                intentAvatar.putExtra("name", strName);
                intentAvatar.putExtra("email", strEmail);
                intentAvatar.putExtra("avatar", strAvatar);
                startActivity(intentAvatar);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        });
        dialog.show();
    }

    public void pageEng() {
        Intent intentEng = new Intent(MainActivity.this, IntroEng.class);
        intentEng.putExtra("nick", strNick);
        intentEng.putExtra("pw", strPw);
        intentEng.putExtra("name", strName);
        intentEng.putExtra("email", strEmail);
        intentEng.putExtra("avatar", strAvatar);
        startActivity(intentEng);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    public void pageFra() {
        Intent intentFra = new Intent(MainActivity.this, IntroFra.class);
        intentFra.putExtra("nick", strNick);
        intentFra.putExtra("pw", strPw);
        intentFra.putExtra("name", strName);
        intentFra.putExtra("email", strEmail);
        intentFra.putExtra("avatar", strAvatar);
        startActivity(intentFra);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    public void pageDeu() {
        Intent intentDeu = new Intent(MainActivity.this, IntroDeu.class);
        intentDeu.putExtra("nick", strNick);
        intentDeu.putExtra("pw", strPw);
        intentDeu.putExtra("name", strName);
        intentDeu.putExtra("email", strEmail);
        intentDeu.putExtra("avatar", strAvatar);
        startActivity(intentDeu);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    public void pageIta() {
        Intent intentIta = new Intent(MainActivity.this, IntroIta.class);
        intentIta.putExtra("nick", strNick);
        intentIta.putExtra("pw", strPw);
        intentIta.putExtra("name", strName);
        intentIta.putExtra("email", strEmail);
        intentIta.putExtra("avatar", strAvatar);
        startActivity(intentIta);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    public void pageSpa() {
        Intent intentSpa = new Intent(MainActivity.this, IntroSpa.class);
        intentSpa.putExtra("nick", strNick);
        intentSpa.putExtra("pw", strPw);
        intentSpa.putExtra("name", strName);
        intentSpa.putExtra("email", strEmail);
        intentSpa.putExtra("avatar", strAvatar);
        startActivity(intentSpa);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    public void pageRus() {
        Intent intentRus = new Intent(MainActivity.this, IntroRus.class);
        intentRus.putExtra("nick", strNick);
        intentRus.putExtra("pw", strPw);
        intentRus.putExtra("name", strName);
        intentRus.putExtra("email", strEmail);
        intentRus.putExtra("avatar", strAvatar);
        startActivity(intentRus);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    public void setColorStateList(View view, int selectedColor, int defaultColor) {
        int[][] states = new int[][]{
                new int[]{
                        android.R.attr.state_pressed,
                        android.R.attr.state_selected
                }, // pressed, selected, focused
                new int[]{} // default
        };

        int[] colors = new int[]{
                selectedColor,
                defaultColor
        };

        ColorStateList textColorList = new ColorStateList(states, colors);
        if (view instanceof TextView || view instanceof AppCompatTextView) { // TextView
            ((TextView) view).setTextColor(textColorList);
            view.setClickable(true);
        } else if (view instanceof Button || view instanceof AppCompatButton) { // Button
            ((Button) view).setTextColor(textColorList);
        }
        view.setSelected(true);
    }

}