package com.victorszeto.signalintensity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class WifiReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int newRssi = intent.getIntExtra(wm.EXTRA_NEW_RSSI, 0);
        System.out.println("recieved one "+newRssi);
    }
}
