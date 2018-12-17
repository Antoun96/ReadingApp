package com.example.biro.test;


import android.os.Build;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;

public abstract class Contract  {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static final class ContactEntry implements Telephony.BaseMmsColumns{
        public static final String TABLE_NAME="contact";
        public static final String COLUMN_ID ="id";
        public static final String COULMN_NAME="name";
        public static final String COULMN_PH_NUM="phonenumber";

    }


}
