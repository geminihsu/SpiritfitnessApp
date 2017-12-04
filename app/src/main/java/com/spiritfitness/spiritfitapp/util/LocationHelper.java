package com.spiritfitness.spiritfitapp.util;

import com.spiritfitness.spiritfitapp.common.Constants;

/**
 * Created by geminihsu on 01/12/2017.
 */

public class LocationHelper {

    //find the location mapping zone number
    public static int MapZoneCode(String location)
    {
        int code = -1;
        int locNum = Integer.valueOf(location);

        if (locNum == Constants.ZONE_CODE_4_ONE)
            code = Constants.ZONE_CODE_4;
        else if (locNum >= Constants.ZONE_CODE_1_MIN && locNum <= Constants.ZONE_CODE_1_MAX)
            code = Constants.ZONE_CODE_1;
        else if (locNum >= Constants.ZONE_CODE_2_MIN && locNum <= Constants.ZONE_CODE_2_MAX && locNum != Constants.ZONE_CODE_3_A && locNum != Constants.ZONE_CODE_3_B && locNum != Constants.ZONE_CODE_3_C && locNum != Constants.ZONE_CODE_3_D && locNum != Constants.ZONE_CODE_4)
        {
            if (locNum % 10 == 1 || locNum % 10 == 2)
                code = Constants.ZONE_CODE_2;
        }
        else if (locNum == Constants.ZONE_CODE_3_A || locNum == Constants.ZONE_CODE_3_B || locNum == Constants.ZONE_CODE_3_C || locNum == Constants.ZONE_CODE_3_D)
        {
            code = Constants.ZONE_CODE_3;
        }

        return code;
    }

    public static String convertLocation(String location)
    {
        String result = location;
        int locLen = result.length();

        while(locLen < 3)
        {
            result = "0" + result;
            locLen++;
        }

        return  result;
    }


}
