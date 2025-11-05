package com.example.smartattendanceapp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d(TAG, "onCreate");

        View logo = findViewById(R.id.imgLogo);
        logo.setScaleX(0.8f); logo.setScaleY(0.8f); logo.setAlpha(0f);
        ObjectAnimator.ofFloat(logo, View.ALPHA, 0f, 1f).setDuration(600).start();
        ObjectAnimator.ofFloat(logo, View.SCALE_X, 0.8f, 1f).setDuration(600).start();
        ObjectAnimator.ofFloat(logo, View.SCALE_Y, 0.8f, 1f).setDuration(600).start();

        boolean fromLogout = getIntent().getBooleanExtra("fromLogout", false);
        long delay = fromLogout ? 1200L : 3000L;

        new Handler(getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(this, LoginActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, delay);
    }

    @Override protected void onStart(){ super.onStart(); Log.d(TAG,"onStart"); }
    @Override protected void onResume(){ super.onResume(); Log.d(TAG,"onResume"); }
    @Override protected void onPause(){ super.onPause(); Log.d(TAG,"onPause"); }
    @Override protected void onStop(){ super.onStop(); Log.d(TAG,"onStop"); }
    @Override protected void onDestroy(){ super.onDestroy(); Log.d(TAG,"onDestroy"); }
}
