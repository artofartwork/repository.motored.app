package com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Modelo;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class ModeloRepository {

    private DatabaseHelper dbHelper;
    private Dao<Modelo, Integer> entidadDao;

    public ModeloRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getModeloDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Modelo entidad) {
        try {
            entidadDao.create(entidad);
            return entidad.getIdModelo();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
    }


    public Modelo getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Modelo> findAll() {
        try {
            return entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Modelo> findAllporMarca(int idMarca) {
        try {
            QueryBuilder qb = entidadDao.queryBuilder();
            qb.where().eq("IdMarca", idMarca);
            return qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean deleteAllRows()
    {
        DeleteBuilder del = entidadDao.deleteBuilder();
        try
        {
            del.where().ge("IdModelo", 0);
            del.delete();
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }


}
