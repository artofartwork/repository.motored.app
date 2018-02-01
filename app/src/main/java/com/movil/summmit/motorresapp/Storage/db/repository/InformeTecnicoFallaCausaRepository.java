package com.movil.summmit.motorresapp.Storage.db.repository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaCausa;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class InformeTecnicoFallaCausaRepository {

    private DatabaseHelper dbHelper;
    private Dao<InformeTecnicoFallaCausa, Integer> entidadDao;

    public InformeTecnicoFallaCausaRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getInformeTecnicoFallaCausaDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(InformeTecnicoFallaCausa entidad) {
        try {
            entidadDao.create(entidad);

            return entidad.getIdInformeTecnicoFalla();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
        //
    }

    public int update(InformeTecnicoFallaCausa entidad) {
        try {
            return entidadDao.update(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
            //Log.v(TAG, "update exception " + e);
        }

        return 0;
    }

    public int delete(InformeTecnicoFallaCausa entidad) {
        try {
            return entidadDao.delete(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public InformeTecnicoFallaCausa getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InformeTecnicoFallaCausa> findAll() {
        try {
            return entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<String> findAllxInformeTecnicoFalla(int IdInformeTecnicoFalla) {
        try {

           /* QueryBuilder qb = entidadDao.queryBuilder();
            qb.selectColumns("Descripcion").prepareStatementString();

            qb.where()
                    .eq("IdInformeTecnicoFalla", IdInformeTecnicoFalla);
            return qb.query();*/
            List<String> lista = new ArrayList<>();

            GenericRawResults<String[]> rawResults =
                    entidadDao.queryRaw(
                            "select " +
                                    "inf.Descripcion "+
                                    "from InformeTecnicoFallaCausa as inf where inf.IdInformeTecnicoFalla =  " + IdInformeTecnicoFalla );

            List<String[]> results = rawResults.getResults();

            for (String[] result : results) {

                lista.add(result[0]);
            }
            //return entidadDao.queryForAll();
            return  lista;
            //return entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InformeTecnicoFallaCausa> AllxInformeTecnicoFalla(int IdInformeTecnicoFalla) {
        try {

            QueryBuilder qb = entidadDao.queryBuilder();
            qb.where()
                    .eq("IdInformeTecnicoFalla", IdInformeTecnicoFalla);
            return qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public long getNumberOfNotes() {
        QueryBuilder<InformeTecnicoFallaCausa, Integer> qb = entidadDao.queryBuilder();
        try {
            return qb.countOf();
        } catch (SQLException e) {
            return -1;
        }
    }
}
