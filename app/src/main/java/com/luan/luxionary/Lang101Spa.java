package com.luan.luxionary;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Lang101Spa extends AppCompatActivity {

    // Data from DB
    String username, email, profile, avatar;

    TextView tvTitle1, tvTitle2;
    Button btnSpa1, btnSpa2, btnSpa3, btnSpa4, btnSpa5, btnSpa6, btnSpa7, btnSpa8;
    Button btnSpa9, btnSpa10, btnSpa11, btnSpa12, btnSpa13, btnSpa14, btnSpa15, btnSpa16;
    LinearLayout layoutProfile;
    LinearLayout layoutMain;

    Animation aniTouch;
    Animation aniTitle1, aniTitle2;
    Animation aniLayoutProfile;
    Animation aniLayoutMain;

    // Sidebar
    private DrawerLayout drawerLayout;
    private View drawerView;
    TextView tvNickname, tvEmail;
    ImageView btnClose;
    Button btnAccount, btnCharge, btnSupport;

    // Footer
    ImageButton btnSidebar, btnHome, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lang101_spa);

        // Data from Firebase
        Intent getData = getIntent();
        username = getData.getStringExtra("username");
        email = getData.getStringExtra("email");
        profile = getData.getStringExtra("profile");
        avatar = getData.getStringExtra("avatar");

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
        btnAccount = (Button) findViewById(R.id.btnAccount);
        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAccount = new Intent(Lang101Spa.this, AccountActivity.class);
                intentAccount.putExtra("username", username);
                intentAccount.putExtra("email", email);
                intentAccount.putExtra("profile", profile);
                intentAccount.putExtra("avatar", avatar);
                startActivity(intentAccount);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        btnCharge = (Button) findViewById(R.id.btnCharge);
        btnCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCharge = new Intent(Lang101Spa.this, ChargeActivity.class);
                intentCharge.putExtra("username", username);
                intentCharge.putExtra("email", email);
                intentCharge.putExtra("profile", profile);
                intentCharge.putExtra("avatar", avatar);
                startActivity(intentCharge);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        btnSupport = (Button) findViewById(R.id.btnSupport);
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSupport = new Intent(Lang101Spa.this, SupportActivity.class);
                intentSupport.putExtra("username", username);
                intentSupport.putExtra("email", email);
                intentSupport.putExtra("profile", profile);
                intentSupport.putExtra("avatar", avatar);
                startActivity(intentSupport);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });

        // Title
        layoutProfile = (LinearLayout) findViewById(R.id.layoutProfile);
        aniLayoutProfile = AnimationUtils.loadAnimation(Lang101Spa.this, R.anim.descend);
        layoutProfile.startAnimation(aniLayoutProfile);
        tvTitle1 = (TextView) findViewById(R.id.tvTitle1);
        tvTitle1.setOnClickListener(mClickListener);
        tvTitle2 = (TextView) findViewById(R.id.tvTitle2);
        aniTitle1 = AnimationUtils.loadAnimation(Lang101Spa.this, R.anim.fadein);
        aniTitle1.setStartOffset(600);
        aniTitle2 = AnimationUtils.loadAnimation(Lang101Spa.this, R.anim.fadein);
        aniTitle2.setStartOffset(800);
        tvTitle1.startAnimation(aniTitle1);
        tvTitle2.startAnimation(aniTitle2);

        // Touch Animation
        aniTouch = AnimationUtils.loadAnimation(Lang101Spa.this, R.anim.scale);

        // Main Layout
        layoutMain = (LinearLayout) findViewById(R.id.layoutMain);
        aniLayoutMain = AnimationUtils.loadAnimation(Lang101Spa.this, R.anim.fadein);
        aniLayoutMain.setStartOffset(400);
        layoutMain.startAnimation(aniLayoutMain);

        // Buttons & TextViews
        btnSpa1 = (Button) findViewById(R.id.btnSpa1);
        btnSpa2 = (Button) findViewById(R.id.btnSpa2);
        btnSpa3 = (Button) findViewById(R.id.btnSpa3);
        btnSpa4 = (Button) findViewById(R.id.btnSpa4);
        btnSpa5 = (Button) findViewById(R.id.btnSpa5);
        btnSpa6 = (Button) findViewById(R.id.btnSpa6);
        btnSpa7 = (Button) findViewById(R.id.btnSpa7);
        btnSpa8 = (Button) findViewById(R.id.btnSpa8);
        btnSpa9 = (Button) findViewById(R.id.btnSpa9);
        btnSpa10 = (Button) findViewById(R.id.btnSpa10);
        btnSpa11 = (Button) findViewById(R.id.btnSpa11);
        btnSpa12 = (Button) findViewById(R.id.btnSpa12);
        btnSpa13 = (Button) findViewById(R.id.btnSpa13);
        btnSpa14 = (Button) findViewById(R.id.btnSpa14);
        btnSpa15 = (Button) findViewById(R.id.btnSpa15);
        btnSpa16 = (Button) findViewById(R.id.btnSpa16);
        btnSpa1.setOnClickListener(mClickListener);
        btnSpa2.setOnClickListener(mClickListener);
        btnSpa3.setOnClickListener(mClickListener);
        btnSpa4.setOnClickListener(mClickListener);
        btnSpa5.setOnClickListener(mClickListener);
        btnSpa6.setOnClickListener(mClickListener);
        btnSpa7.setOnClickListener(mClickListener);
        btnSpa8.setOnClickListener(mClickListener);
        btnSpa9.setOnClickListener(mClickListener);
        btnSpa10.setOnClickListener(mClickListener);
        btnSpa11.setOnClickListener(mClickListener);
        btnSpa12.setOnClickListener(mClickListener);
        btnSpa13.setOnClickListener(mClickListener);
        btnSpa14.setOnClickListener(mClickListener);
        btnSpa15.setOnClickListener(mClickListener);
        btnSpa16.setOnClickListener(mClickListener);
        setColorStateList(btnSpa2, ContextCompat.getColor(this, R.color.spa_dark), btnSpa2.getCurrentTextColor());
        setColorStateList(btnSpa3, ContextCompat.getColor(this, R.color.spa_dark), btnSpa3.getCurrentTextColor());
        setColorStateList(btnSpa4, ContextCompat.getColor(this, R.color.spa_dark), btnSpa4.getCurrentTextColor());
        setColorStateList(btnSpa5, ContextCompat.getColor(this, R.color.spa_dark), btnSpa5.getCurrentTextColor());
        setColorStateList(btnSpa6, ContextCompat.getColor(this, R.color.spa_dark), btnSpa6.getCurrentTextColor());
        setColorStateList(btnSpa7, ContextCompat.getColor(this, R.color.spa_dark), btnSpa7.getCurrentTextColor());
        setColorStateList(btnSpa8, ContextCompat.getColor(this, R.color.spa_dark), btnSpa8.getCurrentTextColor());
        setColorStateList(btnSpa9, ContextCompat.getColor(this, R.color.spa_dark), btnSpa9.getCurrentTextColor());
        setColorStateList(btnSpa10, ContextCompat.getColor(this, R.color.spa_dark), btnSpa10.getCurrentTextColor());
        setColorStateList(btnSpa11, ContextCompat.getColor(this, R.color.spa_dark), btnSpa11.getCurrentTextColor());
        setColorStateList(btnSpa12, ContextCompat.getColor(this, R.color.spa_dark), btnSpa12.getCurrentTextColor());
        setColorStateList(btnSpa13, ContextCompat.getColor(this, R.color.spa_dark), btnSpa13.getCurrentTextColor());
        setColorStateList(btnSpa14, ContextCompat.getColor(this, R.color.spa_dark), btnSpa14.getCurrentTextColor());
        setColorStateList(btnSpa15, ContextCompat.getColor(this, R.color.spa_dark), btnSpa15.getCurrentTextColor());

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
        Intent intentBack = new Intent(Lang101Spa.this, MainSpa.class);
        intentBack.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentBack.putExtra("username", username);
        intentBack.putExtra("email", email);
        intentBack.putExtra("profile", profile);
        intentBack.putExtra("avatar", avatar);
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
            if (username== null) {
                tvNickname.setText("해리슨");
            } else {
                tvNickname.setText(username);
            }
            if (email == null) {
                tvEmail.setText("luxionary@gmail.com");
            } else {
                tvEmail.setText(email);
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
                case R.id.btnSpa1:
                    btnSpa1.startAnimation(aniTouch);
                    page01();
                    break;
                case R.id.btnSpa2:
                    btnSpa2.startAnimation(aniTouch);
                    page02();
                    break;
                case R.id.btnSpa3:
                    btnSpa3.startAnimation(aniTouch);
                    break;
                case R.id.btnSpa4:
                    btnSpa4.startAnimation(aniTouch);
                    break;
                case R.id.btnSpa5:
                    btnSpa5.startAnimation(aniTouch);
                    break;
                case R.id.btnSpa6:
                    btnSpa6.startAnimation(aniTouch);
                    break;
                case R.id.btnSpa7:
                    btnSpa7.startAnimation(aniTouch);
                    break;
                case R.id.btnSpa8:
                    btnSpa8.startAnimation(aniTouch);
                    break;
                case R.id.btnSpa9:
                    btnSpa9.startAnimation(aniTouch);
                    break;
                case R.id.btnSpa10:
                    btnSpa10.startAnimation(aniTouch);
                    break;
                case R.id.btnSpa11:
                    btnSpa11.startAnimation(aniTouch);
                    break;
                case R.id.btnSpa12:
                    btnSpa12.startAnimation(aniTouch);
                    break;
                case R.id.btnSpa13:
                    btnSpa13.startAnimation(aniTouch);
                    break;
                case R.id.btnSpa14:
                    btnSpa14.startAnimation(aniTouch);
                    break;
                case R.id.btnSpa15:
                    btnSpa15.startAnimation(aniTouch);
                    break;
                case R.id.btnSpa16:
                    btnSpa16.startAnimation(aniTouch);
                    break;
                case R.id.btnSidebar:
                    drawerLayout.openDrawer(drawerView);
                    break;
                case R.id.btnHome:
                    Intent intentHome = new Intent(Lang101Spa.this, MainActivity.class);
                    intentHome.putExtra("username", username);
                    intentHome.putExtra("email", email);
                    intentHome.putExtra("profile", profile);
                    intentHome.putExtra("avatar", avatar);
                    startActivity(intentHome);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.btnUpdate:
                    break;
            }
        }
    };

    public void page01() {
        Intent intent01 = new Intent(Lang101Spa.this, Lang101Spa_01_1.class);
        intent01.putExtra("username", username);
        intent01.putExtra("email", email);
        intent01.putExtra("profile", profile);
        intent01.putExtra("avatar", avatar);
        startActivity(intent01);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    public void page02() {
        Intent intent02 = new Intent(Lang101Spa.this, Lang101Spa_02_1.class);
        intent02.putExtra("username", username);
        intent02.putExtra("email", email);
        intent02.putExtra("profile", profile);
        intent02.putExtra("avatar", avatar);
        startActivity(intent02);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    public void setColorStateList(View view, int selectedColor, int defaultColor) {
        int[][] states = new int[][] {
                new int[] {
                        android.R.attr.state_pressed,
                        android.R.attr.state_selected
                }, // pressed, selected, focused
                new int[] {} // default
        };

        int[] colors = new int[] {
                selectedColor,
                defaultColor
        };

        ColorStateList textColorList = new ColorStateList(states, colors);
        if (view instanceof TextView || view instanceof AppCompatTextView) { // TextView
            ((TextView)view).setTextColor(textColorList);
            view.setClickable(true);
        } else if (view instanceof Button || view instanceof AppCompatButton) { // Button
            ((Button)view).setTextColor(textColorList);
        }
        view.setSelected(true);
    }

}