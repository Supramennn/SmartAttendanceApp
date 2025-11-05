package com.example.smartattendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendanceapp.databinding.ActivityDashboardBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {
    private static final String TAG = "DashboardActivity";
    private ActivityDashboardBinding b;
    private final Handler clockHandler = new Handler();
    private final SimpleDateFormat dfDate = new SimpleDateFormat("EEEE, dd MMM yyyy", Locale.getDefault());
    private final SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private final SimpleDateFormat dfStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    private final Runnable clockTick = new Runnable() {
        @Override public void run() {
            Date now = new Date();
            b.tvDate.setText(dfDate.format(now));
            b.tvClock.setText(dfTime.format(now));
            clockHandler.postDelayed(this, 1000);
        }
    };

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        Log.d(TAG, "onCreate");

        String user = getIntent().getStringExtra("username");
        b.tvHello.setText("Halo, " + (user == null ? "User" : user));

        b.btnIn.setOnClickListener(v -> {
            String t = dfStamp.format(new Date());
            b.tvLast.setText("Riwayat: Absen Masuk • " + t);
            Toast.makeText(this, "Absen Masuk tersimpan", Toast.LENGTH_SHORT).show();
        });

        b.btnOut.setOnClickListener(v -> {
            String t = dfStamp.format(new Date());
            b.tvLast.setText("Riwayat: Absen Keluar • " + t);
            Toast.makeText(this, "Absen Keluar tersimpan", Toast.LENGTH_SHORT).show();

            // Balik ke Splash, clear back stack
            Intent i = new Intent(this, SplashActivity.class);
            i.putExtra("fromLogout", true);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        });
    }

    @Override protected void onStart(){ super.onStart(); Log.d(TAG,"onStart"); clockHandler.post(clockTick); }
    @Override protected void onResume(){ super.onResume(); Log.d(TAG,"onResume"); }
    @Override protected void onPause(){ super.onPause(); Log.d(TAG,"onPause"); }
    @Override protected void onStop(){ super.onStop(); Log.d(TAG,"onStop"); clockHandler.removeCallbacks(clockTick); }
    @Override protected void onDestroy(){ super.onDestroy(); Log.d(TAG,"onDestroy"); }
}
