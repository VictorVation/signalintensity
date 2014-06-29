package com.victorszeto.signalintensity;

import android.app.Activity;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
        float prop = getRssiProportion(Integer.parseInt(msg));

        TextView rssiText = (TextView) findViewById(R.id.fullscreen_content);
        rssiText.setText(msg);

        TextView percentText = (TextView) findViewById(R.id.percent);
        String percent = String.valueOf(Math.round(prop * 100))+'%';
        percentText.setText(percent);

        View backgroundView = findViewById(R.id.background);
        backgroundView.setBackgroundColor(interpolateColor(0x1729B0, 0xFF3500, prop));
    }

    // Get proportion of MAX_RSSI from value
    public static float getRssiProportion(int rssi){
        final int MIN_RSSI = -100;
        final int MAX_RSSI = -55;

        float range = MAX_RSSI - MIN_RSSI;
        float normalized = (MAX_RSSI - rssi) / range;
        System.out.println(normalized);
        return normalized;
    }

    // Get a color with the same proportion
    private static int interpolateColor(int a, int b, float proportion) {
        float[] hsva = new float[3];
        float[] hsvb = new float[3];
        Color.colorToHSV(a, hsva);
        Color.colorToHSV(b, hsvb);
        for (int i = 0; i < 3; i++) {
            hsvb[i] = interpolate(hsva[i], hsvb[i], proportion);
        }
        return Color.HSVToColor(hsvb);
    }

    private static float interpolate(float a, float b, float proportion) {
        return (a + ((b - a) * proportion));
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


