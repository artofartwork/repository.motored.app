package com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empresa;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class EmpresaRepository {
    private DatabaseHelper dbHelper;
    private Dao<Empresa, Integer> entidadDao;

    public EmpresaRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getEmpresaDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Empresa entidad) {
        try {
            entidadDao.create(entidad);
            return entidad.getIdEmpresa();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
    }


    public Empresa getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Empresa> findAll() {
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
            del.where().ge("IdEmpresa", 0);
            del.delete();
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }
}
