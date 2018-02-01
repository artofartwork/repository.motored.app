package com.movil.summmit.motorresapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.BoringLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;
import com.movil.summmit.motorresapp.Adapters.AdapterEmpleado;
import com.movil.summmit.motorresapp.Adapters.CasoTecnicoAdapter;
import com.movil.summmit.motorresapp.Listeners.OnItemClickListener;
import com.movil.summmit.motorresapp.LogicMethods.Repository;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFalla;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaCausa;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaCorrectivos;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaDiagnostico;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaxEmpleado;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.CasoTecnico;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empleado;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.HelpMaestro;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.MaestraArgu;
import com.movil.summmit.motorresapp.Storage.Files.FilesControl;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.NormalFilePickActivity;
import com.vincent.filepicker.filter.entity.NormalFile;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class DetalleCausaAnalisisFallaActivity extends AppCompatActivity implements OnItemClickListener  {
    LinearLayout layoutTecnicos, layoutCasoTecnico;

    Spinner spnSistemaFalla, spnModoFalla;
    ListView listDiagnostico, listCausaFalla, listTrabajocorrec,lsvTecnicos;
    RecyclerView lsvCasoTecnico;
    EditText nrcaso, edtFileScanner, edtFileMuestAceite, edtFileMuestCombus, edtDiagnostico, edtCausaFalla, edtTrabajocorrec,edtTecnicos;
    TextInputLayout tmpTecnicos, tmpSistemaFalla, tmpModoFalla, tmpNrcaso, tmpFileScanner, tmpFileMuestAceite, tmpFileMuestCombus;
    CheckBox chkScanner, chkAceite, chkCombustible;
    Button btnDiagnostico, btnCausaFalla, btnTrabajocorrec, btnSavedTecnicos, btnSavedCasoTecnico;
    int IdInformeTecnico = 0;

    //repositorys
    Repository repository;

    //adpater coments
    ArrayAdapter<String> adapterComentarios;
    AdapterEmpleado adapterEmpleado;
    CasoTecnicoAdapter casoTecnicoAdapter;
    //---
    //listas comentarios
    List<InformeTecnicoFallaDiagnostico> listaInformeTecnicoFallaDiagnosticos;
    List<InformeTecnicoFallaCausa> listaInformeTecnicoFallaCausa;
    List<InformeTecnicoFallaCorrectivos> listaInformeTecnicoFallaCorrectivo;
    //
    List<Empleado>listaEmpleados;
    List<CasoTecnico>listaCasoTecnico;

    java.util.Date utilDatehoy;
    java.sql.Date sqlDatehoy;

    Integer inputFileSelected = 0;
    FilesControl filesControl;
    String estacionPath;
    static NormalFile fileScanner;
    static NormalFile fileAceite;
    static NormalFile fileCombustible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_causa_analisis_falla);
        Intent myIntent = getIntent(); // gets the previously created intent
        IdInformeTecnico = myIntent.getIntExtra("IdInformeTecnico", 0);
        repository = new Repository(this);
        filesControl = new FilesControl();
        estacionPath =  filesControl.getAlbumStorageDirEstacion("INFORME_" + IdInformeTecnico) + File.separator; //+ "hola.txt";

        utilDatehoy = new java.util.Date();
        sqlDatehoy =  new java.sql.Date(utilDatehoy.getTime());

        initListas();

        initComponents();
        initComponentsTabs();

        lsvTecnicos.setAdapter(adapterEmpleado);

    }

    public void initListas()
    {
        //inicializo listas
        listaInformeTecnicoFallaDiagnosticos = new ArrayList<>();
        listaInformeTecnicoFallaCausa = new ArrayList<>();
        listaInformeTecnicoFallaCorrectivo = new ArrayList<>();
        listaEmpleados = repository.empleadoRepository().findAll();
        adapterEmpleado = new AdapterEmpleado(this, listaEmpleados);

        //--
    }

    public void initComponents()
    {

        //spnSistemaFalla, spnModoFalla;
        spnSistemaFalla = (Spinner)findViewById(R.id.spnSistemaFalla);
        final List<MaestraArgu> lstsistemafalla = repository.maestraArguRepository().findAll(HelpMaestro.getSistemaFalla());
        ArrayAdapter sistemafallaAdapter = new ArrayAdapter(this, R.layout.spinner, lstsistemafalla);
        spnSistemaFalla.setAdapter(sistemafallaAdapter);
        spnSistemaFalla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                MaestraArgu objsistema = lstsistemafalla.get(position);

                Log.d("sistema select", "idsistema->" + objsistema.getIdArgu());
                populateCasoTecnico(objsistema.getIdArgu());
              //  Marca objMarca =  lstMarca.get(position);
               // populateModelo(objMarca.getIdMarca());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnModoFalla = (Spinner)findViewById(R.id.spnModoFalla);
        List<MaestraArgu> lstmodofalla = repository.maestraArguRepository().findAll(HelpMaestro.getModoFalla());
        ArrayAdapter modofallaAdapter = new ArrayAdapter(this, R.layout.spinner, lstmodofalla);
        spnModoFalla.setAdapter(modofallaAdapter);

        btnSavedCasoTecnico = (Button)findViewById(R.id.btnSavedCasoTecnico);
        btnSavedCasoTecnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mostrarListaCasosTecnicos();
            }
        });


        btnSavedTecnicos = (Button)findViewById(R.id.btnSavedTecnicos);
        btnSavedTecnicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> nombres = new ArrayList<>();

                for (Empleado obj: listaEmpleados)
                {
                    if (obj.getSelected())
                    {
                        nombres.add(obj.getNombre());
                    }

                }

                edtTecnicos.setText(TextUtils.join(", ", nombres));

                mostrarListaTecnicos();
            }
        });

        layoutCasoTecnico = (LinearLayout)findViewById(R.id.layoutCasoTecnico);
        lsvCasoTecnico = (RecyclerView) findViewById(R.id.lsvCasoTecnico);
        lsvCasoTecnico.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        layoutTecnicos = (LinearLayout)findViewById(R.id.layoutTecnicos);
        lsvTecnicos = (ListView)findViewById(R.id.lsvTecnicos);
        lsvTecnicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Empleado model = listaEmpleados.get(i);

                if (model.getSelected())
                    model.setSelected(false);
                else
                    model.setSelected(true);
                listaEmpleados.set(i, model);
                //now update adapter
                adapterEmpleado.updateRecords(listaEmpleados);
            }
        });

        listDiagnostico =(ListView)findViewById(R.id.listDiagnostico);
        listCausaFalla =(ListView)findViewById(R.id.listCausaFalla);
        listTrabajocorrec =(ListView)findViewById(R.id.listTrabajocorrec);

        nrcaso = (EditText)findViewById(R.id.nrcaso);
        nrcaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openDialogCasosTecnicos();
                mostrarListaCasosTecnicos();
            }
        });

        edtFileScanner = (EditText)findViewById(R.id.edtFileScanner);
        edtFileScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputFileSelected = 1;
                openFileExplorer();
            }
        });

        edtFileMuestAceite = (EditText)findViewById(R.id.edtFileMuestAceite);
        edtFileMuestAceite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputFileSelected = 2;
                openFileExplorer();
            }
        });
        edtFileMuestCombus = (EditText)findViewById(R.id.edtFileMuestCombus);
        edtFileMuestCombus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputFileSelected = 3;
                openFileExplorer();
            }
        });

        edtDiagnostico = (EditText)findViewById(R.id.edtDiagnostico);
        edtCausaFalla = (EditText)findViewById(R.id.edtCausaFalla);
        edtTrabajocorrec = (EditText)findViewById(R.id.edtTrabajocorrec);

        tmpTecnicos = (TextInputLayout) findViewById(R.id.tmpTecnicos);
        tmpSistemaFalla = (TextInputLayout) findViewById(R.id.tmpSistemaFalla);
        tmpModoFalla = (TextInputLayout) findViewById(R.id.tmpModoFalla);
        tmpNrcaso = (TextInputLayout) findViewById(R.id.tmpNrcaso);
        tmpFileScanner = (TextInputLayout) findViewById(R.id.tmpFileScanner);
        tmpFileMuestAceite = (TextInputLayout) findViewById(R.id.tmpFileMuestAceite);
        tmpFileMuestCombus = (TextInputLayout) findViewById(R.id.tmpFileMuestCombus);

        chkScanner = (CheckBox)findViewById(R.id.chkScanner);
        chkAceite = (CheckBox)findViewById(R.id.chkAceite);
        chkCombustible = (CheckBox)findViewById(R.id.chkCombustible);

        btnDiagnostico = (Button)findViewById(R.id.btnDiagnostico);
        btnDiagnostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comentario = edtDiagnostico.getText().toString().trim();
                if (comentario.length() > 2)
                {
                    InformeTecnicoFallaDiagnostico objDiag = new InformeTecnicoFallaDiagnostico();
                    objDiag.setAudFechaRegistro(sqlDatehoy);
                    objDiag.setAudUsuarioRegistro(1);
                    objDiag.setDescripcion(comentario);

                    listaInformeTecnicoFallaDiagnosticos.add(objDiag);
                    populateListDiagnostico();

                }
                edtDiagnostico.setText("");

            }
        });

        btnCausaFalla = (Button)findViewById(R.id.btnCausaFalla);
        btnCausaFalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comentario = edtCausaFalla.getText().toString().trim();
                if (comentario.length() > 2)
                {
                    InformeTecnicoFallaCausa objDiag = new InformeTecnicoFallaCausa();
                    objDiag.setAudFechaRegistro(sqlDatehoy);
                    objDiag.setAudUsuarioRegistro(1);
                    objDiag.setDescripcion(comentario);

                    listaInformeTecnicoFallaCausa.add(objDiag);
                    populateListCausa();
                }

                edtCausaFalla.setText("");
            }
        });

        btnTrabajocorrec = (Button)findViewById(R.id.btnTrabajocorrec);
        btnTrabajocorrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comentario = edtTrabajocorrec.getText().toString().trim();
                if (comentario.length() > 2)
                {
                    InformeTecnicoFallaCorrectivos objDiag = new InformeTecnicoFallaCorrectivos();
                    objDiag.setAudFechaRegistro(sqlDatehoy);
                    objDiag.setAudUsuarioRegistro(1);
                    objDiag.setDescripcion(comentario);

                    listaInformeTecnicoFallaCorrectivo.add(objDiag);
                    populateListCorrectivos();

                }

                edtTrabajocorrec.setText("");
            }
        });


        edtTecnicos = (EditText)findViewById(R.id.edtTecnicos);
        edtTecnicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarListaTecnicos();
            }
        });
    }

    public void populateCasoTecnico(int IdSistema)
    {
        listaCasoTecnico = repository.casoTecnicoRepository().findAllxSistemaFalla(IdSistema);

        Log.d("lista caso tec", "size -> " + listaCasoTecnico.size());
        casoTecnicoAdapter = new CasoTecnicoAdapter(listaCasoTecnico);
        lsvCasoTecnico.setAdapter(casoTecnicoAdapter);
        casoTecnicoAdapter.setOnItemClickListener(this);

    }

    private void mostrarListaTecnicos() {

        if (!layoutTecnicos.isShown())
        {
            layoutTecnicos.setVisibility(View.VISIBLE);
        }
        else
        {
            layoutTecnicos.setVisibility(View.GONE);
        }

    }
    private void mostrarListaCasosTecnicos() {

        if (!layoutCasoTecnico.isShown())
        {
            layoutCasoTecnico.setVisibility(View.VISIBLE);
        }
        else
        {
            layoutCasoTecnico.setVisibility(View.GONE);
        }

    }

    public void initComponentsTabs()
    {
        Resources res = getResources();
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Trab. Diagnostico");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Causa Falla");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Trab. Correctivo");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        setTitle("Detalle de causa y analisis");
        getMenuInflater().inflate(R.menu.menu_nuevo, menu);
        super.onCreateOptionsMenu(menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_save:

                if (validateForm())
                {
                    InformeTecnicoFalla objInforme = mapperInformeTecnicoFalla();
                    try
                    {
                        int IdGenerado = repository.informeTecnicoFallaRepository().create(objInforme);

                        // si todo sale bien se guardan los comentarios
                        if (IdGenerado > 0)
                        {
                            for (InformeTecnicoFallaDiagnostico obj: listaInformeTecnicoFallaDiagnosticos)
                            {
                                obj.setIdInformeTecnicoFalla(IdGenerado);
                                repository.informeTecnicoFallaDiagnosticoRepository().create(obj);
                            }

                            for (InformeTecnicoFallaCausa obj: listaInformeTecnicoFallaCausa)
                            {
                                obj.setIdInformeTecnicoFalla(IdGenerado);
                                repository.informeTecnicoFallaCausaRepository().create(obj);
                            }
                            for (InformeTecnicoFallaCorrectivos obj: listaInformeTecnicoFallaCorrectivo)
                            {
                                obj.setIdInformeTecnicoFalla(IdGenerado);
                                repository.informeTecnicoFallaCorrectivosRepository().create(obj);
                            }

                            //se guarda los tecnicos, es en otra tabla

                            for (Empleado obj : listaEmpleados)
                            {
                                if (obj.getSelected())
                                {
                                    InformeTecnicoFallaxEmpleado objnew = new InformeTecnicoFallaxEmpleado();
                                    objnew.setIdInformeTecnicoFalla(IdGenerado);
                                    objnew.setIdEmpleado(obj.getIdEmpleado());
                                    objnew.setAudUsuarioRegistro(1);
                                    objnew.setAudFechaRegistro(sqlDatehoy);
                                    repository.informeTecnicoFallaxEmpleadoRepository().create(objnew);
                                }

                            }

                            if (chkScanner.isChecked())
                            {
                                File srcScaner = new File(fileScanner.getPath());
                                File destScaner = new File(estacionPath + objInforme.getArchivoScannerNombreGenerado());
                                filesControl.copy(srcScaner, destScaner);
                            }

                            if (chkAceite.isChecked())
                            {
                                File srcAceite = new File(fileAceite.getPath());
                                File destAceite = new File(estacionPath + objInforme.getArchivoAceiteNombreGenerado());
                                filesControl.copy(srcAceite, destAceite);
                            }

                            if (chkCombustible.isChecked())
                            {
                                File srcCombus = new File(fileCombustible.getPath());
                                File destCombus = new File(estacionPath + objInforme.getArchivoCombustibleNombreGenerado());
                                filesControl.copy(srcCombus, destCombus);
                            }


                        }

                        Intent inte =new Intent(DetalleCausaAnalisisFallaActivity.this, AnalisisCausaFallaActivity.class);
                        inte.putExtra("IdInformeTecnico", IdInformeTecnico);
                        startActivity(inte);
                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(this, "OCURRIO UN ERRROR INESPERADO", Toast.LENGTH_SHORT).show();
                    }

                }

                break;
        }

        //return super.onOptionsItemSelected(item);
        return true;
    }



    public Boolean validateForm()
    {
        MaestraArgu sistemafalla = (MaestraArgu) ( (Spinner) findViewById(R.id.spnSistemaFalla) ).getSelectedItem();
        if (sistemafalla == null) {
            tmpSistemaFalla.setError("Este campo es obligatorio");
            return false;
        }

        MaestraArgu modofalla = (MaestraArgu) ( (Spinner) findViewById(R.id.spnModoFalla) ).getSelectedItem();
        if (modofalla == null) {
            tmpModoFalla.setError("Este campo es obligatorio");
            return false;
        }
        if ( nrcaso.getText().toString().trim().isEmpty())
        {
            tmpNrcaso.setError("Este campo es obligatorio");
            return false;
        }

        if (chkScanner.isChecked())
        {
            if(edtFileScanner.getText().toString().trim().isEmpty())
            {
                tmpFileScanner.setError("Este campo es obligatorio");
                return false;
            }
        }

        if (chkAceite.isChecked())
        {
            if(edtFileMuestAceite.getText().toString().trim().isEmpty())
            {
                tmpFileMuestAceite.setError("Este campo es obligatorio");
                return false;
            }
        }

        if (chkCombustible.isChecked())
        {
            if(edtFileMuestCombus.getText().toString().trim().isEmpty())
            {
                tmpFileMuestCombus.setError("Este campo es obligatorio");
                return false;
            }
        }


        return true;
    }

    public InformeTecnicoFalla mapperInformeTecnicoFalla()
    {
        InformeTecnicoFalla objInformeFalla = new InformeTecnicoFalla();

        objInformeFalla.setIdInformeTecnico(IdInformeTecnico);

        MaestraArgu sistemafalla = (MaestraArgu) ( (Spinner) findViewById(R.id.spnSistemaFalla) ).getSelectedItem();
        objInformeFalla.setIdArguSistema(sistemafalla.getIdArgu());

        MaestraArgu modofalla = (MaestraArgu) ( (Spinner) findViewById(R.id.spnModoFalla) ).getSelectedItem();
        objInformeFalla.setIdArguModoFalla(modofalla.getIdArgu());

        objInformeFalla.setNroCaso(nrcaso.getText().toString().trim());


        objInformeFalla.setScanner(chkScanner.isChecked());
        if (chkScanner.isChecked())
        {
            Long timestamp = (System.currentTimeMillis() / 1000)+ 1;
            objInformeFalla.setArchivoScannerNombre(filesControl.getFileName(fileScanner.getPath()) );
            objInformeFalla.setArchivoScannerNombreGenerado( timestamp + "_" + filesControl.getFileName(fileScanner.getPath()));
        }

        objInformeFalla.setAceite(chkAceite.isChecked());
        if (chkAceite.isChecked())
        {
            Long timestampAce = (System.currentTimeMillis() / 1000)+2;
            objInformeFalla.setArchivoAceiteNombre(filesControl.getFileName(fileAceite.getPath()));
            objInformeFalla.setArchivoAceiteNombreGenerado( timestampAce + "_" + filesControl.getFileName(fileAceite.getPath()));
        }

        objInformeFalla.setCombustible(chkCombustible.isChecked());
        if (chkCombustible.isChecked())
        {
            Long timestampComb = (System.currentTimeMillis() / 1000) + 3;
            objInformeFalla.setArchivoCombustibleNombre(edtFileMuestCombus.getText().toString().trim().isEmpty() ? null  : filesControl.getFileName(fileCombustible.getPath()));
            objInformeFalla.setArchivoCombustibleNombreGenerado( timestampComb + "_" + filesControl.getFileName(fileCombustible.getPath()));
        }

        objInformeFalla.setActivo(true);

        objInformeFalla.setAudFechaRegistro(sqlDatehoy);

        objInformeFalla.setAudUsuarioRegistro(1);


        return  objInformeFalla;



    }

    public void populateListDiagnostico()
    {
        ArrayList<String> listaString = new ArrayList<>();
        for (InformeTecnicoFallaDiagnostico obj: listaInformeTecnicoFallaDiagnosticos)
        {
            listaString.add(obj.getDescripcion());
        }
        adapterComentarios = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaString);
        listDiagnostico.setAdapter(adapterComentarios);
    }
    private void populateListCausa() {
        ArrayList<String> listaString = new ArrayList<>();
        for (InformeTecnicoFallaCausa obj: listaInformeTecnicoFallaCausa)
        {
            listaString.add(obj.getDescripcion());
        }
        adapterComentarios = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaString);
        listCausaFalla.setAdapter(adapterComentarios);
    }
    private void populateListCorrectivos() {
        ArrayList<String> listaString = new ArrayList<>();
        for (InformeTecnicoFallaCorrectivos obj: listaInformeTecnicoFallaCorrectivo)
        {
            listaString.add(obj.getDescripcion());
        }
        adapterComentarios= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaString);
        listTrabajocorrec.setAdapter(adapterComentarios);
    }

    @Override
    public void onClikListener(int position) {
        //Toast.makeText(this, "este es la posi: " + position + "", Toast.LENGTH_SHORT).show();
        //seteao a false a todos
        for (CasoTecnico obj: listaCasoTecnico)
        {
            if (obj.getSelected())
            {
                obj.setSelected(false);
            }
        }

        //el seleccionado le pongo true
        CasoTecnico model = listaCasoTecnico.get(position);
        if (model.getSelected())
            model.setSelected(false);
        else
            model.setSelected(true);

        nrcaso.setText(model.getIdCasoTecnico() + "");
        listaCasoTecnico.set(position, model);
        //now update adapter
        casoTecnicoAdapter.updateRecords(listaCasoTecnico);
       // mostrarListaCasosTecnicos();

    }

    private void openFileExplorer()
    {
        Intent intent4 = new Intent(this, NormalFilePickActivity.class);
        intent4.putExtra(Constant.MAX_NUMBER, 1);
        intent4.putExtra(FilePickActivity.SUFFIX,
                new String[] {"xlsx", "xls", "doc", "dOcX", "ppt", ".pptx", "pdf"});
        startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data != null)
        {
            //aqui solo se obtiene el onkjecto del documento
            ArrayList<NormalFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE);
            //  NormalFile objnormal = list.get(0);

            switch (inputFileSelected)
            {
                case 1:
                    //fileScanner =  "sca_" + filename;
                    //  fileScanner = new NormalFile();
                    fileScanner =  list.get(0);
                    edtFileScanner.setText(fileScanner.getPath());
                    break;
                case 2:
                    fileAceite = list.get(0);
                    edtFileMuestAceite.setText(fileAceite.getPath());
                    break;
                case 3:
                    fileCombustible = list.get(0);
                    edtFileMuestCombus.setText(fileCombustible.getPath());
                    break;
            }
        }


    }

  /*  public void copy(File src, File dst) throws IOException {
        FileInputStream inStream = new FileInputStream(src);
        FileOutputStream outStream = new FileOutputStream(dst);
        FileChannel inChannel = inStream.getChannel();
        FileChannel outChannel = outStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inStream.close();
        outStream.close();
    }*/




}
