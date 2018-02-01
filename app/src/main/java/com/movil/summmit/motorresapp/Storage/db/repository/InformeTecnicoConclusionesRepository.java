package com.movil.summmit.motorresapp.Storage.db.repository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoConclusiones;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class InformeTecnicoConclusionesRepository {

    private DatabaseHelper dbHelper;
    private Dao<InformeTecnicoConclusiones, Integer> entidadDao;

    public InformeTecnicoConclusionesRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getInformeTecnicoConclusionesDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(InformeTecnicoConclusiones entidad) {
        try {
            entidadDao.create(entidad);

            return entidad.getIdInformeConclusiones();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
        //
    }

    public int update(InformeTecnicoConclusiones entidad) {
        try {
            return entidadDao.update(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
            //Log.v(TAG, "update exception " + e);
        }

        return 0;
    }

    public int delete(InformeTecnicoConclusiones entidad) {
        try {
            return entidadDao.delete(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public InformeTecnicoConclusiones getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InformeTecnicoConclusiones> findAll() {
        try {
            return entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InformeTecnicoConclusiones> findAllxInforme(int IdInforme) {
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

    public long getNumberOfNotes() {
        QueryBuilder<InformeTecnicoConclusiones, Integer> qb = entidadDao.queryBuilder();
        try {
            return qb.countOf();
        } catch (SQLException e) {
            return -1;
        }
    }
}
