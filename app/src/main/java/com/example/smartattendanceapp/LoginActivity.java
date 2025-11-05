package com.example.smartattendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartattendanceapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private ActivityLoginBinding b;

    // kredensial demo
    private static final String USER = "Noval Suprayoga";
    private static final String PASS = "1234";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        Log.d(TAG, "onCreate");

        b.btnLogin.setOnClickListener(v -> {
            String u = b.edtUser.getText()==null? "": b.edtUser.getText().toString().trim();
            String p = b.edtPass.getText()==null? "": b.edtPass.getText().toString().trim();

            boolean ok = true;
            if (u.isEmpty()) { b.tilUser.setError("Username wajib"); ok = false; }
            else b.tilUser.setError(null);

            if (p.length() < 4) { b.tilPass.setError("Min 4 karakter"); ok = false; }
            else b.tilPass.setError(null);

            if (!ok) return;

            if (USER.equals(u) && PASS.equals(p)) {
                Intent i = new Intent(this, DashboardActivity.class);
                i.putExtra("username", u);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(this, "Username/Password salah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override protected void onStart(){ super.onStart(); Log.d(TAG,"onStart"); }
    @Override protected void onResume(){ super.onResume(); Log.d(TAG,"onResume"); }
    @Override protected void onPause(){ super.onPause(); Log.d(TAG,"onPause"); }
    @Override protected void onStop(){ super.onStop(); Log.d(TAG,"onStop"); }
    @Override protected void onDestroy(){ super.onDestroy(); Log.d(TAG,"onDestroy"); }
}
