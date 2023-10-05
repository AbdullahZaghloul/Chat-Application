package com.abdullah.chat_app.Acivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.abdullah.chat_app.R;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler h= new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(splashActivity.this,Onboarding.class);
                startActivity(i);
                finish();
            }
        },4200);

    }
}