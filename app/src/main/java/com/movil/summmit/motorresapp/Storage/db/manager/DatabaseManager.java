package com.movil.summmit.motorresapp.Storage.db.manager;

import android.content.Context;


import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;

/**
 * Created by TELEFONICA on 2/12/2017.
 */

public class DatabaseManager {

    private DatabaseHelper databaseHelper = null;

    public DatabaseHelper getHelper(Context context) {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }

        return databaseHelper;
    }

    public void releaseHelper() {
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
