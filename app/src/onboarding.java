package com.example.blood_line;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;

public class onboarding extends AppCompatActivity {
    private ViewPager slideViewPager;
    private LinearLayout LinearDots;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);



        slideViewPager = findViewById(R.id.slideViewpager);
        LinearDots = findViewById(R.id.linearbelow);
    }
}
