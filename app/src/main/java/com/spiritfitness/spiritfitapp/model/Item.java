package com.spiritfitness.spiritfitapp.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by geminihsu on 30/11/2017.
 */

public class Item extends RealmObject implements Serializable{
    private static final long serialVersionUID = 8067313536384397083L;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
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

    public String getModelNo() {
        return ModelNo;
    }

    public void setModelNo(String modelNo) {
        ModelNo = modelNo;
    }

    public String getFGDateIn() {
        return FGDateIn;
    }

    public void setFGDateIn(String FGDateIn) {
        this.FGDateIn = FGDateIn;
    }

    public String getFGSerial() {
        return FGSerial;
    }

    public void setFGSerial(String FGSerial) {
        this.FGSerial = FGSerial;
    }

    private String ID;
    @PrimaryKey
    private String SN;

    private String ModelNo;
    private String FGDateIn;
    private String FGSerial;
    private String Location;
    private int ZoneCoe;




}
