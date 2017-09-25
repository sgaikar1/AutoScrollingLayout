package com.sgaikar1.dummyscrollingbg;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.sgaikar1.autoscrollinglayout.AutoScrollingLayout;

/**
 * Created by Santosh on 10/09/17.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        AutoScrollingLayout autoScrollingLayout =(AutoScrollingLayout)findViewById(R.id.scrolling_layout);
        autoScrollingLayout.setBackgroundSrc(ContextCompat.getDrawable(this,R.drawable.longimage1));
        autoScrollingLayout .setSpeed(500f);
        autoScrollingLayout .setBackgroundAlpha(0.5f);
        autoScrollingLayout .setTintColor(Color.parseColor("#E658AA"));
    }
}
