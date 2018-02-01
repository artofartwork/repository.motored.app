package com.movil.summmit.motorresapp.LogicMethods;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.opengl.Visibility;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.movil.summmit.motorresapp.Listeners.OnRequestListener;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.CasoTecnico;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Cliente;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empleado;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empresa;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Maestra;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.MaestraArgu;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Marca;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Modelo;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.SyncMaestro;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Usuario;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Vin;
import com.movil.summmit.motorresapp.R;
import com.movil.summmit.motorresapp.Request.ApiClienteMaestros;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.CasoTecnicoRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.ClienteRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.EmpleadoRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.EmpresaRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.MaestraArguRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.MaestraRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.MarcaRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.ModeloRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.SyncMaestroRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.UsuarioRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.VinRepository;

import org.w3c.dom.Text;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    private OnRequestListener listener;
    View progressDialog = null;
    int cont = 0;

    public LogicMaestro(Context ctx, OnRequestListener listener,View progressDialog)
    {
        this.ctx = ctx;
        this.progressDialog = progressDialog;
       // getApplicationContext();
        this.listener =listener;
    }

    public int onMessageExitoSync() {


        cont= cont + 1;
        if(cont == 10){
            Snackbar snackbar = Snackbar
                    .make(progressDialog,"¡SINCRONIZACION EXITOSA!", Snackbar.LENGTH_LONG);

            snackbar.show();
            cont = 0;
            progressDialog.setVisibility(View.GONE);

        }

        return  1;
    }

    public int onMessageFalloSync() {
        Snackbar snackbar = Snackbar
                .make(progressDialog,"¡SINCRONIZACION FALLIDA!", Snackbar.LENGTH_LONG);

        snackbar.show();
        progressDialog.setVisibility(View.GONE);
        return  1;
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

                        onMessageExitoSync();
                    }
                }

                @Override
                public void onFailure(Call<List<Empresa>> call, Throwable t) {
                    progressDialog.setVisibility(View.GONE);
                    onMessageFalloSync();
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
        progressDialog.setVisibility(View.VISIBLE);

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

                    //    listener.onPrueba("aaaujh");

                        onMessageExitoSync();
                    }
                }

                @Override
                public void onFailure(Call<List<CasoTecnico>> call, Throwable t) {
                    Log.d("caso.tec.fechfalla", "-> " + t.getMessage());
                    progressDialog.setVisibility(View.GONE);
                    onMessageFalloSync();
                }
            });


            return 1;

        }catch (Exception e)
        {
            progressDialog.setVisibility(View.GONE);
            Log.d("casotecnico metodo", "-> " + e.getMessage());
            return 0;
        }
    }

    public int SyncCliente()
    {
        progressDialog.setVisibility(View.VISIBLE);

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
                        onMessageExitoSync();
                    }
                }

                @Override
                public void onFailure(Call<List<Cliente>> call, Throwable t) {
                    progressDialog.setVisibility(View.GONE);
                    onMessageFalloSync();
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
        progressDialog.setVisibility(View.VISIBLE);

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
                        onMessageExitoSync();
                    }
                }

                @Override
                public void onFailure(Call<List<Empleado>> call, Throwable t) {
                    progressDialog.setVisibility(View.GONE);
                    onMessageFalloSync();
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
        progressDialog.setVisibility(View.VISIBLE);

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
                        onMessageExitoSync();
                    }
                }

                @Override
                public void onFailure(Call<List<Maestra>> call, Throwable t) {
                    Log.d("maestra", "-> " + t.getMessage());
                    progressDialog.setVisibility(View.GONE);
                    onMessageFalloSync();
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
        progressDialog.setVisibility(View.VISIBLE);

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
                        onMessageExitoSync();
                    }
                }

                @Override
                public void onFailure(Call<List<MaestraArgu>> call, Throwable t) {
                    progressDialog.setVisibility(View.GONE);
                    onMessageFalloSync();
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

        progressDialog.setVisibility(View.VISIBLE);

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
                        onMessageExitoSync();
                    }
                }

                @Override
                public void onFailure(Call<List<Marca>> call, Throwable t) {
                    progressDialog.setVisibility(View.GONE);
                    onMessageFalloSync();
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
        progressDialog.setVisibility(View.VISIBLE);

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
                        onMessageExitoSync();
                    }
                }

                @Override
                public void onFailure(Call<List<Modelo>> call, Throwable t) {
                    progressDialog.setVisibility(View.GONE);
                    onMessageFalloSync();
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
        progressDialog.setVisibility(View.VISIBLE);
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
                        onMessageExitoSync();
                    }
                }

                @Override
                public void onFailure(Call<List<Usuario>> call, Throwable t) {
                    progressDialog.setVisibility(View.GONE);
                    onMessageFalloSync();
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
        progressDialog.setVisibility(View.VISIBLE);

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
                        onMessageExitoSync();


                    }
                }

                @Override
                public void onFailure(Call<List<Vin>> call, Throwable t) {
                    progressDialog.setVisibility(View.GONE);
                    onMessageFalloSync();
                }
            });


            return 1;

        }catch (Exception e)
        {
            return 0;
        }
    }

}
