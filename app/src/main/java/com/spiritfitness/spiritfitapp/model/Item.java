package com.spiritfitness.spiritfitapp.model;

import java.io.Serializable;

/**
 * Created by geminihsu on 30/11/2017.
 */

public class Item implements Serializable{
    private static final long serialVersionUID = 8067313536384397083L;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getZoneCoe() {
        return ZoneCoe;
    }

    public void setZoneCoe(int zoneCoe) {
        ZoneCoe = zoneCoe;
    }

    private int ID;
    private String SN;
    private String Location;
    private int ZoneCoe;


}
