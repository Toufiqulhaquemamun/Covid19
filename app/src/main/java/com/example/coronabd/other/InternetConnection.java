package com.example.coronabd.other;

import android.content.Context;
import android.net.ConnectivityManager;

public class InternetConnection {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        return cm.getActiveNetworkInfo() != null;
    }
}
