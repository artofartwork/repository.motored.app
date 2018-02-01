package com.movil.summmit.motorresapp.Storage.db.repository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAdjuntos;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class InformeTecnicoAdjuntosRepository {
    // private static final String TAG = "InformeTecnicoRepository";
    private DatabaseHelper dbHelper;
    private Dao<InformeTecnicoAdjuntos, Integer> entidadDao;

    public InformeTecnicoAdjuntosRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getInformeTecnicoAdjuntosDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(InformeTecnicoAdjuntos entidad) {
        try {
            entidadDao.create(entidad);

            return entidad.getIdAdjuntos();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
        //
    }

    public int update(InformeTecnicoAdjuntos entidad) {
        try {
            return entidadDao.update(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
            //Log.v(TAG, "update exception " + e);
        }

        return 0;
    }

    public int delete(InformeTecnicoAdjuntos entidad) {
        try {
            return entidadDao.delete(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public InformeTecnicoAdjuntos getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InformeTecnicoAdjuntos> findAll() {
        try {
            return entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public InformeTecnicoAdjuntos findxInforme(int IdInformeTecnico) {
        try {


            QueryBuilder<InformeTecnicoAdjuntos, Integer> queryBuilder =
                    entidadDao.queryBuilder();

            queryBuilder.where().eq("IdInformeTecnico", IdInformeTecnico);

            PreparedQuery<InformeTecnicoAdjuntos> preparedQuery = queryBuilder.prepare();

            entidadDao.queryForFirst(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public long getNumberOfNotes() throws SQLException {
        QueryBuilder<InformeTecnicoAdjuntos, Integer> qb = entidadDao.queryBuilder();
       // DeleteBuilder<InformeTecnicoAdjuntos, Integer> del = entidadDao.deleteBuilder();
       // del.where().l
        try {
            return qb.countOf();
        } catch (SQLException e) {
            return -1;
        }
    }
}
