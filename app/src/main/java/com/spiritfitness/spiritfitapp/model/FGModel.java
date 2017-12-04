package com.spiritfitness.spiritfitapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by geminihsu on 02/12/2017.
 */

public class FGModel extends RealmObject {

    @PrimaryKey
    private String ModelNo;
    private String ModelTitle;

    public String getModelNo() {
        return ModelNo;
    }

    public void setModelNo(String modelNo) {
        ModelNo = modelNo;
    }

    public String getModelTitle() {
        return ModelTitle;
    }

    public void setModelTitle(String modelTitle) {
        ModelTitle = modelTitle;
    }
}
