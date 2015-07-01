package com.example.alexandrevey.applicationandroidwifi;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by alexandrevey on 30/06/15.
 */
public class WifiItemView extends RelativeLayout{
    private int rank;
    private String SSID;  // WiFi Network name
    private String BSSID; // MAC Address
    private int level;    // RSSI (signal strength)


    public WifiItemView(Context context, int rank, String SSID, String BSSID, int level){
        super(context);


        /*     Generating the GUI, view by view with corresponding parameters    */
        RelativeLayout.LayoutParams rankParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView rankView = new TextView(context);
        rankView.setText(rank);
        rankParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rankParams.addRule(RelativeLayout.CENTER_VERTICAL);
        this.addView(rankView,rankParams);

        RelativeLayout.LayoutParams ssidParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView ssidView = new TextView(context);
        ssidView.setText(SSID);
        ssidParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        ssidParams.addRule(RelativeLayout.RIGHT_OF,rankView.getId());
        this.addView(ssidView, ssidParams);

        RelativeLayout.LayoutParams bssidParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView bssidView = new TextView(context);
        bssidView.setText(BSSID);
        bssidParams.addRule(RelativeLayout.ALIGN_LEFT,ssidView.getId());
        bssidParams.addRule(RelativeLayout.BELOW,ssidView.getId());
        this.addView(bssidView, bssidParams);

        RelativeLayout.LayoutParams levelParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView levelView = new TextView(context);
        levelView.setText(level);
        levelParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        levelParams.addRule(RelativeLayout.CENTER_VERTICAL);
        this.addView(bssidView, bssidParams);

    }

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
}
