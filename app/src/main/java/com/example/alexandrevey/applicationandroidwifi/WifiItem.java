package com.example.alexandrevey.applicationandroidwifi;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by alexandrevey on 30/06/15.
 */
public class WifiItem implements Comparable{
    private ScanResult scanResult;
    private int rank;
    private String SSID;  // WiFi Network name
    private String BSSID; // MAC Address
    private int level;    // RSSI (signal strength)
    private String capabilities;
    private int frequency;

    public WifiItem(String SSID, String BSSID, int level){
        this.SSID=SSID;
        this.BSSID=BSSID;
        this.level=level;
    }

    public WifiItem(ScanResult scanResult){
        SSID=scanResult.SSID;
        BSSID=scanResult.BSSID;
        level=scanResult.level;
        capabilities=scanResult.capabilities;
        frequency=scanResult.frequency;
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

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
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
