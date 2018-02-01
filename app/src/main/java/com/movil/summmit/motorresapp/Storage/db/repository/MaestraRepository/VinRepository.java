package com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Vin;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class VinRepository {
    private DatabaseHelper dbHelper;
    private Dao<Vin, Integer> entidadDao;

    public VinRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getVinDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Vin entidad) {
        try {
            entidadDao.create(entidad);
            return  1;
            //return entidad.id();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
    }


    public Vin getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Vin> findAll() {
        try {
            return entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Vin> findAllporMarcaCliente(int IdMarca, int IdCliente) {
        try {

            QueryBuilder qb = entidadDao.queryBuilder();
            qb.where().eq("IdMarca", IdMarca).and().eq("IdCliente", IdCliente);
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
            del.where().ge("IdChasis", 0);
            del.delete();
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }
}
