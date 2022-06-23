package com.example.dexterphones.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.dexterphones.R;

public class Launch extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_launch);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Launch.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}