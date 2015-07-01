package com.example.alexandrevey.applicationandroidwifi;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends ActionBarActivity {

    private ListView wifiListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiListView = (ListView)findViewById(R.id.wifi_list_wiew);
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

    private ArrayList<WifiItemView> getFakeWifiList(){
        ArrayList<WifiItemView> wifiList = new ArrayList<WifiItemView>() ;
        WifiItemView o2wifi1 = new WifiItemView(this,1,"O2 Hotspot 1","B2:B3:C7:D8",-50);
        WifiItemView o2wifi2 = new WifiItemView(this,2,"O2 Hotspot 2","F2:B3:C7:D8",-20);
        WifiItemView o2wifi3 = new WifiItemView(this,3,"O2 Hotspot 3","A2:B3:C7:D8",-10);
        wifiList.add(o2wifi1);
        wifiList.add(o2wifi2);
        wifiList.add(o2wifi3);
        return wifiList;
    }

    // Probleme de compatibilite, le ListAdapter ne peut gerer que des TextView
    private void updateWifiListView(){
        //ArrayAdapter<WifiItemView> arrayAdapter =
                //new ArrayAdapter<WifiItemView>(this,R.id.wifi_list_wiew,R.id.,getFakeWifiList());
        //wifiListView.setAdapter(arrayAdapter);
    }



}
