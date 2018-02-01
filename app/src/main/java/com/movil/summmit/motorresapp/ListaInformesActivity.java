package com.movil.summmit.motorresapp;

import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.gson.Gson;
import com.movil.summmit.motorresapp.Adapters.InformeTecnicoAdapter;
import com.movil.summmit.motorresapp.LogicMethods.LogicGeneral;
import com.movil.summmit.motorresapp.LogicMethods.LogicMaestro;
import com.movil.summmit.motorresapp.LogicMethods.Repository;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnico;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAdjuntos;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoConclusiones;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFalla;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoRecomendaciones;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.CasoTecnico;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empleado;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empresa;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.HelpMaestro;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.MaestraArgu;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Marca;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Modelo;
import com.movil.summmit.motorresapp.Request.ApiClienteInformes;
import com.movil.summmit.motorresapp.Request.ReturnValue;
import com.movil.summmit.motorresapp.Storage.Files.FilesControl;
import com.movil.summmit.motorresapp.Storage.PreferencesHelper;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.CasoTecnicoRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.EmpleadoRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.EmpresaRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.MaestraArguRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.MarcaRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.ModeloRepository;
import com.movil.summmit.motorresapp.controllerSwipe.SwipeController;
import com.movil.summmit.motorresapp.controllerSwipe.SwipeControllerActions;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListaInformesActivity extends AppCompatActivity {

    private View flayLoading, container;
    private InformeTecnicoAdapter mAdapter;
    SwipeController swipeController = null;
    private CasoTecnicoRepository casoTecnicoRepository;
   /* private InformeTecnicoRepository informeTecnicoRepository;
    private EmpresaRepository empresaRepository;
    private MarcaRepository marcaRepository;
    private ModeloRepository modeloRepository;
    private MaestraArguRepository maestraArguRepository;*/
    Repository repository;
    List<InformeTecnico> lstInformes;
    RecyclerView recyclerView;
    Spinner spnEmpresa, spnSucursal, spnArea, spnMarca, spnModelo;
    Button btnBuscar;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_informes);
        repository = new Repository(this);
      //  casoTecnicoRepository = new CasoTecnicoRepository(this);
        FilesControl carpeta = new FilesControl();
        carpeta.getAlbumStorageDir(PreferencesHelper.getCarpetaGeneralNombre(this));

        container=findViewById(R.id.container);
        flayLoading=findViewById(R.id.flayLoading);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //init dao
       // informeTecnicoRepository = new InformeTecnicoRepository(this);
       // empresaRepository = new EmpresaRepository(this);
        //marcaRepository = new MarcaRepository(this);
        //modeloRepository = new ModeloRepository(this);
        //maestraArguRepository = new MaestraArguRepository(this);




        //inicializo componentes de lista recycler
        setInformeDataAdapter();
        setupRecyclerView();

        //inicalizo componenets de busqueda
        spnEmpresa = (Spinner)findViewById(R.id.spnEmpresa);
        spnSucursal = (Spinner)findViewById(R.id.spnSucursal);
        spnArea = (Spinner)findViewById(R.id.spnArea);
        spnMarca = (Spinner)findViewById(R.id.spnMarca);
        spnModelo = (Spinner)findViewById(R.id.spnModelo);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);

        initDataParams();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buscarPorParametros();

            }
        });

      //  showLoading();

    }

    private void buscarPorParametros()
    {
        Empresa empresa = (Empresa) ( (Spinner) findViewById(R.id.spnEmpresa) ).getSelectedItem();
        MaestraArgu sucursal = (MaestraArgu) ( (Spinner) findViewById(R.id.spnSucursal) ).getSelectedItem();
        MaestraArgu area = (MaestraArgu) ( (Spinner) findViewById(R.id.spnArea) ).getSelectedItem();
        Marca marca = (Marca) ( (Spinner) findViewById(R.id.spnMarca) ).getSelectedItem();
        Modelo modelo = (Modelo) ( (Spinner) findViewById(R.id.spnModelo) ).getSelectedItem();

    }

    public void initDataParams()
    {
        List<Empresa> lstEmpresa = repository.empresaRepository().findAll();
        ArrayAdapter empresaAdapter = new ArrayAdapter(this, R.layout.spinner, lstEmpresa);
        spnEmpresa.setAdapter(empresaAdapter);

        List<MaestraArgu> lstSucursal = repository.maestraArguRepository().findAll(HelpMaestro.getSucursal());
        ArrayAdapter sucursalAdapter = new ArrayAdapter(this, R.layout.spinner, lstSucursal);
        spnSucursal.setAdapter(sucursalAdapter);

        List<MaestraArgu> lstArea = repository.maestraArguRepository().findAll(HelpMaestro.getArea());
        ArrayAdapter areaAdapter = new ArrayAdapter(this, R.layout.spinner, lstArea);
        spnArea.setAdapter(areaAdapter);

        final List<Marca> lstMarca = repository.marcaRepository().findAll();
        ArrayAdapter marcaAdapter = new ArrayAdapter(this, R.layout.spinner, lstMarca);
        spnMarca.setAdapter(marcaAdapter);

        spnMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Marca objMarca =  lstMarca.get(position);
                populateModelo(objMarca.getIdMarca());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

    }

    private void populateModelo(int IdMarca)
    {
        List<Modelo> lstModelo = repository.modeloRepository().findAllporMarca(IdMarca);
        ArrayAdapter modeloAdapter = new ArrayAdapter(this, R.layout.spinner, lstModelo);
        spnModelo.setAdapter(modeloAdapter);
    }
    private void setInformeDataAdapter() {

        lstInformes = repository.informeTecnicoRepository().findAll();
        mAdapter = new InformeTecnicoAdapter(lstInformes);
    }

    private void setupRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        swipeController = new SwipeController(2,new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
               // mAdapter.players.remove(position);
               // mAdapter.notifyItemRemoved(position);
                //mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
                InformeTecnico objInforme = lstInformes.get(position);

                //    InformeTecnico objnueva = informeTecnicoRepository.findEntidad(objInforme.getIdInformeTecnico());
                //Gson gson   = new Gson();

                //String data = gson.toJson(objnueva).toString();

                //Log.d("json informe", "-> " + data);

                Intent inte =new Intent(ListaInformesActivity.this, DetailInformeTecnicoActivity.class);
                inte.putExtra("IdInformeTecnico", objInforme.getIdInformeTecnico());
                startActivity(inte);
            }

            @Override
            public void onLeftClicked(int position) {
                //Toast.makeText(ListaInformesActivity.this, "hola mundo" + position, Toast.LENGTH_SHORT).show();
                InformeTecnico objInforme = lstInformes.get(position);
                Intent inte =new Intent(ListaInformesActivity.this, EditarInformeTecnicoActivity.class);
                inte.putExtra("IdInformeTecnico", objInforme.getIdInformeTecnico());
                startActivity(inte);
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        setTitle("Lista de Informes");
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
         super.onCreateOptionsMenu(menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_add:
                Intent inte =new Intent(ListaInformesActivity.this, NuevoInformeActivity.class);
                startActivity(inte);
                break;

            case R.id.action_update:


                sincronizarDatos();


                break;

            case R.id.action_signout:


                preferencesHelper.signOut(this);
                Intent intent = new Intent(ListaInformesActivity.this, LoginActivity.class);
                ListaInformesActivity.this.startActivity(intent);
                finish();

                break;

        }


        //return super.onOptionsItemSelected(item);
        return true;
    }


    public void sincronizarDatos(){


        try
        {
            showLoading();
            LogicMaestro logicMaestro = new LogicMaestro(this);
            logicMaestro.SyncEmpresa();
            logicMaestro.SyncCasoTecnico();
            logicMaestro.SyncCliente();
            logicMaestro.SyncEmpleado();
            logicMaestro.SyncMaestra();
            logicMaestro.SyncMaestraArgu();
            logicMaestro.SyncMarca();
            logicMaestro.SyncModelo();
            logicMaestro.SyncVin();
            logicMaestro.SyncUsuario();

            hideLoading();
            onMessageExitoSync();


        }catch (Exception e)
        {
            onMessageErrorSync();
        }


    }

    private void showLoading(){
        flayLoading.setVisibility(View.VISIBLE);
    }
    private void hideLoading(){
        flayLoading.setVisibility(View.GONE);
    }

    public int onMessageErrorSync() {
        Snackbar snackbar = Snackbar
                .make(container,"¡OCURRIO UN ERROR EN SINCRONIZACION!", Snackbar.LENGTH_LONG);

        snackbar.show();
        return  1;
    }

    public int onMessageExitoSync() {
        Snackbar snackbar = Snackbar
                .make(container,"¡SINCRONIZACION EXITOSA!", Snackbar.LENGTH_LONG);

        snackbar.show();
        return  1;
    }


    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    public void tomasu(View view)
    {
        try {

            InformeTecnico objInforme = repository.informeTecnicoRepository().findEntidad(8);
            List<InformeTecnicoFalla> listaFallas = repository.informeTecnicoFallaRepository().findAllxInforme(objInforme.getIdInformeTecnico());
            objInforme.setListaFallas(listaFallas);
            List<InformeTecnicoConclusiones> listaConclusiones = repository.informeTecnicoConclusionesRepository().findAllxInforme(objInforme.getIdInformeTecnico());
            objInforme.setListaConclusiones(listaConclusiones);
            List<InformeTecnicoRecomendaciones> listaRecomen = repository.informeTecnicoRecomendacionesRepository().findAllxInforme(objInforme.getIdInformeTecnico());
            objInforme.setListaRecomendaciones(listaRecomen);
            InformeTecnicoAdjuntos objAdjuntos = repository.informeTecnicoAdjuntosRepository().findxInforme(objInforme.getIdInformeTecnico());
            objInforme.setInformeTecnicoAdjuntos(objAdjuntos);

            FilesControl filesControl = new FilesControl();
            File scanner = filesControl.getpruebados();

            LogicGeneral logicGeneral = new LogicGeneral();

            Gson gson = new Gson();

            String datos = gson.toJson(objInforme).toString();

            Uri fileUri = Uri.fromFile(scanner);

            RequestBody requestFile =
                    RequestBody.create(
                            MediaType.parse(getMimeType(fileUri.getPath())),
                            scanner
                    );

            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("fileScanner", scanner.getName(), requestFile);

            RequestBody description =
                    RequestBody.create(
                            okhttp3.MultipartBody.FORM, datos);

            Call<ReturnValue> call = ApiClienteInformes.getMyApiClient().uploadfiles(description, body);
            call.enqueue(new Callback<ReturnValue>() {
                @Override
                public void onResponse(Call<ReturnValue> call,
                                       Response<ReturnValue> response) {
                    Log.v("Upload", "success");
                }

                @Override
                public void onFailure(Call<ReturnValue> call, Throwable t) {
                    Log.e("Upload error:", t.getMessage());
                }
            });


            //logicGeneral.syncDatainforme(datos, scanner);
           // File file = FileUtils.    getFile(this, fileUri);
          /* FilesControl filesControl = new FilesControl();
           File mifile = filesControl.getprueba();

            Uri fileUri = Uri.fromFile(mifile);

            RequestBody requestFile =
                    RequestBody.create(
                            MediaType.parse(getMimeType(fileUri.getPath())),
                            mifile
                    );
            // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("excel", mifile.getName(), requestFile);



           /* File mifiledos = filesControl.getpruebados();

            Uri fileUridos = Uri.fromFile(mifiledos);

            RequestBody requestFiledos =
                    RequestBody.create(
                            MediaType.parse(getMimeType(fileUridos.getPath())),
                            mifiledos
                    );
            // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part bodydos =
                    MultipartBody.Part.createFormData("mipdf", mifiledos.getName(), requestFiledos);*/

            // add another part within the multipart request
          /*  List<InformeTecnico> informess = repository.informeTecnicoRepository().findAll();
            Gson gson = new Gson();
            String datos = gson.toJson(informess).toString();

            String descriptionString = datos;
            RequestBody description =
                    RequestBody.create(
                            okhttp3.MultipartBody.FORM, descriptionString);
            //RequestBody lista = RequestBody.create()

            // finally, execute the request
            Call<ReturnValue> call = ApiClienteInformes.getMyApiClient().uploadfiles(description, body, bodydos);
            call.enqueue(new Callback<ReturnValue>() {
                @Override
                public void onResponse(Call<ReturnValue> call,
                                       Response<ReturnValue> response) {
                    Log.v("Upload", "success");
                }

                @Override
                public void onFailure(Call<ReturnValue> call, Throwable t) {
                    Log.e("Upload error:", t.getMessage());
                }
            });*/



        }catch (Exception e)
        {
            String da = e.getMessage();
        }
    }



    }

