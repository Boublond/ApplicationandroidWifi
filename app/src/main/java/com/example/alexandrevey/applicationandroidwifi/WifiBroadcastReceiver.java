package com.example.alexandrevey.applicationandroidwifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexandrevey on 01/07/15.
 */
public class WifiBroadcastReceiver extends BroadcastReceiver{
    String TAG = "WifiBroadcastReceiver";

    public void onReceive (Context context, Intent intent) {

        WifiManager wifiManager = ((MainActivity) context).getWifiManager();
        Log.i(TAG,"Entered onReceive.");
        ArrayList<WifiItem> listWifiItem = ((MainActivity) context).getWifiItemList();
        Log.i(TAG,"getWifiAdapter OK.");
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        if(wifiManager != null) {
            if(wifiManager.isWifiEnabled()) {
                List<ScanResult> listeScan = wifiManager.getScanResults();
                listWifiItem.clear();
                for (ScanResult scanResult : listeScan) {
                    if (scanResult.SSID.contains(""/* Mettre la chaine de caractère que l'on veut retrouver dans les réseaux wifi*/)){
                    WifiItem item = new WifiItem(scanResult);
                    listWifiItem.add(item);
                    }

                }
                Collections.sort(listWifiItem,Collections.reverseOrder());
                int i =1;
                for (WifiItem wifiItem : listWifiItem) {
                        wifiItem.setRank(i);
                        i=i+1;
                }
                NetworkGridFragment networkGridFragment = (NetworkGridFragment)((MainActivity) context)
                        .getSupportFragmentManager()
                        .findFragmentByTag(NetworkGridFragment.TAG);
                networkGridFragment.updateWifiGridView(listWifiItem);


            } else {
                Toast.makeText((MainActivity) context, "You must enable your wifi ", Toast.LENGTH_SHORT);
            }

        }

    }



}
