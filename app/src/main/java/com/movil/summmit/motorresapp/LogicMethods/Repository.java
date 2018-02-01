package com.movil.summmit.motorresapp.LogicMethods;

import android.content.Context;

import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAdjuntos;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoAdjuntosDetalleRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoAdjuntosRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoAntecedenteRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoConclusionesRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoFallaCausaRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoFallaCorrectivosRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoFallaDiagnosticoRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoFallaRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoFallaxEmpleadoRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoRecomendacionesRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.CasoTecnicoRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.ClienteRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.EmpleadoRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.EmpresaRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.MaestraArguRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.MarcaRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.ModeloRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.VinRepository;

/**
 * Created by cgonzalez on 29/01/2018.
 */

public class Repository {

    private InformeTecnicoRepository informeTecnicoRepository;
    private InformeTecnicoAntecedenteRepository informeTecnicoAntecedenteRepository;
    private EmpresaRepository empresaRepository;
    private MarcaRepository marcaRepository;
    private ModeloRepository modeloRepository;
    private MaestraArguRepository maestraArguRepository;
    private ClienteRepository clienteRepository;
    private VinRepository vinRepository;

    private InformeTecnicoFallaxEmpleadoRepository informeTecnicoFallaxEmpleadoRepository;
    private CasoTecnicoRepository casoTecnicoRepository;
    private EmpleadoRepository empleadoRepository;
    private InformeTecnicoFallaRepository informeTecnicoFallaRepository;
    private InformeTecnicoFallaDiagnosticoRepository informeTecnicoFallaDiagnosticoRepository;
    private InformeTecnicoFallaCausaRepository informeTecnicoFallaCausaRepository;
    private InformeTecnicoFallaCorrectivosRepository informeTecnicoFallaCorrectivosRepository;

    private InformeTecnicoAdjuntosDetalleRepository informeTecnicoAdjuntosDetalleRepository;
    private InformeTecnicoConclusionesRepository informeTecnicoConclusionesRepository;
    private InformeTecnicoRecomendacionesRepository informeTecnicoRecomendacionesRepository;
    private InformeTecnicoAdjuntosRepository informeTecnicoAdjuntosRepository;

    private Context ctx;

    public Repository(Context ctx)
    {
        this.ctx = ctx;
    }

    public InformeTecnicoRepository informeTecnicoRepository()
    {
        return informeTecnicoRepository = new InformeTecnicoRepository(ctx);
    }

    public InformeTecnicoAntecedenteRepository informeTecnicoAntecedenteRepository()
    {
        return informeTecnicoAntecedenteRepository = new InformeTecnicoAntecedenteRepository(ctx);
    }

    public EmpresaRepository empresaRepository()
    {
        return empresaRepository = new EmpresaRepository(ctx);
    }
    public MarcaRepository marcaRepository()
    {
        return marcaRepository = new MarcaRepository(ctx);
    }

    public ModeloRepository modeloRepository()
    {
        return modeloRepository = new ModeloRepository(ctx);
    }

    public MaestraArguRepository maestraArguRepository()
    {
        return maestraArguRepository = new MaestraArguRepository(ctx);
    }

    public ClienteRepository clienteRepository()
    {
        return clienteRepository = new ClienteRepository(ctx);
    }
    public VinRepository vinRepository()
    {
        return vinRepository = new VinRepository(ctx);
    }

    public InformeTecnicoFallaxEmpleadoRepository informeTecnicoFallaxEmpleadoRepository()
    {
        return informeTecnicoFallaxEmpleadoRepository = new InformeTecnicoFallaxEmpleadoRepository(ctx);
    }

    public CasoTecnicoRepository casoTecnicoRepository()
    {
        return casoTecnicoRepository = new CasoTecnicoRepository(ctx);
    }

    public EmpleadoRepository empleadoRepository()
    {
        return empleadoRepository = new EmpleadoRepository(ctx);
    }

    public InformeTecnicoFallaRepository informeTecnicoFallaRepository()
    {
        return informeTecnicoFallaRepository = new InformeTecnicoFallaRepository(ctx);
    }

    public InformeTecnicoFallaDiagnosticoRepository informeTecnicoFallaDiagnosticoRepository()
    {
        return informeTecnicoFallaDiagnosticoRepository = new InformeTecnicoFallaDiagnosticoRepository(ctx);
    }

    public InformeTecnicoFallaCausaRepository informeTecnicoFallaCausaRepository()
    {
        return informeTecnicoFallaCausaRepository = new InformeTecnicoFallaCausaRepository(ctx);
    }
    public InformeTecnicoFallaCorrectivosRepository informeTecnicoFallaCorrectivosRepository()
    {
        return informeTecnicoFallaCorrectivosRepository = new InformeTecnicoFallaCorrectivosRepository(ctx);
    }
    public InformeTecnicoAdjuntosDetalleRepository informeTecnicoAdjuntosDetalleRepository()
    {
        return informeTecnicoAdjuntosDetalleRepository = new InformeTecnicoAdjuntosDetalleRepository(ctx);
    }

    public InformeTecnicoConclusionesRepository informeTecnicoConclusionesRepository()
    {
        return informeTecnicoConclusionesRepository = new InformeTecnicoConclusionesRepository(ctx);
    }

    public InformeTecnicoRecomendacionesRepository informeTecnicoRecomendacionesRepository()
    {
        return informeTecnicoRecomendacionesRepository = new InformeTecnicoRecomendacionesRepository(ctx);
    }

    public InformeTecnicoAdjuntosRepository informeTecnicoAdjuntosRepository()
    {
        return informeTecnicoAdjuntosRepository = new InformeTecnicoAdjuntosRepository(ctx);
    }
}


