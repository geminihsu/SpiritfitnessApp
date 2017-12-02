package com.spiritfitness.spiritfitapp.model;

import android.content.ClipData;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import io.realm.RealmObject;

/**
 * Created by geminihsu on 30/11/2017.
 */

public class Container extends RealmObject implements Serializable{
    private static final long serialVersionUID = 421359095565877857L;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getContainerNo() {
        return ContainerNo;
    }

    public void setContainerNo(String containerNo) {
        ContainerNo = containerNo;
    }

    public String getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        InvoiceNo = invoiceNo;
    }

    public String getPiceRecevid() {
        return PiceRecevid;
    }

    public void setPiceRecevid(String piceRecevid) {
        PiceRecevid = piceRecevid;
    }

    public String getRecevidDate() {
        return RecevidDate;
    }

    public void setRecevidDate(String recevidDate) {
        RecevidDate = recevidDate;
    }

    public String getWorkerInfo() {
        return WorkerInfo;
    }

    public void setWorkerInfo(String workerInfo) {
        WorkerInfo = workerInfo;
    }

    public String getEnteredCS() {
        return EnteredCS;
    }

    public void setEnteredCS(String enteredCS) {
        EnteredCS = enteredCS;
    }

    public String getEnteredPT() {
        return EnteredPT;
    }

    public void setEnteredPT(String enteredPT) {
        EnteredPT = enteredPT;
    }


    private String Id;
    private String ContainerNo;
    private String InvoiceNo;
    private String PiceRecevid ;
    private String RecevidDate ;
    private String WorkerInfo ;
    private String EnteredCS ;
    private String EnteredPT ;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    private List<Item> items;
}
