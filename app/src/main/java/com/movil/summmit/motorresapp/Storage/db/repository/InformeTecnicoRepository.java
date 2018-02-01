package com.movil.summmit.motorresapp.Storage.db.repository;
import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnico;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class InformeTecnicoRepository {

   // private static final String TAG = "InformeTecnicoRepository";
    private DatabaseHelper dbHelper;
    private Dao<InformeTecnico, Integer> entidadDao;

    public InformeTecnicoRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getInformeTecnicosDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(InformeTecnico entidad) {
        try {
            entidadDao.create(entidad);

            return entidad.getIdInformeTecnico();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
       //
    }

    public int update(InformeTecnico entidad) {
        try {
            return entidadDao.update(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
            //Log.v(TAG, "update exception " + e);
        }

        return 0;
    }

    public int delete(InformeTecnico entidad) {
        try {
            return entidadDao.delete(entidad);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public InformeTecnico getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<InformeTecnico> findAll() {
        try {

            List<InformeTecnico> listaInforme = new ArrayList<>();
// find out how many orders account-id #10 has
            GenericRawResults<String[]> rawResults =
                    entidadDao.queryRaw(
                            "select " +
                                    "inf.Estado, "+
                                    "inf.AudFechaRegistro, "+
                                    "inf.Observacion, " +
                                    "inf.Serie, " +
                                    "cli.Nombres as NombreCliente, " +
                                    "sucu.Nombre as NombreSucursal, "+
                                    "mod.Descripcion as NombreModelo, "+
                                    "inf.IdInformeTecnico " +
                                    "from informetecnico as inf " +
                                    "inner join cliente as cli on inf.IdCliente = cli.IdCliente "+
                                    "inner join maestraargu as sucu on sucu.IdArgu = inf.IdArguSucursal "+
                                    "inner join modelo as mod on mod.IdModelo = inf.IdModelo "
                                     );
// there should be 1 result
            List<String[]> results = rawResults.getResults();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


            for (String[] result : results) {

                InformeTecnico objInforme = new InformeTecnico();
                objInforme.setEstado(result[0]);

                java.util.Date utilDate = format.parse(result[1]);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                objInforme.setAudFechaRegistro(sqlDate);

                objInforme.setObservacion(result[2]);
                objInforme.setSerie(result[3]);
                objInforme.setNombreCliente(result[4]);
                objInforme.setNombreSucursal(result[5]);
                objInforme.setNombreModelo(result[6]);
                objInforme.setIdInformeTecnico(Integer.parseInt(result[7]));

                listaInforme.add(objInforme);
            }
// the results array should have 1 value
            //String[] resultArray = results.get(0);
// this should print the number of orders that have this account-id
           // System.out.println("Account-id 10 has " + resultArray[0] + " orders");

            return  listaInforme;//entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public InformeTecnico findEntidad(int IdInforme) {
        try {

            GenericRawResults<String[]> rawResults =
                    entidadDao.queryRaw(
                            "select " +
                                    "emp.Nombre as NombreEmpresa, " +
                                    "vn.vin as NombreVin, " +
                                    "inf.NroOT, " +
                                    "marc.Descripcion as NombreMarca, " +
                                    "area.Nombre as NombreArea, "+
                                    "ap.Nombre as NombreAplicacion, "+
                                    "inf.Estado, "+
                                    "inf.FechaInicio, "+
                                    "inf.FechaFalla, "+
                                    "inf.Observacion, " +
                                    "inf.Serie, " +
                                    "inf.Kilometraje, "+
                                    "inf.Horometro, "+
                                    "compo.Nombre as NombreComponente, "+
                                    "cli.Nombres as NombreCliente, " +
                                    "sucu.Nombre as NombreSucursal, "+
                                    "mod.Descripcion as NombreModelo, "+

                                    "inf.IdInformeTecnico " +
                                    "from informetecnico as inf " +
                                    "inner join cliente as cli on inf.IdCliente = cli.IdCliente "+
                                    "inner join maestraargu as sucu on sucu.IdArgu = inf.IdArguSucursal "+
                                    "inner join modelo as mod on mod.IdModelo = inf.IdModelo "+
                                    "inner join empresa as emp on emp.IdEmpresa = inf.IdEmpresa " +
                                    "inner join maestraargu as area on area.IdArgu = inf.IdArguArea " +
                                    "inner join marca as marc on marc.IdMarca = inf.IdMarca " +
                                    "inner join vin as vn on vn.IdChasis = inf.VIN " +
                                    "inner join maestraargu compo ON compo.IdArgu = inf.IdArguComponente " +
                                    "inner join maestraargu ap on ap.IdArgu = inf.IdArguAplicacion where inf.IdInformeTecnico = " + IdInforme
                    );

            List<String[]> results = rawResults.getResults();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            InformeTecnico objInforme = new InformeTecnico();
            for (String[] result : results) {

                objInforme.setNombreEmpresa(result[0]);
                objInforme.setNombreVin(result[1]);
                objInforme.setNroOT(result[2]);
                objInforme.setNombreMarca(result[3]);
                objInforme.setNombreArea(result[4]);

                objInforme.setNombreAplicacion(result[5]);
                objInforme.setEstado(result[6]);

                java.util.Date utilDate = format.parse(result[7]);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                objInforme.setFechaInicio(sqlDate);

                java.util.Date utilDatefa = format.parse(result[8]);
                java.sql.Date sqlDatefa = new java.sql.Date(utilDatefa.getTime());
                objInforme.setFechaFalla(sqlDatefa);

                objInforme.setObservacion(result[9]);
                objInforme.setSerie(result[10]);
                objInforme.setKilometraje( Double.parseDouble(result[11]));
                objInforme.setHorometro( Double.parseDouble(result[12]));

                objInforme.setNombreComponente(result[13]);
                objInforme.setNombreCliente(result[14]);
                objInforme.setNombreSucursal(result[15]);
                objInforme.setNombreModelo(result[16]);
                objInforme.setIdInformeTecnico( Integer.parseInt(result[17]));




            }
// the results array should have 1 value
            //String[] resultArray = results.get(0);
// this should print the number of orders that have this account-id
            // System.out.println("Account-id 10 has " + resultArray[0] + " orders");

            return  objInforme;//entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();

            Log.d("selexcep", "-> " + e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("parseexcep", "-> " + e.getMessage());
        }

        return null;
    }

    public List<InformeTecnico> findAllporParametros(int IdEmpresa, int IdSucursal, int IdArea, int IdMarca, int IdModelo) {
        try {

            QueryBuilder qb = entidadDao.queryBuilder();
            qb.where()
                    .eq("IdEmpresa", IdEmpresa).and()
                    .eq("IdArguSucursal", IdSucursal)
                     .and()
                    .eq("IdArguArea", IdArea)
                    .and()
                    .eq("IdMarca", IdMarca)
                    .and()
                    .eq("IdModelo", IdModelo);
            return qb.query();

        } catch (SQLException e) {
            e.printStackTrace();}


        return null;
    }


    public long getNumberOfNotes() {
        QueryBuilder<InformeTecnico, Integer> qb = entidadDao.queryBuilder();
        try {
            return qb.countOf();
        } catch (SQLException e) {
            return -1;
        }
    }


}
