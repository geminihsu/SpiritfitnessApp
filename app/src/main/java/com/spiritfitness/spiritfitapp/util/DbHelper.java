package com.spiritfitness.spiritfitapp.util;

import android.content.Context;
import android.os.Environment;

import com.spiritfitness.spiritfitapp.common.Constants;
import com.spiritfitness.spiritfitapp.model.Container;
import com.spiritfitness.spiritfitapp.model.FGModel;
import com.spiritfitness.spiritfitapp.model.Item;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by geminihsu on 02/12/2017.
 */

public class DbHelper {

    public final Context mContext;
    private Realm mRealm;
    public DbHelper(Context mContext) {
        File file = new File(Environment.getExternalStorageDirectory()+ Constants.SDACRD_DIR_APP_ROOT);


        this.mContext = mContext;
        mRealm = Realm.getInstance(
                new RealmConfiguration.Builder(file)
                        .name(Constants.SDACRD_DIR_DB_FILE_NAME)
                        .deleteRealmIfMigrationNeeded()
                        .build()
        );
    }

    //------------------Insert Data into table --------------------
    //Add model to table
    public void addContainer(Container _container)
    {
        mRealm.beginTransaction();

        Container container = mRealm.createObject(Container.class);
        container.setContainerNo(_container.getContainerNo());
        container.setInvoiceNo(_container.getInvoiceNo());


        mRealm.copyToRealm(container);
        mRealm.commitTransaction();
    }

    //Add item data to table
    public void addModel(FGModel fgModel)
    {
        mRealm.beginTransaction();

        FGModel item1 = mRealm.createObject(FGModel.class);
        item1.setModelNo(fgModel.getModelNo());
        item1.setModelTitle(fgModel.getModelTitle());


        mRealm.copyToRealm(item1);
        mRealm.commitTransaction();
    }

    //Add item data to table
    public void addItem(Item item)
    {
        mRealm.beginTransaction();

        Item item1 = mRealm.createObject(Item.class);
        item1.setID(item.getID());
        item1.setSN(item.getSN());
        item1.setLocation(item.getLocation());
        item1.setZoneCoe(item.getZoneCoe());
        item1.setModelNo(item.getModelNo());
        item1.setFGDateIn(item.getFGDateIn());
        item1.setFGSerial(item.getFGSerial());

        mRealm.copyToRealm(item1);
        mRealm.commitTransaction();
    }

    //------------------Update Data into table --------------------
    public void updateItemLocation(String sn,String location,int zoneCode)
    {
        Item toEdit = mRealm.where(Item.class)
                .equalTo(Constants.ITEM_SN_COLUMN, sn).findFirst();
        mRealm.beginTransaction();
        toEdit.setLocation(location);
        toEdit.setZoneCoe(zoneCode);
        mRealm.commitTransaction();
    }


    //------------------Query Data from table --------------------

    public FGModel queryModel(String column, String value) {

        FGModel model = mRealm.where(FGModel.class).equalTo(column, value).findFirst();
        return model;
    }

    public RealmResults<Item> queryItemsList(String column, String value) {

        RealmResults<Item> items = mRealm.where(Item.class).equalTo(column, value).findAll();
        return items;
    }

    public void clearDB(final Class table) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.clear(table);
            }
        });
    }

}
