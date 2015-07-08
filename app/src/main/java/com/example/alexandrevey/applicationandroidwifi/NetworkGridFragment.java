package com.example.alexandrevey.applicationandroidwifi;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NetworkGridFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NetworkGridFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NetworkGridFragment extends android.support.v4.app.Fragment {
    public final static String TAG = "NGF";
    private GridView wifiGridView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivity parentActivity;
    private WifiManager wifiManager;
    private ArrayList<WifiItem> wifiItemList;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NetworkGridFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NetworkGridFragment newInstance(String param1, String param2) {
        NetworkGridFragment fragment = new NetworkGridFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public NetworkGridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//
//        }
        parentActivity = (MainActivity)getActivity();



        WifiBroadcastReceiver broadcastReceiver = new WifiBroadcastReceiver();
        parentActivity.registerReceiver(broadcastReceiver, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_network_grid, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        wifiManager = parentActivity.getWifiManager();
        wifiItemList = parentActivity.getWifiItemList();
        WifiAdapter wifiAdapter = new WifiAdapter(parentActivity,wifiItemList);
        wifiGridView = (GridView) parentActivity.findViewById(R.id.wifi_grid_wiew);
        wifiGridView.setAdapter(wifiAdapter);
        wifiGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WifiItem wifiItem = (WifiItem) parent.getItemAtPosition(position);
                String networkSSID = wifiItem.getSSID();

                WifiConfiguration config = new WifiConfiguration();
                config.SSID = "\"" + networkSSID + "\"";

                switch (wifiItem.getSecurity()) {
                    case WifiItem.OPEN:
                        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                        wifiManager.addNetwork(config);
                        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                        for (WifiConfiguration i : list) {
                            if (i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                                wifiManager.disconnect();
                                wifiManager.enableNetwork(i.networkId, true);
                                wifiManager.reconnect();
                                break;
                            }
                        }
                        break;
                    default:

                        (Toast.makeText(parentActivity, R.string.select_network, Toast.LENGTH_SHORT)).show();
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        //startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));

                }
            }
        });


        swipeRefreshLayout = (SwipeRefreshLayout) parentActivity.findViewById(R.id.swipe_refresh_layout);
        SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (parentActivity.getWifiManager().startScan()) {
                    swipeRefreshLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(true);
                        }
                    });
                }
            }
        };
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.main_purple);
        swipeRefreshLayout.setColorSchemeResources(R.color.white);
        // Scans and displays networks when activity is started
        onRefreshListener.onRefresh();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    public void updateWifiGridView(ArrayList<WifiItem> list){
        WifiAdapter wifiAdapter = new WifiAdapter(parentActivity,list);
        wifiGridView.setAdapter(wifiAdapter);
        swipeRefreshLayout.setRefreshing(false);

        for (WifiItem item : list){
            System.out.println(item.getSSID()+"  "+item.getSecurity()+"  "+item.getCapabilities());
        }
    }

}
