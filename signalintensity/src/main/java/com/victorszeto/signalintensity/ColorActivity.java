package com.victorszeto.signalintensity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;
import android.widget.TextView;

public class ColorActivity extends Activity {

    private static ColorActivity mInst;
    WifiReceiver wr;
    IntentFilter rssiFilter = new IntentFilter(WifiManager.RSSI_CHANGED_ACTION);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        TextView contentView = (TextView) findViewById(R.id.fullscreen_content);

        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp (MotionEvent e) {
                setImmersive();
                return true;
            }
        });

        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        wr = new WifiReceiver();
        this.registerReceiver(wr, rssiFilter);
    }

    public void setContentText(String msg) {
        TextView rssiText = (TextView) findViewById(R.id.fullscreen_content);
        rssiText.setText(msg);
    }

    private void setImmersive() {
        ViewGroup currentView = (ViewGroup)getWindow().getDecorView();
        currentView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setImmersive();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mInst = null;
        this.unregisterReceiver(wr);
    }

    @Override
    public void onResume() {
        super.onResume();
        mInst = this;
        this.registerReceiver(wr, rssiFilter);
    }

    public static ColorActivity getInstance() {
        return mInst;
    }
}


