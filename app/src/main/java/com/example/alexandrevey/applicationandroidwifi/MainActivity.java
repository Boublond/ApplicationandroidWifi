package com.example.alexandrevey.applicationandroidwifi;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    String TAG = "MainActivity";

    private GridView wifiListView;
    private WifiManager wifiManager;
    private WifiAdapter wifiAdapter;
    private Button refreshButton;

    private ArrayList<WifiItem> wifiItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifiListView = (GridView)findViewById(R.id.wifi_list_wiew);
        refreshButton = (Button)findViewById(R.id.refresh);

        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        wifiItemList = new ArrayList<>();
        WifiAdapter wifiAdapter = new WifiAdapter(this,wifiItemList);

        WifiBroadcastReceiver broadcastReceiver = new WifiBroadcastReceiver();
        registerReceiver(broadcastReceiver, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        wifiListView.setAdapter(wifiAdapter);
        wifiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String networkSSID = ((WifiItem)parent.getItemAtPosition(position)).getSSID();

                WifiConfiguration conf = new WifiConfiguration();
                conf.SSID = "\"" + networkSSID + "\"";
                conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                wifiManager.addNetwork(conf);
                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                for( WifiConfiguration i : list ) {
                    if(i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                        wifiManager.disconnect();
                        wifiManager.enableNetwork(i.networkId, true);
                        wifiManager.reconnect();
                        break;
                    }
                }
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

//    private ArrayList<WifiItem> getFakeWifiList(){
//        Log.i(TAG,"Has entered getFakeWifiList");
//        ArrayList<WifiItem> wifiList = new ArrayList<WifiItem>() ;
//        WifiItem o2wifi1 = new WifiItem(4,"O2 Hotspot 1","B2:B3:C7:D8",-50);
//        WifiItem o2wifi2 = new WifiItem(2,"O2 Hotspot 2","F2:B3:C7:D8",-20);
//        WifiItem o2wifi3 = new WifiItem(3,"O2 Hotspot 3","A2:B3:C7:D8",-10);
//        wifiList.add(o2wifi1);
//        wifiList.add(o2wifi2);
//        wifiList.add(o2wifi3);
//        return wifiList;
//    }


    public void updateWifiListView(ArrayList<WifiItem> liste){
        WifiAdapter wifiAdapter = new WifiAdapter(this,liste);
        wifiListView.setAdapter(wifiAdapter);

        refreshButton.setText(getResources().getString(R.string.refresh));
        refreshButton.setClickable(true);

        for (WifiItem item : liste){
            System.out.println(item.getCapabilities());
        }
    }

    public void onClickRefresh(View v){
        if (wifiManager.startScan()){
            refreshButton.setText(getResources().getString(R.string.scanning));
            refreshButton.setClickable(false);
        }

    }



    public WifiManager getWifiManager() {
        return wifiManager;
    }

    public void setWifiManager(WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }

    public WifiAdapter getWifiAdapter() {
        return wifiAdapter;
    }

    public void setWifiAdapter(WifiAdapter wifiAdapter) {
        this.wifiAdapter = wifiAdapter;
    }
    public void setwifiListView(ListView wifiListView){
        //this.wifiListView = wifiListView;
    }

    public GridView getwifiListView(){
        return wifiListView;
    }

    public ArrayList<WifiItem> getWifiItemList() {
        return wifiItemList;
    }

    public void setWifiItemList(ArrayList<WifiItem> wifiItemList) {
        this.wifiItemList = wifiItemList;
    }
}

