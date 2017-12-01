package com.spiritfitness.spiritfitapp.Constants;

/**
 * Created by geminihsu on 01/12/2017.
 */

public class Constants {
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
}
