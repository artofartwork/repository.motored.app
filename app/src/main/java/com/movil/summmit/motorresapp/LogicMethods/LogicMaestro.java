package com.movil.summmit.motorresapp.LogicMethods;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.movil.summmit.motorresapp.Models.Enity.InformeTecnico;
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
import com.movil.summmit.motorresapp.Request.ApiClienteInformes;
import com.movil.summmit.motorresapp.Request.ApiClienteMaestros;
import com.movil.summmit.motorresapp.Request.ReturnValue;
import com.movil.summmit.motorresapp.Storage.Files.FilesControl;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.CasoTecnicoRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.ClienteRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.EmpleadoRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.EmpresaRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.MaestraArguRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.MaestraRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.MarcaRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.ModeloRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.UsuarioRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.VinRepository;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cgonzalez on 24/01/2018.
 */

public class LogicMaestro {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private Context ctx;
    EmpresaRepository empresaRepository;
    CasoTecnicoRepository casoTecnicoRepository;
    ClienteRepository clienteRepository;
    EmpleadoRepository empleadoRepository;
    MaestraRepository maestraRepository;
    MaestraArguRepository maestraArguRepository;
    MarcaRepository marcaRepository;
    ModeloRepository modeloRepository;
    UsuarioRepository usuarioRepository;
    VinRepository vinRepository;
    public LogicMaestro(Context ctx)
    {
        this.ctx = ctx;
    }




    public int SyncEmpresa()  // 1 es exito, 0 es fallado
    {
        empresaRepository = new EmpresaRepository(ctx);

        try {
            Call<List<Empresa>> call= ApiClienteMaestros.getMyApiClient().listaEmpresa();
            call.enqueue(new Callback<List<Empresa>>() {
                @Override
                public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {

                    if(response!=null && response.isSuccessful()){
                        List<Empresa> listaResponse = response.body();

                        //limpio todo
                        empresaRepository.deleteAllRows();
                        for (Empresa obj : listaResponse)
                        {
                            empresaRepository.create(obj);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Empresa>> call, Throwable t) {

                }
            });


            return 1;

        }catch (Exception e)
        {
            return 0;
        }



    }

    public int SyncCasoTecnico()
    {
        casoTecnicoRepository = new CasoTecnicoRepository(ctx);

        try {
            Call<List<CasoTecnico>> call= ApiClienteMaestros.getMyApiClient().listaCasoTecnico();
            call.enqueue(new Callback<List<CasoTecnico>>() {
                @Override
                public void onResponse(Call<List<CasoTecnico>> call, Response<List<CasoTecnico>> response) {

                    if(response!=null && response.isSuccessful()){
                        List<CasoTecnico> listaResponse = response.body();

                        //limpio todo
                        casoTecnicoRepository.deleteAllRows();

                        for (CasoTecnico obj : listaResponse)
                        {

                            casoTecnicoRepository.create(obj);


                        }
                    }
                }

                @Override
                public void onFailure(Call<List<CasoTecnico>> call, Throwable t) {
                    Log.d("caso.tec.fechfalla", "-> " + t.getMessage());
                }
            });


            return 1;

        }catch (Exception e)
        {
            Log.d("casotecnico metodo", "-> " + e.getMessage());
            return 0;
        }
    }

    public int SyncCliente()
    {
        clienteRepository = new ClienteRepository(ctx);

        try {
            Call<List<Cliente>> call= ApiClienteMaestros.getMyApiClient().listaCliente();
            call.enqueue(new Callback<List<Cliente>>() {
                @Override
                public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {

                    if(response!=null && response.isSuccessful()){
                        List<Cliente> listaResponse = response.body();

                        //limpio todo
                        clienteRepository.deleteAllRows();
                        for (Cliente obj : listaResponse)
                        {
                            clienteRepository.create(obj);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Cliente>> call, Throwable t) {

                }
            });


            return 1;

        }catch (Exception e)
        {
            return 0;
        }
    }

    public int SyncEmpleado()
    {
        empleadoRepository = new EmpleadoRepository(ctx);

        try {
            Call<List<Empleado>> call= ApiClienteMaestros.getMyApiClient().listaEmpleado();
            call.enqueue(new Callback<List<Empleado>>() {
                @Override
                public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {

                    if(response!=null && response.isSuccessful()){
                        List<Empleado> listaResponse = response.body();

                        //limpio todo
                        empleadoRepository.deleteAllRows();
                        for (Empleado obj : listaResponse)
                        {
                           empleadoRepository.create(obj);


                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Empleado>> call, Throwable t) {

                }
            });


            return 1;

        }catch (Exception e)
        {
            return 0;
        }
    }

    public int SyncMaestra()
    {
        maestraRepository = new MaestraRepository(ctx);

        try {
            Call<List<Maestra>> call= ApiClienteMaestros.getMyApiClient().listaMaestra();
            call.enqueue(new Callback<List<Maestra>>() {
                @Override
                public void onResponse(Call<List<Maestra>> call, Response<List<Maestra>> response) {

                    if(response!=null && response.isSuccessful()){
                        List<Maestra> listaResponse = response.body();

                        //limpio todo
                        maestraRepository.deleteAllRows();
                        for (Maestra obj : listaResponse)
                        {
                            maestraRepository.create(obj);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Maestra>> call, Throwable t) {
                    Log.d("maestra", "-> " + t.getMessage());
                }
            });


            return 1;

        }catch (Exception e)
        {
            return 0;
        }
    }
    public int SyncMaestraArgu()
    {
        maestraArguRepository = new MaestraArguRepository(ctx);

        try {
            Call<List<MaestraArgu>> call= ApiClienteMaestros.getMyApiClient().listaMaestraArgu();
            call.enqueue(new Callback<List<MaestraArgu>>() {
                @Override
                public void onResponse(Call<List<MaestraArgu>> call, Response<List<MaestraArgu>> response) {

                    if(response!=null && response.isSuccessful()){
                        List<MaestraArgu> listaResponse = response.body();

                        //limpio todo
                        maestraArguRepository.deleteAllRows();
                        for (MaestraArgu obj : listaResponse)
                        {
                            maestraArguRepository.create(obj);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<MaestraArgu>> call, Throwable t) {

                }
            });


            return 1;

        }catch (Exception e)
        {
            return 0;
        }
    }

    public int SyncMarca()
    {
        marcaRepository = new MarcaRepository(ctx);

        try {
            Call<List<Marca>> call= ApiClienteMaestros.getMyApiClient().listaMarca();
            call.enqueue(new Callback<List<Marca>>() {
                @Override
                public void onResponse(Call<List<Marca>> call, Response<List<Marca>> response) {

                    if(response!=null && response.isSuccessful()){
                        List<Marca> listaResponse = response.body();

                        //limpio todo
                        marcaRepository.deleteAllRows();
                        for (Marca obj : listaResponse)
                        {
                            marcaRepository.create(obj);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Marca>> call, Throwable t) {

                }
            });


            return 1;

        }catch (Exception e)
        {
            return 0;
        }
    }

    public int SyncModelo()
    {
        modeloRepository = new ModeloRepository(ctx);

        try {
            Call<List<Modelo>> call= ApiClienteMaestros.getMyApiClient().listaModelo();
            call.enqueue(new Callback<List<Modelo>>() {
                @Override
                public void onResponse(Call<List<Modelo>> call, Response<List<Modelo>> response) {

                    if(response!=null && response.isSuccessful()){
                        List<Modelo> listaResponse = response.body();

                        //limpio todo
                        modeloRepository.deleteAllRows();
                        for (Modelo obj : listaResponse)
                        {
                            modeloRepository.create(obj);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Modelo>> call, Throwable t) {

                }
            });


            return 1;

        }catch (Exception e)
        {
            return 0;
        }
    }

    public int SyncUsuario()
    {
        usuarioRepository = new UsuarioRepository(ctx);

        try {
            Call<List<Usuario>> call= ApiClienteMaestros.getMyApiClient().listaUsuario();
            call.enqueue(new Callback<List<Usuario>>() {
                @Override
                public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {

                    if(response!=null && response.isSuccessful()){
                        List<Usuario> listaResponse = response.body();

                        //limpio todo
                        usuarioRepository.deleteAllRows();
                        for (Usuario obj : listaResponse)
                        {
                            usuarioRepository.create(obj);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Usuario>> call, Throwable t) {

                }
            });


            return 1;

        }catch (Exception e)
        {
            return 0;
        }
    }
    public int SyncVin()
    {
        vinRepository = new VinRepository(ctx);

        try {
            Call<List<Vin>> call= ApiClienteMaestros.getMyApiClient().listaVin();
            call.enqueue(new Callback<List<Vin>>() {
                @Override
                public void onResponse(Call<List<Vin>> call, Response<List<Vin>> response) {

                    if(response!=null && response.isSuccessful()){
                        List<Vin> listaResponse = response.body();

                        //limpio todo
                        vinRepository.deleteAllRows();
                        for (Vin obj : listaResponse)
                        {
                            vinRepository.create(obj);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Vin>> call, Throwable t) {

                }
            });


            return 1;

        }catch (Exception e)
        {
            return 0;
        }
    }

}
