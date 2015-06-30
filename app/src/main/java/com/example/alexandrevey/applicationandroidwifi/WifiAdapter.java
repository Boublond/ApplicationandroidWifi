package com.example.alexandrevey.applicationandroidwifi;

import android.content.Context;
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
    private List<WifiItem> listeWifiItem;
    private LayoutInflater layoutInflater;
    public WifiAdapter (Context context, List<WifiItem> objects){
        listeWifiItem = objects;
        layoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return listeWifiItem.size();
    }

    public Object getItem(int position){
        return listeWifiItem.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    private class ViewWifiHolder{
        TextView tvApName;
        TextView tvAdresseMAc;
        TextView ForceSignal;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewWifiHolder viewHolder;
        if(convertView== null){
            viewHolder = new ViewWifiHolder();
            convertView = layoutInflater.inflate(R.layout.abc_list_menu_item_layout, null);
        }
    }
}
