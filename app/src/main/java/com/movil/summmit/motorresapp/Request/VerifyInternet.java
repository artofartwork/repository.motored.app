package com.movil.summmit.motorresapp.Request;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by cgonzalez on 24/01/2018.
 */

public class VerifyInternet {
    private Context ctx;
    private String juan;
    public VerifyInternet (Context ctx)
    {
        this.ctx = ctx;
    }

    public boolean isInternetConnected() {

        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }
}
