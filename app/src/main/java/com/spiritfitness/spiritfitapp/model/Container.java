package com.spiritfitness.spiritfitapp.model;

import android.content.ClipData;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by geminihsu on 30/11/2017.
 */

public class Container implements Serializable{
    private static final long serialVersionUID = 421359095565877857L;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
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


    private UUID Id;
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

    public List<Item> items;
}
