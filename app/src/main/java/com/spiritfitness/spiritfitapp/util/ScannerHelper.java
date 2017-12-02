package com.spiritfitness.spiritfitapp.util;

import android.app.Activity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.spiritfitness.spiritfitapp.ScannerActivity;

/**
 * Created by geminihsu on 02/12/2017.
 */

public class ScannerHelper {
    public static void scannerXzing(Activity _activity) {
        IntentIntegrator integrator = new IntentIntegrator(_activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();

    }
}
