package com.movil.summmit.motorresapp.Storage.db.repository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaDiagnostico;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class InformeTecnicoFallaDiagnosticoRepository {

    private DatabaseHelper dbHelper;
    private Dao<InformeTecnicoFallaDiagnostico, Integer> entidadDao;

    public InformeTecnicoFallaDiagnosticoRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getInformeTecnicoFallaDiagnosticoDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(InformeTecnicoFallaDiagnostico entidad) {
        try {
            entidadDao.create(entidad);

            return entidad.getIdFallaDiagnostico();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
        //
    }

    public int update(InformeTecnicoFallaDiagnostico entidad) {
        try {
            return entidadDao.update(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
            //Log.v(TAG, "update exception " + e);
        }

        return 0;
    }

    public int delete(InformeTecnicoFallaDiagnostico entidad) {
        try {
            return entidadDao.delete(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public InformeTecnicoFallaDiagnostico getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InformeTecnicoFallaDiagnostico> findAll() {
        try {
            return entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<String> findAllxInformeTecnicoFalla(int IdInformeTecnicoFalla) {
        try {



            List<String> lista = new ArrayList<>();

            GenericRawResults<String[]> rawResults =
                    entidadDao.queryRaw(
                            "select " +
                                    "inf.Descripcion "+
                                    "from InformeTecnicoFallaDiagnostico as inf where inf.IdInformeTecnicoFalla =  " + IdInformeTecnicoFalla );

            List<String[]> results = rawResults.getResults();

            for (String[] result : results) {

                lista.add(result[0]);
            }
            //return entidadDao.queryForAll();
            return  lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InformeTecnicoFallaDiagnostico> AllxInformeTecnicoFalla(int IdInformeTecnicoFalla) {
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
        QueryBuilder<InformeTecnicoFallaDiagnostico, Integer> qb = entidadDao.queryBuilder();
        try {
            return qb.countOf();
        } catch (SQLException e) {
            return -1;
        }
    }
}
