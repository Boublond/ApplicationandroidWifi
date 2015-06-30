package com.example.alexandrevey.applicationandroidwifi;

import android.content.Context;
import android.graphics.Color;
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
            convertView = layoutInflater.inflate(R.layout.abc_list_menu_item_layout, null);//TODO modifier le type de la liste ?

            viewHolder.tvApName= (TextView) convertView.findViewById(R.id.withText); //TODO modifier les types de R.ID
            viewHolder.tvAdresseMAc= (TextView) convertView.findViewById(R.id.withText);
            viewHolder.ForceSignal= (TextView) convertView.findViewById(R.id.withText);
        } else {
            viewHolder = (ViewWifiHolder)convertView.getTag();
        }

        viewHolder.tvApName.setText(listeWifiItem.get(position).getAPName());
        viewHolder.tvAdresseMAc.setText(listeWifiItem.get(position).getAdresseMac());


        //Truc stylé qui devrait changer la couleur du Wifi en fonction de la puissance de son signal !
        if(listeWifiItem.get(position).getForceSignal() <= -80) {
            viewHolder.ForceSignal.setBackgroundColor(Color.GREEN);
        } else if(listeWifiItem.get(position).getForceSignal() <= -50) {
            viewHolder.ForceSignal.setBackgroundColor(Color.YELLOW);
        } else {
            viewHolder.ForceSignal.setBackgroundColor(Color.RED);
        }

        return convertView;
    }

}

