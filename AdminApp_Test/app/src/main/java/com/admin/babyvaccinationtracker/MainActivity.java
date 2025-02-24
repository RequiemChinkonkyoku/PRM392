package com.admin.babyvaccinationtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.cloudinary.android.MediaManager;
import com.google.firebase.FirebaseApp;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Map config = new HashMap();
    private boolean isCloudinaryInitialized = false;
    private void configCloudinary() {
        if(!isCloudinaryInitialized){
            config.put("cloud_name", "dhp0udnhs");
            config.put("api_key", "287733341235534");
            config.put("api_secret", "mXGs-AKl8JcE0AYw-gxDNu8iCOY");
            MediaManager.init(this, config);
            isCloudinaryInitialized = true;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        configCloudinary();

        Intent intent = new Intent(MainActivity.this, AuthActivity.class);
        startActivity(intent);
    }
}