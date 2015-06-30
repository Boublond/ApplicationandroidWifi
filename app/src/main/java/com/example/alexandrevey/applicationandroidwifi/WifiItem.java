package com.example.alexandrevey.applicationandroidwifi;

/**
 * Created by alexandrevey on 30/06/15.
 */
public class WifiItem {
    private String APName;
    private String AdresseMac;
    private int ForceSignal;

    public String getAPName() {
        return APName;
    }

    public String getAdresseMac() {
        return AdresseMac;
    }

    public int getForceSignal() {
        return ForceSignal;
    }

    public void setAPName(String APName) {
        this.APName = APName;
    }

    public void setAdresseMac(String adresseMac) {
        AdresseMac = adresseMac;
    }

    public void setForceSignal(int forceSignal) {
        ForceSignal = forceSignal;
    }
}
