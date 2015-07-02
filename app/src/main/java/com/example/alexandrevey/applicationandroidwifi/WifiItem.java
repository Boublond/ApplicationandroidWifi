package com.example.alexandrevey.applicationandroidwifi;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by alexandrevey on 30/06/15.
 */
public class WifiItem implements Comparable{
    private int rank;
    private String SSID;  // WiFi Network name
    private String BSSID; // MAC Address
    private int level;    // RSSI (signal strength)


    public WifiItem(int rank, String SSID, String BSSID, int level){
        this.rank=rank;
        this.SSID=SSID;
        this.BSSID=BSSID;
        this.level=level;
    }

    public WifiItem(){}

    public int getRank() {
        return rank;
    }

    public String getSSID() {
        return SSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public int getLevel() {
        return level;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }





    @Override
    public int compareTo(Object another) {
        int nombre1 = ((WifiItem) another).getLevel();
        int nombre2 = this.getLevel();
        if (nombre2 > nombre1) {
            return 1;
        }else if(nombre2 < nombre1){
            return -1;
        } else {
            return 0;
        }
    }
}
