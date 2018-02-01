package com.movil.summmit.motorresapp.Storage.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnico;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAdjuntos;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAdjuntosDetalle;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAntecedente;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoConclusiones;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFalla;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaCausa;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaCorrectivos;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaDiagnostico;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaxEmpleado;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoRecomendaciones;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.CasoTecnico;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Cliente;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empleado;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empresa;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Maestra;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.MaestraArgu;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Marca;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Modelo;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Usuario;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Vin;

import android.util.Log.*;
/**
 * Created by TELEFONICA on 2/12/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "motoredapp.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<InformeTecnico,Integer> InformeTecnicoDAO = null;
    private Dao<InformeTecnicoAdjuntos,Integer> InformeTecnicoAdjuntosDAO = null;
    private Dao<InformeTecnicoAdjuntosDetalle,Integer> InformeTecnicoAdjuntosDetalleDAO = null;
    private Dao<InformeTecnicoAntecedente,Integer> InformeTecnicoAntecedenteDAO = null;
    private Dao<InformeTecnicoConclusiones,Integer> InformeTecnicoConclusionesDAO = null;
    private Dao<InformeTecnicoFalla,Integer> InformeTecnicoFallaDAO = null;
    private Dao<InformeTecnicoFallaCausa,Integer> InformeTecnicoFallaCausaDAO = null;
    private Dao<InformeTecnicoFallaCorrectivos,Integer> InformeTecnicoFallaCorrectivosDAO = null;
    private Dao<InformeTecnicoFallaDiagnostico,Integer> InformeTecnicoFallaDiagnosticoDAO = null;
    private Dao<InformeTecnicoRecomendaciones,Integer> InformeTecnicoRecomendacionesDAO = null;
    private Dao<InformeTecnicoFallaxEmpleado,Integer> InformeTecnicoFallaxEmpleadoDAO = null;

    private Dao<CasoTecnico,Integer> CasoTecnicoDAO = null;
    private Dao<Cliente,Integer> ClienteDAO = null;
    private Dao<Empleado,Integer> EmpleadoDAO = null;
    private Dao<Empresa,Integer> EmpresaDAO = null;
    private Dao<Maestra,Integer> MaestraDAO = null;
    private Dao<MaestraArgu,Integer> MaestraArguDAO = null;
    private Dao<Modelo,Integer> ModeloDAO = null;
    private Dao<Vin,Integer> VinDAO = null;
    private Dao<Usuario,Integer> UsuarioDAO = null;
    private Dao<Marca,Integer> MarcaDAO = null;

   // private RuntimeExceptionDao<DatosGenerales, Integer> datosGeneralesRuntimeDao = null;

    public DatabaseHelper (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource source)
    {
        try
        {
            android.util.Log.i(DatabaseHelper.class.getSimpleName(), "omccreate()");
            //tablas generales
            TableUtils.createTable(source, InformeTecnico.class);
            TableUtils.createTable(source, InformeTecnicoAdjuntos.class);
            TableUtils.createTable(source, InformeTecnicoAdjuntosDetalle.class);
            TableUtils.createTable(source, InformeTecnicoAntecedente.class);
            TableUtils.createTable(source, InformeTecnicoConclusiones.class);
            TableUtils.createTable(source, InformeTecnicoFalla.class);
            TableUtils.createTable(source, InformeTecnicoFallaCausa.class);
            TableUtils.createTable(source, InformeTecnicoFallaCorrectivos.class);
            TableUtils.createTable(source, InformeTecnicoFallaDiagnostico.class);
            TableUtils.createTable(source, InformeTecnicoRecomendaciones.class);
            TableUtils.createTable(source, InformeTecnicoFallaxEmpleado.class);

            //tablas maestra

            TableUtils.createTable(source, CasoTecnico.class);
            TableUtils.createTable(source, Cliente.class);
            TableUtils.createTable(source, Empleado.class);
            TableUtils.createTable(source, Empresa.class);
            TableUtils.createTable(source, Maestra.class);
            TableUtils.createTable(source, MaestraArgu.class);
            TableUtils.createTable(source, Modelo.class);
            TableUtils.createTable(source, Vin.class);
            TableUtils.createTable(source, Usuario.class);
            TableUtils.createTable(source, Marca.class);

        }
        catch (SQLException ex)
        {
            android.util.Log.e(DatabaseHelper.class.getSimpleName(), "imposible crear la base d datos");
        } catch (java.sql.SQLException e) {
            android.util.Log.e(DatabaseHelper.class.getSimpleName(), "imposible crear la base d datos");
            e.printStackTrace();
        }

    }

    /*SE EJECUTA CADA VEZ Q LA APLICACION NO EXISTE O CUANDO SE INSTALA LA APP*/
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource source, int oldVersion, int newVersion)
    {
        android.util.Log.i(DatabaseHelper.class.getSimpleName(), "onUpgrade()");
        try {

            TableUtils.dropTable(source, InformeTecnico.class, true);
            TableUtils.dropTable(source, InformeTecnicoAdjuntos.class, true);
            TableUtils.dropTable(source, InformeTecnicoAdjuntosDetalle.class, true);
            TableUtils.dropTable(source, InformeTecnicoAntecedente.class, true);
            TableUtils.dropTable(source, InformeTecnicoConclusiones.class, true);
            TableUtils.dropTable(source, InformeTecnicoFalla.class, true);
            TableUtils.dropTable(source, InformeTecnicoFallaCausa.class, true);
            TableUtils.dropTable(source, InformeTecnicoFallaCorrectivos.class, true);
            TableUtils.dropTable(source, InformeTecnicoFallaDiagnostico.class, true);
            TableUtils.dropTable(source, InformeTecnicoRecomendaciones.class, true);
            TableUtils.dropTable(source, InformeTecnicoFallaxEmpleado.class, true);

            TableUtils.dropTable(source, CasoTecnico.class, true);
            TableUtils.dropTable(source, Cliente.class, true);
            TableUtils.dropTable(source, Empleado.class, true);
            TableUtils.dropTable(source, Empresa.class, true);
            TableUtils.dropTable(source, Maestra.class, true);
            TableUtils.dropTable(source, MaestraArgu.class, true);
            TableUtils.dropTable(source, Modelo.class, true);
            TableUtils.dropTable(source, Vin.class, true);
            TableUtils.dropTable(source, Usuario.class, true);
            TableUtils.dropTable(source, Marca.class, true);
            onCreate(database, source);

        } catch (java.sql.SQLException e) {

            android.util.Log.i(DatabaseHelper.class.getSimpleName(), "error en onUpgrade");
            e.printStackTrace();

        }
    }

  /*  public RuntimeExceptionDao<DatosGenerales, Integer> getDatosGeneralesRuntimeDao() {

        if (datosGeneralesRuntimeDao == null){
            datosGeneralesRuntimeDao = getRuntimeExceptionDao(DatosGenerales.class);
        }
        return datosGeneralesRuntimeDao;
    }*/
    @Override
    public void close() {
        super.close();
        InformeTecnicoDAO = null;
        InformeTecnicoAdjuntosDAO = null;
        InformeTecnicoAdjuntosDetalleDAO = null;
        InformeTecnicoAntecedenteDAO = null;
        InformeTecnicoConclusionesDAO = null;
        InformeTecnicoFallaDAO = null;
        InformeTecnicoFallaCausaDAO = null;
        InformeTecnicoFallaCorrectivosDAO = null;
        InformeTecnicoFallaDiagnosticoDAO = null;
        InformeTecnicoRecomendacionesDAO = null;
        InformeTecnicoFallaxEmpleadoDAO = null;

        CasoTecnicoDAO = null;
        ClienteDAO = null;
        EmpleadoDAO = null;
        EmpresaDAO = null;
        MaestraDAO = null;
        MaestraArguDAO = null;
        ModeloDAO = null;
        VinDAO = null;
        UsuarioDAO = null;
        MarcaDAO = null;

    }

    public Dao<InformeTecnicoFallaxEmpleado, Integer> getInformeTecnicoFallaxEmpleadoDAO() throws java.sql.SQLException {

        if (InformeTecnicoFallaxEmpleadoDAO == null){
            InformeTecnicoFallaxEmpleadoDAO = getDao(InformeTecnicoFallaxEmpleado.class);
        }
        return InformeTecnicoFallaxEmpleadoDAO;
    }


    public Dao<InformeTecnico, Integer> getInformeTecnicosDAO() throws java.sql.SQLException {

        if (InformeTecnicoDAO == null){
            InformeTecnicoDAO = getDao(InformeTecnico.class);
        }
        return InformeTecnicoDAO;
    }

    public Dao<InformeTecnicoAdjuntos, Integer> getInformeTecnicoAdjuntosDAO()  throws java.sql.SQLException {
        if (InformeTecnicoAdjuntosDAO == null){
            InformeTecnicoAdjuntosDAO = getDao(InformeTecnicoAdjuntos.class);
        }
        return InformeTecnicoAdjuntosDAO;

    }


    public Dao<InformeTecnicoAdjuntosDetalle, Integer> getInformeTecnicoAdjuntosDetalleDAO() throws java.sql.SQLException {
        if (InformeTecnicoAdjuntosDetalleDAO == null){
            InformeTecnicoAdjuntosDetalleDAO = getDao(InformeTecnicoAdjuntosDetalle.class);
        }
        return InformeTecnicoAdjuntosDetalleDAO;
    }

    public Dao<InformeTecnicoAntecedente, Integer> getInformeTecnicoAntecedenteDAO() throws java.sql.SQLException {

        if (InformeTecnicoAntecedenteDAO == null){
            InformeTecnicoAntecedenteDAO = getDao(InformeTecnicoAntecedente.class);
        }
        return InformeTecnicoAntecedenteDAO;
    }

    public Dao<InformeTecnicoConclusiones, Integer> getInformeTecnicoConclusionesDAO() throws java.sql.SQLException {

        if (InformeTecnicoConclusionesDAO == null){
            InformeTecnicoConclusionesDAO = getDao(InformeTecnicoConclusiones.class);
        }
        return InformeTecnicoConclusionesDAO;
    }

    public Dao<InformeTecnicoFalla, Integer> getInformeTecnicoFallaDAO() throws java.sql.SQLException {

        if (InformeTecnicoFallaDAO == null){
            InformeTecnicoFallaDAO = getDao(InformeTecnicoFalla.class);
        }
        return InformeTecnicoFallaDAO;
    }

    public Dao<InformeTecnicoFallaCausa, Integer> getInformeTecnicoFallaCausaDAO() throws java.sql.SQLException {

        if (InformeTecnicoFallaCausaDAO == null){
            InformeTecnicoFallaCausaDAO = getDao(InformeTecnicoFallaCausa.class);
        }
        return InformeTecnicoFallaCausaDAO;
    }

    public Dao<InformeTecnicoFallaCorrectivos, Integer> getInformeTecnicoFallaCorrectivosDAO() throws java.sql.SQLException {

        if (InformeTecnicoFallaCorrectivosDAO == null){
            InformeTecnicoFallaCorrectivosDAO = getDao(InformeTecnicoFallaCorrectivos.class);
        }
        return InformeTecnicoFallaCorrectivosDAO;
    }

    public Dao<InformeTecnicoFallaDiagnostico, Integer> getInformeTecnicoFallaDiagnosticoDAO() throws java.sql.SQLException {

        if (InformeTecnicoFallaDiagnosticoDAO == null){
            InformeTecnicoFallaDiagnosticoDAO = getDao(InformeTecnicoFallaDiagnostico.class);
        }
        return InformeTecnicoFallaDiagnosticoDAO;
    }

    public Dao<InformeTecnicoRecomendaciones, Integer> getInformeTecnicoRecomendacionesDAO() throws java.sql.SQLException {
        if (InformeTecnicoRecomendacionesDAO == null){
            InformeTecnicoRecomendacionesDAO = getDao(InformeTecnicoRecomendaciones.class);
        }
        return InformeTecnicoRecomendacionesDAO;
    }

    public Dao<CasoTecnico, Integer> getCasoTecnicoDAO() throws java.sql.SQLException {

        if (CasoTecnicoDAO == null){
            CasoTecnicoDAO = getDao(CasoTecnico.class);
        }
        return CasoTecnicoDAO;
    }

    public Dao<Cliente, Integer> getClienteDAO() throws java.sql.SQLException {

        if (ClienteDAO == null){
            ClienteDAO = getDao(Cliente.class);
        }
        return ClienteDAO;
    }

    public Dao<Empleado, Integer> getEmpleadoDAO() throws java.sql.SQLException {

        if (EmpleadoDAO == null){
            EmpleadoDAO = getDao(Empleado.class);
        }
        return EmpleadoDAO;
    }

    public Dao<Empresa, Integer> getEmpresaDAO() throws java.sql.SQLException {
        if (EmpresaDAO == null){
            EmpresaDAO = getDao(Empresa.class);
        }
        return EmpresaDAO;
    }

    public Dao<Maestra, Integer> getMaestraDAO() throws java.sql.SQLException {

        if (MaestraDAO == null){
            MaestraDAO = getDao(Maestra.class);
        }
        return MaestraDAO;
    }

    public Dao<MaestraArgu, Integer> getMaestraArguDAO() throws java.sql.SQLException {

        if (MaestraArguDAO == null){
            MaestraArguDAO = getDao(MaestraArgu.class);
        }
        return MaestraArguDAO;
    }

    public Dao<Modelo, Integer> getModeloDAO() throws java.sql.SQLException {

        if (ModeloDAO == null){
            ModeloDAO = getDao(Modelo.class);
        }
        return ModeloDAO;
    }

    public Dao<Vin, Integer> getVinDAO() throws java.sql.SQLException {

        if (VinDAO == null){
            VinDAO = getDao(Vin.class);
        }
        return VinDAO;
    }

    public Dao<Usuario, Integer> getUsuarioDAO() throws java.sql.SQLException {

        if (UsuarioDAO == null){
            UsuarioDAO = getDao(Usuario.class);
        }
        return UsuarioDAO;
    }

    public Dao<Marca, Integer> getMarcaDAO() throws java.sql.SQLException {
        if (MarcaDAO == null){
            MarcaDAO = getDao(Marca.class);
        }
        return MarcaDAO;
    }
}
