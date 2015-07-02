package com.example.alexandrevey.applicationandroidwifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandrevey on 01/07/15.
 */
public class WifiBroadcastReceiver extends BroadcastReceiver {
    private WifiManager wifiManager;
    private WifiAdapter wifiAdapter;
    private List<WifiItem> listeWifiItem;
    public void onReceive (Context context, Intent intent) {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        // Gestion de la liste des AP WiFi (voir tuto sur les adapters et les
        // listviews)
        listeWifiItem = new ArrayList<WifiItem>();
        wifiAdapter = new WifiAdapter(context, listeWifiItem);

        if(wifiManager != null) {
            if(wifiManager.isWifiEnabled()) {
                List<ScanResult> listeScan = wifiManager.getScanResults();
                listeWifiItem.clear();
                for (ScanResult scanResult : listeScan) {
                    WifiItem item = new WifiItem();
                    item.setBSSID(scanResult.BSSID);
                    item.setSSID(scanResult.SSID);
                    item.setLevel(scanResult.level);

                    listeWifiItem.add(item);
                }
                //TODO faire une notification pour rafraichir la vue
            } else {
                Toast.makeText(context,"You must enabled your wifi ", Toast.LENGTH_SHORT);
            }

        }

    }


}
