package com.nnd.metube.Helper;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lenovo on 4/6/2016.
 */
public class GeneralHelper {
    //simplified make toast (true = LENGTH.LONG , false = LENGTH.FALSE)
    public static void makeToast(Context context, String text, boolean length){
        if(length){
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }
}
