package com.movil.summmit.motorresapp.Storage.db.repository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaCorrectivos;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class InformeTecnicoFallaCorrectivosRepository {

    private DatabaseHelper dbHelper;
    private Dao<InformeTecnicoFallaCorrectivos, Integer> entidadDao;

    public InformeTecnicoFallaCorrectivosRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getInformeTecnicoFallaCorrectivosDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(InformeTecnicoFallaCorrectivos entidad) {
        try {
            entidadDao.create(entidad);

            return entidad.getIdFallaCorrectivos();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
        //
    }

    public int update(InformeTecnicoFallaCorrectivos entidad) {
        try {
            return entidadDao.update(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
            //Log.v(TAG, "update exception " + e);
        }

        return 0;
    }

    public int delete(InformeTecnicoFallaCorrectivos entidad) {
        try {
            return entidadDao.delete(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public InformeTecnicoFallaCorrectivos getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InformeTecnicoFallaCorrectivos> findAll() {
        try {
            return entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<String> findAllxInformeTecnicoFalla(int IdInformeTecnicoFalla) {
        try {

            /*QueryBuilder qb = entidadDao.queryBuilder();
            qb.selectColumns("Descripcion").prepareStatementString();
            qb.where()
                    .eq("IdInformeTecnicoFalla", IdInformeTecnicoFalla);
            return qb.query();*/
            List<String> lista = new ArrayList<>();

            GenericRawResults<String[]> rawResults =
                    entidadDao.queryRaw(
                            "select " +
                                    "inf.Descripcion "+
                                    "from InformeTecnicoFallaCorrectivos as inf where inf.IdInformeTecnicoFalla =  " + IdInformeTecnicoFalla );

            List<String[]> results = rawResults.getResults();

            for (String[] result : results) {

                lista.add(result[0]);
            }

            return lista;
            //return entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InformeTecnicoFallaCorrectivos> AllxInformeTecnicoFalla(int IdInformeTecnicoFalla) {
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
        QueryBuilder<InformeTecnicoFallaCorrectivos, Integer> qb = entidadDao.queryBuilder();
        try {
            return qb.countOf();
        } catch (SQLException e) {
            return -1;
        }
    }
}
