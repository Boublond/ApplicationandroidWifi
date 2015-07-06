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
import java.util.Random;

/**
 * Created by alexandrevey on 01/07/15.
 */
public class WifiBroadcastReceiver extends BroadcastReceiver{
    String TAG = "WifiBroadcastReceiver";



    private ListView wifiListView;
    private WifiManager wifiManager ;
    private ArrayList<WifiItem> listeWifiItem = new ArrayList<>();
    private WifiAdapter wifiAdapter ;


    public void onReceive (Context context, Intent intent) {

        wifiManager = ((MainActivity) context).getWifiManager();
        Log.i(TAG,"Entered onReceive.");
        wifiAdapter = ((MainActivity) context).getWifiAdapter();
        listeWifiItem = ((MainActivity) context).getWifiItemList();
        Log.i(TAG,"getWifiAdapter OK.");
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);



        if(wifiManager != null) {
            if(wifiManager.isWifiEnabled()) {
                List<ScanResult> listeScan = wifiManager.getScanResults();
                listeWifiItem.clear();
                for (ScanResult scanResult : listeScan) {
                    if (scanResult.SSID.indexOf(""/* Mettre la chaine de caractère que l'on veut retrouver dans les réseaux wifi*/)!=-1){
                    WifiItem item = new WifiItem(scanResult);
                    listeWifiItem.add(item);
                    }

                }
                Collections.sort(listeWifiItem,Collections.reverseOrder());
                int i =1;
                for (WifiItem wifiItem : listeWifiItem) {
                        wifiItem.setRank(i);
                        i=i+1;
                }
                ((MainActivity) context).updateWifiListView(listeWifiItem);


            } else {
                Toast.makeText((MainActivity) context, "You must enable your wifi ", Toast.LENGTH_SHORT);
            }

        }

    }



}
