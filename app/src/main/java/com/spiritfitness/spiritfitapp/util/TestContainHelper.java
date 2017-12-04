package com.spiritfitness.spiritfitapp.util;

import android.app.Activity;

import com.spiritfitness.spiritfitapp.common.Constants;
import com.spiritfitness.spiritfitapp.model.FGModel;
import com.spiritfitness.spiritfitapp.model.Item;

/**
 * Created by geminihsu on 02/12/2017.
 */

public class TestContainHelper {


    public static void inital(Activity activity, final Class table){
               clearContent(activity,table);
               clearContent(activity,FGModel.class);
               itemsContent(activity);
               modelContent(activity);


    }
    private static void itemsContent(Activity activity){

        DbHelper dbHelper = new DbHelper(activity);

        for(int i = 1; i < 10; i++)
        {
            Item item1 = new Item();
            item1.setID(String.valueOf(i));
            item1.setSN("158012130800080"+String.valueOf(i));
            String sn = item1.getSN();
            item1.setModelNo(sn.substring(Constants.FG_MODEL_STR_START_INDEX,Constants.FG_MODEL_STR_START_INDEX+Constants.FG_MODEL_STR_LEN));
            item1.setFGDateIn(sn.substring(Constants.FG_DATE_IN_STR_START_INDEX,Constants.FG_DATE_IN_STR_START_INDEX+Constants.FG_DATE_IN_STR_LEN));
            item1.setFGSerial(sn.substring(Constants.FG_SERIAL_STR_START_INDEX,Constants.FG_SERIAL_STR_START_INDEX + Constants.FG_SERIAL_STR_LEN));
            item1.setLocation("002");
            item1.setZoneCoe(LocationHelper.MapZoneCode(item1.getLocation()));
            dbHelper.addItem(item1);
        }

        for(int i = 10; i < 20; i++)
        {
            Item item1 = new Item();
            item1.setID(String.valueOf(i));
            item1.setSN("15801213080008"+String.valueOf(i));
            item1.setModelNo(item1.getSN().substring(Constants.FG_MODEL_STR_START_INDEX,Constants.FG_MODEL_STR_START_INDEX+Constants.FG_MODEL_STR_LEN));
            item1.setFGDateIn(item1.getSN().substring(Constants.FG_DATE_IN_STR_START_INDEX,Constants.FG_DATE_IN_STR_START_INDEX+Constants.FG_DATE_IN_STR_LEN));
            item1.setFGSerial(item1.getSN().substring(Constants.FG_SERIAL_STR_START_INDEX,Constants.FG_SERIAL_STR_START_INDEX + Constants.FG_SERIAL_STR_LEN));
            item1.setLocation("701");
            item1.setZoneCoe(LocationHelper.MapZoneCode(item1.getLocation()));
            dbHelper.addItem(item1);
        }

        Item item3 = new Item();
        item3.setID(String.valueOf(90));
        item3.setSN("5511151711003778");
        item3.setModelNo(item3.getSN().substring(Constants.FG_MODEL_STR_START_INDEX,Constants.FG_MODEL_STR_START_INDEX+Constants.FG_MODEL_STR_LEN));
        item3.setFGDateIn(item3.getSN().substring(Constants.FG_DATE_IN_STR_START_INDEX,Constants.FG_DATE_IN_STR_START_INDEX+Constants.FG_DATE_IN_STR_LEN));
        item3.setFGSerial(item3.getSN().substring(Constants.FG_SERIAL_STR_START_INDEX,Constants.FG_SERIAL_STR_START_INDEX + Constants.FG_SERIAL_STR_LEN));
        item3.setLocation("002");
        item3.setZoneCoe(LocationHelper.MapZoneCode(item3.getLocation()));
        dbHelper.addItem(item3);

        Item item1 = new Item();
        item1.setID(String.valueOf(20));
        item1.setSN("5511151711003773");
        item1.setModelNo(item1.getSN().substring(Constants.FG_MODEL_STR_START_INDEX,Constants.FG_MODEL_STR_START_INDEX+Constants.FG_MODEL_STR_LEN));
        item1.setFGDateIn(item1.getSN().substring(Constants.FG_DATE_IN_STR_START_INDEX,Constants.FG_DATE_IN_STR_START_INDEX+Constants.FG_DATE_IN_STR_LEN));
        item1.setFGSerial(item1.getSN().substring(Constants.FG_SERIAL_STR_START_INDEX,Constants.FG_SERIAL_STR_START_INDEX + Constants.FG_SERIAL_STR_LEN));
        item1.setLocation("891");
        item1.setZoneCoe(LocationHelper.MapZoneCode(item1.getLocation()));
        dbHelper.addItem(item1);

        Item item2 = new Item();
        item2.setID("30");
        item2.setSN("5511151711003780");
        item2.setModelNo(item1.getSN().substring(Constants.FG_MODEL_STR_START_INDEX,Constants.FG_MODEL_STR_START_INDEX+Constants.FG_MODEL_STR_LEN));
        item2.setFGDateIn(item1.getSN().substring(Constants.FG_DATE_IN_STR_START_INDEX,Constants.FG_DATE_IN_STR_START_INDEX+Constants.FG_DATE_IN_STR_LEN));
        item2.setFGSerial(item1.getSN().substring(Constants.FG_SERIAL_STR_START_INDEX,Constants.FG_SERIAL_STR_START_INDEX + Constants.FG_SERIAL_STR_LEN));
        item2.setLocation("888");
        item2.setZoneCoe(LocationHelper.MapZoneCode(item2.getLocation()));
        dbHelper.addItem(item2);

    }

    private static void modelContent(Activity activity) {

        DbHelper dbHelper = new DbHelper(activity);
        FGModel fgModel = new FGModel();
        fgModel.setModelNo("551115");
        fgModel.setModelTitle("XBR55 2016");

        dbHelper.addModel(fgModel);

        FGModel fgModel1 = new FGModel();
        fgModel1.setModelNo("158012");
        fgModel1.setModelTitle("XTERRA FS5.8e 2013");

        dbHelper.addModel(fgModel1);
        }

    private static void clearContent(Activity _activity, final Class table)
    {
        DbHelper dbHelper = new DbHelper(_activity);
        dbHelper.clearDB(table);
    }


}
