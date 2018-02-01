package com.movil.summmit.motorresapp.Storage.db.repository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoRecomendaciones;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class InformeTecnicoRecomendacionesRepository {

    private DatabaseHelper dbHelper;
    private Dao<InformeTecnicoRecomendaciones, Integer> entidadDao;

    public InformeTecnicoRecomendacionesRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getInformeTecnicoRecomendacionesDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(InformeTecnicoRecomendaciones entidad) {
        try {
            entidadDao.create(entidad);

            return entidad.getIdInformeRecomendaciones();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
        //
    }

    public int update(InformeTecnicoRecomendaciones entidad) {
        try {
            return entidadDao.update(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
            //Log.v(TAG, "update exception " + e);
        }

        return 0;
    }

    public int delete(InformeTecnicoRecomendaciones entidad) {
        try {
            return entidadDao.delete(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public InformeTecnicoRecomendaciones getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InformeTecnicoRecomendaciones> findAllxInforme(int IdInforme) {
        try {
            QueryBuilder qb = entidadDao.queryBuilder();
            qb.where()
                    .eq("IdInformeTecnico", IdInforme);
            return qb.query();
            //return entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InformeTecnicoRecomendaciones> findAll() {
        try {
            return entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public long getNumberOfNotes() {
        QueryBuilder<InformeTecnicoRecomendaciones, Integer> qb = entidadDao.queryBuilder();
        try {
            return qb.countOf();
        } catch (SQLException e) {
            return -1;
        }
    }
}
