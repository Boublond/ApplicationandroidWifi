package com.example.alexandrevey.applicationandroidwifi;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    String TAG = "MainActivity";

    private ListView wifiListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Layout set");
        wifiListView = (ListView)findViewById(R.id.wifi_list_wiew);
        //updateWifiListView();
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

    private ArrayList<WifiItem> getFakeWifiList(){
        Log.i(TAG,"Has entered getFakeWifiList");
        ArrayList<WifiItem> wifiList = new ArrayList<WifiItem>() ;
        WifiItem o2wifi1 = new WifiItem(4,"O2 Hotspot 1","B2:B3:C7:D8",-50);
        WifiItem o2wifi2 = new WifiItem(2,"O2 Hotspot 2","F2:B3:C7:D8",-20);
        WifiItem o2wifi3 = new WifiItem(3,"O2 Hotspot 3","A2:B3:C7:D8",-10);
        wifiList.add(o2wifi1);
        wifiList.add(o2wifi2);
        wifiList.add(o2wifi3);
        return wifiList;
    }


    private void updateWifiListView(){
        WifiAdapter wifiAdapter = new WifiAdapter(this,getFakeWifiList());
        wifiListView.setAdapter(wifiAdapter);

    }

    public void onClickRefresh(View v){
        updateWifiListView();
    }


}
