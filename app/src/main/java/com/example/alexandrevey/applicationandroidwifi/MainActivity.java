package com.example.alexandrevey.applicationandroidwifi;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements NetworkGridFragment.OnFragmentInteractionListener {
    String TAG = "MainActivity";

    private WifiManager wifiManager;
    private ArrayList<WifiItem> wifiItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        wifiItemList = new ArrayList<>();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);
        }
        //setTitle("WoodenSideProject");

        android.support.v7.app.ActionBar.Tab tab1 = actionBar.newTab().setText(R.string.networks);
        //android.support.v7.app.ActionBar.Tab tab2 = actionBar.newTab().setText(R.string.map);

        NetworkGridFragment networkGridFragment = new NetworkGridFragment();
        NetworkGridFragment networkGridFragment2 = new NetworkGridFragment();

        tab1.setTabListener(new MyTabListener(networkGridFragment));
        //tab2.setTabListener(new MyTabListener(networkGridFragment2));

        actionBar.addTab(tab1);
        //actionBar.addTab(tab2);
    }

    public ArrayList<WifiItem> getWifiItemList() {
        return wifiItemList;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class MyTabListener implements android.support.v7.app.ActionBar.TabListener {
        public android.support.v4.app.Fragment fragment;

        public MyTabListener(android.support.v4.app.Fragment fragment) {
            this.fragment = fragment;
        }


        @Override
        public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            ft.replace(R.id.fragment_holder, fragment,NetworkGridFragment.TAG);
        }

        @Override
        public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            ft.replace(R.id.fragment_holder, fragment,NetworkGridFragment.TAG);
        }

        @Override
        public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            //do what you want when tab is reselected, I do nothing
        }
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
//        if (id == R.id.to_map) {
//
//            return true;
//
//        }

        return super.onOptionsItemSelected(item);
    }





    public WifiManager getWifiManager() {
        return wifiManager;
    }

    public void setWifiManager(WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }






}

