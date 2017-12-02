package com.spiritfitness.spiritfitapp.adapter;

import com.spiritfitness.spiritfitapp.model.Item;

import java.io.Serializable;

/**
 * Created by geminihsu on 02/12/2017.
 */

public class ItemAdapter implements Serializable {

    private static final long serialVersionUID = 3058244914366597968L;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getCheckbox_visible() {
        return checkbox_visible;
    }

    public void setCheckbox_visible(int checkbox_visible) {
        this.checkbox_visible = checkbox_visible;
    }

    public boolean isCheck;
    public int checkbox_visible;

    public Item getItemModel() {
        return itemModel;
    }

    public void setItemModel(Item itemModel) {
        this.itemModel = itemModel;
    }

    public Item itemModel;

}
