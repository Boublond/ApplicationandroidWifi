package com.example.alexandrevey.applicationandroidwifi;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alexandrevey on 30/06/15.
 */
public class WifiAdapter extends BaseAdapter {
    String TAG = "WifiAdapter";
    private List<WifiItem> listWifiItem;
    private LayoutInflater layoutInflater;

    public WifiAdapter (Context context, List<WifiItem> objects){
        listWifiItem = objects;
        layoutInflater = LayoutInflater.from(context);
        Log.i(TAG,"WifiAdapter built.");
    }

    public int getCount() {
        return listWifiItem.size();
    }

    public Object getItem(int position){
        return listWifiItem.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    private class ViewWifiHolder{
        TextView tvRank;
        TextView tvSSID;
        TextView tvBSSID;
        TextView tvLevel;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewWifiHolder viewHolder;
        if(convertView== null){
            Log.i(TAG,"convertView is null.");
            viewHolder = new ViewWifiHolder();
            convertView = layoutInflater.inflate(R.layout.wifi_item, null);
            viewHolder.tvRank = (TextView) convertView.findViewById(R.id.rank);
            viewHolder.tvSSID = (TextView) convertView.findViewById(R.id.ssid);
            viewHolder.tvBSSID = (TextView) convertView.findViewById(R.id.bssid);
            viewHolder.tvLevel = (TextView) convertView.findViewById(R.id.level);
            convertView.setTag(viewHolder); // ???
        } else {
            Log.i(TAG,"convertView is not null.");

            viewHolder = (ViewWifiHolder)convertView.getTag();  // ???
        }

        viewHolder.tvRank.setText(Integer.toString(listWifiItem.get(position).getRank()));
        viewHolder.tvSSID.setText(listWifiItem.get(position).getSSID());
        viewHolder.tvBSSID.setText(listWifiItem.get(position).getBSSID());
        viewHolder.tvLevel.setText(Integer.toString(listWifiItem.get(position).getLevel()));


        //Truc stylé qui devrait changer la couleur du Wifi en fonction de la puissance de son signal !
        if(listWifiItem.get(position).getLevel() <= -80) {
            viewHolder.tvLevel.setBackgroundColor(Color.GREEN);
        } else if(listWifiItem.get(position).getLevel() <= -50) {
            viewHolder.tvLevel.setBackgroundColor(Color.YELLOW);
        } else {
            viewHolder.tvLevel.setBackgroundColor(Color.RED);
        }

        return convertView;
    }

}

