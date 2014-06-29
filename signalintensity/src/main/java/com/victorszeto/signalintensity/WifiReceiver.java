package com.victorszeto.signalintensity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

public class WifiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ColorActivity instance = ColorActivity.getInstance();
        int newRssi = intent.getIntExtra(WifiManager.EXTRA_NEW_RSSI, 0);

        if(instance != null) {
            instance.setContentText(String.valueOf(newRssi));
        }
        else {
            System.out.println("No activity detected. RSSI = " + newRssi);
        }
    }
}
