package com.movil.summmit.motorresapp.Storage.db.repository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFalla;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class InformeTecnicoFallaRepository {

    private DatabaseHelper dbHelper;
    private Dao<InformeTecnicoFalla, Integer> entidadDao;

    public InformeTecnicoFallaRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getInformeTecnicoFallaDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(InformeTecnicoFalla entidad) {
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

    public int update(InformeTecnicoFalla entidad) {
        try {
            return entidadDao.update(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
            //Log.v(TAG, "update exception " + e);
        }

        return 0;
    }

    public int delete(InformeTecnicoFalla entidad) {
        try {
            return entidadDao.deleteById(entidad.getIdInformeTecnicoFalla());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public InformeTecnicoFalla getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InformeTecnicoFalla> findAllxInforme(int IdInforme) {
        try {

            List<InformeTecnicoFalla> listaInforme = new ArrayList<>();
            GenericRawResults<String[]> rawResults =
                    entidadDao.queryRaw(
                            "select " +
                                    "sistema.Nombre as NombreSistema, "+
                                    "modo.Nombre as NombreModoFalla, "+
                                    "inf.NroCaso, " +
                                    "inf.Scanner, " +
                                    "inf.Combustible, " +
                                    "inf.Aceite, " +
                                    "inf.IdInformeTecnicoFalla " +
                                    //"group_concat(emple.Nombre) as NombresTecnicos " +
                                    "from informetecnicofalla as inf " +
                                    "inner join maestraargu as modo on modo.IdArgu = inf.IdArguModoFalla "+
                                    "inner join maestraargu as sistema on sistema.IdArgu = inf.IdArguSistema " +
                                    //"inner join InformeTecnicoFallaxEmpleado as tecnicos on tecnicos.IdInformeTecnicoFalla = inf.IdInformeTecnicoFalla " +
                                    //"inner join Empleado as emple on emple.IdEmpleado = tecnicos.IdEmpleado " +
                                    "where inf.IdInformeTecnico = " + IdInforme + " group by inf.IdInformeTecnicoFalla"

                    );

            List<String[]> results = rawResults.getResults();

            for (String[] result : results) {

                InformeTecnicoFalla objInforme = new InformeTecnicoFalla();
                objInforme.setNombreSistema(result[0]);
                objInforme.setNombreModoFalla(result[1]);
                objInforme.setNroCaso(result[2]);
                objInforme.setScanner( result[3].equalsIgnoreCase("1") ? true : false);
                objInforme.setCombustible( result[4].equalsIgnoreCase("1") ? true : false);
                objInforme.setAceite( result[5].equalsIgnoreCase("1") ? true :  false);
                objInforme.setIdInformeTecnicoFalla(Integer.parseInt(result[6]));
                listaInforme.add(objInforme);
            }

            return  listaInforme;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    public InformeTecnicoFalla findxId(int IdInformeTecnicoFalla) {
        try {

            InformeTecnicoFalla objInforme = new InformeTecnicoFalla();
            GenericRawResults<String[]> rawResults =
                    entidadDao.queryRaw(
                            "select " +
                                    "sistema.Nombre as NombreSistema, "+
                                    "modo.Nombre as NombreModoFalla, "+
                                    "inf.NroCaso, " +
                                    "inf.Scanner, " +
                                    "inf.Combustible, " +
                                    "inf.Aceite, " +
                                    "group_concat(emple.Nombre) as NombresTecnicos, " +
                                    "inf.ArchivoScannerNombre, " +
                                    "inf.ArchivoAceiteNombre, " +
                                    "inf.ArchivoCombustibleNombre, " +
                                    "inf.IdInformeTecnicoFalla " +
                                    "from informetecnicofalla as inf " +
                                    "inner join maestraargu as modo on modo.IdArgu = inf.IdArguModoFalla "+
                                    "inner join maestraargu as sistema on sistema.IdArgu = inf.IdArguSistema " +
                                    "inner join InformeTecnicoFallaxEmpleado as tecnicos on tecnicos.IdInformeTecnicoFalla = inf.IdInformeTecnicoFalla " +
                                    "inner join Empleado as emple on emple.IdEmpleado = tecnicos.IdEmpleado " +
                                    "where inf.IdInformeTecnicoFalla = " + IdInformeTecnicoFalla + " group by inf.IdInformeTecnicoFalla"

                    );

            List<String[]> results = rawResults.getResults();

            for (String[] result : results) {

                objInforme.setNombreSistema(result[0]);
                objInforme.setNombreModoFalla(result[1]);
                objInforme.setNroCaso(result[2]);
                objInforme.setScanner( result[3].equalsIgnoreCase("1") ? true : false);
                objInforme.setCombustible( result[4].equalsIgnoreCase("1") ? true : false);
                objInforme.setAceite( result[5].equalsIgnoreCase("1") ? true :  false);
                objInforme.setNombresTecnicos(result[6]);
                objInforme.setArchivoScannerNombre(result[7]);
                objInforme.setArchivoAceiteNombre(result[8]);
                objInforme.setArchivoCombustibleNombre(result[9]);
                objInforme.setIdInformeTecnicoFalla(Integer.parseInt(result[10]));


            }

            return  objInforme;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public long getNumberOfNotes() {
        QueryBuilder<InformeTecnicoFalla, Integer> qb = entidadDao.queryBuilder();
        try {
            return qb.countOf();
        } catch (SQLException e) {
            return -1;
        }
    }
}
