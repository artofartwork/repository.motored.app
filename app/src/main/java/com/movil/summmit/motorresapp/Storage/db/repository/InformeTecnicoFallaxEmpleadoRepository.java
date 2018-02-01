package com.movil.summmit.motorresapp.Storage.db.repository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaxEmpleado;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cgonzalez on 26/01/2018.
 */

public class InformeTecnicoFallaxEmpleadoRepository {

    private DatabaseHelper dbHelper;
    private Dao<InformeTecnicoFallaxEmpleado, Integer> entidadDao;

    public InformeTecnicoFallaxEmpleadoRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getInformeTecnicoFallaxEmpleadoDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(InformeTecnicoFallaxEmpleado entidad) {
        try {
            entidadDao.create(entidad);

            //return entidad.get();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            //return 0;
        }
        //
    }

    public List<InformeTecnicoFallaxEmpleado> findAllxInformeTecnicoFalla(int IdInformeTecnicoFalla) {
        try {

            QueryBuilder qb = entidadDao.queryBuilder();
            qb.where()
                    .eq("IdInformeTecnicoFalla", IdInformeTecnicoFalla);

            return qb.query();

        } catch (SQLException e) {
            e.printStackTrace();}


        return null;
    }
}
