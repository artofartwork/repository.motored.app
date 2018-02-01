package com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.CasoTecnico;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class CasoTecnicoRepository {
    private DatabaseHelper dbHelper;
    private Dao<CasoTecnico, Integer> entidadDao;

    public CasoTecnicoRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getCasoTecnicoDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(CasoTecnico entidad) {
        try {
            entidadDao.create(entidad);
            return entidad.getIdCasoTecnico();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
    }


    public CasoTecnico getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CasoTecnico> findAllxSistemaFalla(int IdSistemaFalla) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
           // return entidadDao.queryForAll();
            List<CasoTecnico> lista = new ArrayList<>();
// find out how many orders account-id #10 has
            GenericRawResults<String[]> rawResults =
                    entidadDao.queryRaw(
                            "select " +
                                    "caso.IdCasoTecnico, "+
                                    "marc.Descripcion as NombreMarca, "+
                                    "mod.Descripcion as NombreModelo, "+
                                    "caso.Criticidad, " +
                                    "caso.FechaFalla " +
                                    "from casotecnico as caso " +
                                    "inner join marca as marc on caso.IdMarca = marc.IdMarca "+
                                    "inner join modelo as mod on mod.IdModelo = caso.IdModelo where caso.IdComponente = " + IdSistemaFalla
                    );

            List<String[]> results = rawResults.getResults();
           // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


            for (String[] result : results) {

                CasoTecnico objCaso = new CasoTecnico();
                objCaso.setIdCasoTecnico( Integer.parseInt(result[0]));
                objCaso.setNombreMarca(result[1]);
                objCaso.setNombreModelo(result[2]);
                objCaso.setCriticidad(result[3]);

              //  java.util.Date utilDate = format.parse(result[4]);
                //java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                objCaso.setFechaFalla(result[4]);

                lista.add(objCaso);
            }

            return  lista;//entidadDao.queryForAll();

        } catch (SQLException e) {
            Log.d("caso tec repo", "err -> " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Boolean deleteAllRows()
    {
        DeleteBuilder del = entidadDao.deleteBuilder();
        try
        {
            del.where().ge("IdCasoTecnico", 0);
            del.delete();
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }
}
