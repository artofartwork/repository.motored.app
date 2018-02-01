package com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.MaestraArgu;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class MaestraArguRepository {
    private DatabaseHelper dbHelper;
    private Dao<MaestraArgu, Integer> entidadDao;

    public MaestraArguRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getMaestraArguDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(MaestraArgu entidad) {
        try {
            entidadDao.create(entidad);
            return entidad.getIdArgu();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
    }


    public MaestraArgu getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MaestraArgu> findAll(int maestra) {
        try {
            QueryBuilder qb = entidadDao.queryBuilder();
            qb.where().eq("IdMaestra", maestra);

            //List<MaestraArgu> asasd = qb.query();
            //int asa = asasd.size();
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
            del.where().ge("IdArgu", 0);
            del.delete();
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }
}
