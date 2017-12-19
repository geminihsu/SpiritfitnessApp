package com.spiritfitness.spiritfitapp.common;

/**
 * Created by geminihsu on 01/12/2017.
 */

public class Constants {

    /** <code>SDACRD_DIR_APP</code> */
    final public static String SDACRD_DIR_APP_ROOT = "/SpiritFitness/";


    final public static String SDACRD_DIR_DB_FILE_NAME = "SpiritFitness.realm";
    final public static String SPIRIT_FITINESS_WEB_URL = "http://172.16.2.109:5000";

    public static String APP_NAME = "SpiritFitApp";
    //After scanner the all container, the Container excel file column name ex: MSCU9257505---scanned (16022817)
    public static String CONTAINER_NO = "Container #";
    public static String INVOICE_NO = "Invoice #";
    public static String TOTAL_PICES_RECEIVED = "Total Pieces received:";
    public static String RECEIVED = "Received: "; //received date
    public static String RECEIVED_BY = "Received By: ";
    public static String ENTERED_CS = "Entered CS: ";
    public static String ENTERED_PT = "Entered PT: ";
    public static String SERIAL_NUMBERS = "Serial Numbers:";
    public static String LOCATTION = "Location:";

    public static int SN_LIMIT_LEN = 16;

    //Zone mapping Table key : zone number, value : location number
    public static int ZONE_CODE_LEN_LIMIT = 3;
    public static int ZONE_CODE_1 = 1;
    public static int ZONE_CODE_2 = 2;
    public static int ZONE_CODE_3 = 3;
    public static int ZONE_CODE_4 = 4;
    //Zone 1 range
    public static int ZONE_CODE_1_MIN = 0;
    public static int ZONE_CODE_1_MAX = 69;
    //Zone 2 range
    public static int ZONE_CODE_2_MIN = 701;
    public static int ZONE_CODE_2_MAX = 992;

    //Zone 3 range
    public static int ZONE_CODE_3_A = 881;
    public static int ZONE_CODE_3_B = 891;
    public static int ZONE_CODE_3_C = 901;
    public static int ZONE_CODE_3_D = 911;
    //Zone 4 range
    public static int ZONE_CODE_4_ONE = 888;

    //Parser SN to modelColumn Index
    public static int FG_MODEL_STR_START_INDEX = 0;
    public static int FG_MODEL_STR_LEN = 6;
    public static int FG_DATE_IN_STR_START_INDEX = 6;
    public static int FG_DATE_IN_STR_LEN = 4;

    public static int FG_SERIAL_STR_START_INDEX = 10;
    public static int FG_SERIAL_STR_LEN = 6;

    //Item Db column
    public static String ITEM_SN_COLUMN = "SN";
    public static String ITEM_LOCATION_COLUMN = "Location";


    //Model Db column
    public static String MODEL_NO = "ModelNo";

    //Assign item Type
    public static String Assign_TYPE = "type";
    public static int ASSIGN_RECEIVING = 0;
    public static int ASSIGN_MOVING = 1;

    //Query Type
    public static String INQUIRY_TYPE = "type";
    public static int INQUIRY_MODEL = 0;
    public static int INQUIRY_LOCATION = 1;


}
