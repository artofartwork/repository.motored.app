package com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import com.movil.summmit.motorresapp.Models.Enity.Maestro.SyncMaestro;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cgonzalez on 01/02/2018.
 */

public class SyncMaestroRepository {

    private DatabaseHelper dbHelper;
    private Dao<SyncMaestro, Integer> entidadDao;

    public SyncMaestroRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getSyncMaestroDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(SyncMaestro entidad) {
        try {
            entidadDao.create(entidad);

        } catch (SQLException e) {
        }
    }

    public int update(SyncMaestro entidad) {
        try {
            return entidadDao.update(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Boolean deleteAllRows()
    {
        DeleteBuilder del = entidadDao.deleteBuilder();
        try
        {
            del.where().ge("IdSync", 0);
            del.delete();
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }

    public List<SyncMaestro> findAll() {
        try {
            return entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SyncMaestro getMaestroSync(String NombreTabla)
    {
        try {

            QueryBuilder<SyncMaestro, Integer> queryBuilder = entidadDao.queryBuilder();
            Where<SyncMaestro, Integer> where = queryBuilder.where();
            where.eq("NombreTabla", NombreTabla);
            PreparedQuery<SyncMaestro> preparedQuery = queryBuilder.prepare();

            return entidadDao.queryForFirst(preparedQuery);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
