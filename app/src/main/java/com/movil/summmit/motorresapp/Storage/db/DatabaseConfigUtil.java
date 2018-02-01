package com.movil.summmit.motorresapp.Storage.db;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by TELEFONICA on 2/12/2017.
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {



    public static void main(String[] args) {

            try {
                writeConfigFile("ormLite_config.txt");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
