package com.example.alexandrevey.applicationandroidwifi;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alexandrevey on 30/06/15.
 */
public class WifiAdapter extends BaseAdapter {
    String TAG = "WifiAdapter";
    private List<WifiItem> listWifiItem;
    private LayoutInflater layoutInflater;
    private Context context;

    public WifiAdapter (Context context, List<WifiItem> objects){
        this.context=context;
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
        TextView tvSSID;
        TextView tvBSSID;
        TextView tvLevel;
        ImageView ivNetworkLogo;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewWifiHolder viewHolder;
        if(convertView== null){
            viewHolder = new ViewWifiHolder();
            convertView = layoutInflater.inflate(R.layout.wifi_item, null);
            viewHolder.tvSSID = (TextView) convertView.findViewById(R.id.ssid);
            viewHolder.tvBSSID = (TextView) convertView.findViewById(R.id.bssid);
            viewHolder.tvLevel = (TextView) convertView.findViewById(R.id.score);
            viewHolder.ivNetworkLogo = (ImageView) convertView.findViewById(R.id.network_logo);
            convertView.setTag(viewHolder); // ???
        } else {
            viewHolder = (ViewWifiHolder)convertView.getTag();  // ???
        }

        viewHolder.tvSSID.setText(listWifiItem.get(position).getSSID());
        viewHolder.tvBSSID.setText(listWifiItem.get(position).getBSSID());
        viewHolder.tvLevel.setText(Integer.toString(listWifiItem.get(position).getLevel()));
        viewHolder.ivNetworkLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.bullseye));


//        if(listWifiItem.get(position).getLevel() <= -80) {
//            viewHolder.tvLevel.setBackgroundColor(Color.RED);
//        } else if(listWifiItem.get(position).getLevel() <= -50) {
//            viewHolder.tvLevel.setBackgroundColor(Color.YELLOW);
//        } else {
//            viewHolder.tvLevel.setBackgroundColor(Color.GREEN);
//        }

        if(listWifiItem.get(position).getLevel() <= -80) {
            convertView.findViewById(R.id.wifi_item_relative_layout).setBackground(context.getResources().getDrawable(R.drawable.red_box));
        } else if(listWifiItem.get(position).getLevel() <= -50) {
            convertView.findViewById(R.id.wifi_item_relative_layout).setBackground(context.getResources().getDrawable(R.drawable.orange_box));
        } else {
            convertView.findViewById(R.id.wifi_item_relative_layout).setBackground(context.getResources().getDrawable(R.drawable.green_box));
        }

        return convertView;
    }

}

