package com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Marca;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cgonzalez on 18/01/2018.
 */

public class MarcaRepository {

    private DatabaseHelper dbHelper;
    private Dao<Marca, Integer> entidadDao;

    public MarcaRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getMarcaDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Marca entidad) {
        try {
            entidadDao.create(entidad);
            return entidad.getIdMarca();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
    }


    public Marca getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Marca> findAll() {
        try {
            return entidadDao.queryForAll();
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
            del.where().ge("IdMarca", 0);
            del.delete();
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }
}
