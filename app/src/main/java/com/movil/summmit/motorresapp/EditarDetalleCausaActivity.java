package com.movil.summmit.motorresapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EditarDetalleCausaActivity extends AppCompatActivity implements OnItemClickListener {

    //components view
    LinearLayout layoutTecnicos, layoutCasoTecnico;
    Spinner spnSistemaFalla, spnModoFalla;
    ListView listDiagnostico, listCausaFalla, listTrabajocorrec,lsvTecnicos;
    RecyclerView lsvCasoTecnico;
    EditText nrcaso, edtFileScanner, edtFileMuestAceite, edtFileMuestCombus, edtDiagnostico, edtCausaFalla, edtTrabajocorrec,edtTecnicos;
    TextInputLayout tmpTecnicos, tmpSistemaFalla, tmpModoFalla, tmpNrcaso, tmpFileScanner, tmpFileMuestAceite, tmpFileMuestCombus;
    CheckBox chkScanner, chkAceite, chkCombustible;
    Button btnDiagnostico, btnCausaFalla, btnTrabajocorrec, btnSavedTecnicos, btnSavedCasoTecnico;

    AdapterEmpleado adapterEmpleado;
    //--
    Repository repository;
    List<Empleado> listaEmpleados;
    Integer IdInformeTecnicoFalla;
    InformeTecnicoFalla objInformeTecnicoFalla;
    List<CasoTecnico>listaCasoTecnico;
    CasoTecnicoAdapter casoTecnicoAdapter;

    List<InformeTecnicoFallaDiagnostico> listaInformeTecnicoFallaDiagnosticos;
    List<InformeTecnicoFallaCausa> listaInformeTecnicoFallaCausa;
    List<InformeTecnicoFallaCorrectivos> listaInformeTecnicoFallaCorrectivo;

    java.util.Date utilDatehoy;
    java.sql.Date sqlDatehoy;

    Integer inputFileSelected = 0;
    FilesControl filesControl;
    NormalFile fileScanner;
    NormalFile fileAceite;
    NormalFile fileCombustible;
    String estacionPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_detalle_causa);
        Intent myIntent = getIntent(); // gets the previously created intent
        IdInformeTecnicoFalla = myIntent.getIntExtra("IdInformeTecnicoFalla", 0);
        repository = new Repository(this);
        objInformeTecnicoFalla = repository.informeTecnicoFallaRepository().findxId(IdInformeTecnicoFalla);
        initComponents(objInformeTecnicoFalla);
        initComponentsTabs();
        utilDatehoy = new java.util.Date();
        sqlDatehoy =  new java.sql.Date(utilDatehoy.getTime());
        filesControl = new FilesControl();
        estacionPath =  filesControl.getAlbumStorageDirEstacion("INFORME_" + objInformeTecnicoFalla.getIdInformeTecnico()) + File.separator;
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
    public void initComponents(InformeTecnicoFalla objData)
    {
        edtTecnicos = (EditText)findViewById(R.id.edtTecnicos);
        edtTecnicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarListaTecnicos();
            }
        });

        spnSistemaFalla = (Spinner)findViewById(R.id.spnSistemaFalla);
        final List<MaestraArgu> lstsistemafalla = repository.maestraArguRepository().findAll(HelpMaestro.getSistemaFalla());
        ArrayAdapter sistemafallaAdapter = new ArrayAdapter(this, R.layout.spinner, lstsistemafalla);
        spnSistemaFalla.setAdapter(sistemafallaAdapter);

        for (MaestraArgu obj : lstsistemafalla)
        {
            if (obj.getIdArgu() == objData.getIdArguSistema())
            {
                spnSistemaFalla.setSelection(sistemafallaAdapter.getPosition(obj));
                break;
            }
        }

        spnSistemaFalla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                MaestraArgu objsistema = lstsistemafalla.get(position);
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

        for (MaestraArgu obj : lstmodofalla)
        {
            if (obj.getIdArgu() == objData.getIdArguModoFalla())
            {
                spnModoFalla.setSelection(modofallaAdapter.getPosition(obj));
                break;
            }
        }

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
                mostrarNombresTecnicos(listaEmpleados);
                mostrarListaTecnicos();
            }
        });

        layoutCasoTecnico = (LinearLayout)findViewById(R.id.layoutCasoTecnico);
        lsvCasoTecnico = (RecyclerView) findViewById(R.id.lsvCasoTecnico);
        lsvCasoTecnico.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        MaestraArgu sistemafalla = (MaestraArgu) ( (Spinner) findViewById(R.id.spnSistemaFalla) ).getSelectedItem();
        populateCasoTecnico(sistemafalla.getIdArgu());

        layoutTecnicos = (LinearLayout)findViewById(R.id.layoutTecnicos);
        lsvTecnicos = (ListView)findViewById(R.id.lsvTecnicos);

        List<InformeTecnicoFallaxEmpleado> listaEmpleadosSeleccionados = repository.informeTecnicoFallaxEmpleadoRepository().findAllxInformeTecnicoFalla(objInformeTecnicoFalla.getIdInformeTecnicoFalla());
        listaEmpleados = repository.empleadoRepository().findAll();
        for (Empleado obj : listaEmpleados)
        {
            for (InformeTecnicoFallaxEmpleado objFallxEmp : listaEmpleadosSeleccionados)
            {
                if (obj.getIdEmpleado() == objFallxEmp.getIdEmpleado())
                {
                    obj.setSelected(true);
                }
            }
        }

        adapterEmpleado = new AdapterEmpleado(this, listaEmpleados);
        lsvTecnicos.setAdapter(adapterEmpleado);
        mostrarNombresTecnicos(listaEmpleados);
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

        listaInformeTecnicoFallaDiagnosticos = repository.informeTecnicoFallaDiagnosticoRepository().AllxInformeTecnicoFalla(objData.getIdInformeTecnicoFalla());
        populateListDiagnostico();

        listaInformeTecnicoFallaCausa = repository.informeTecnicoFallaCausaRepository().AllxInformeTecnicoFalla(objData.getIdInformeTecnicoFalla());
        populateListCausa();

        listaInformeTecnicoFallaCorrectivo = repository.informeTecnicoFallaCorrectivosRepository().AllxInformeTecnicoFalla(objData.getIdInformeTecnicoFalla());
        populateListCorrectivos();


        nrcaso = (EditText)findViewById(R.id.nrcaso);
        nrcaso.setText(objData.getNroCaso());
        nrcaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openDialogCasosTecnicos();
                mostrarListaCasosTecnicos();
            }
        });

        edtFileScanner = (EditText)findViewById(R.id.edtFileScanner);
        edtFileScanner.setText(objData.getArchivoScannerNombre());
        edtFileScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputFileSelected = 1;
                openFileExplorer();
            }
        });

        edtFileMuestAceite = (EditText)findViewById(R.id.edtFileMuestAceite);
        edtFileMuestAceite.setText(objData.getArchivoAceiteNombre());
        edtFileMuestAceite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputFileSelected = 2;
                openFileExplorer();
            }
        });
        edtFileMuestCombus = (EditText)findViewById(R.id.edtFileMuestCombus);
        edtFileMuestCombus.setText(objData.getArchivoCombustibleNombre());
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
        chkScanner.setChecked(objData.getScanner());
        chkAceite = (CheckBox)findViewById(R.id.chkAceite);
        chkAceite.setChecked(objData.getAceite());
        chkCombustible = (CheckBox)findViewById(R.id.chkCombustible);
        chkCombustible.setChecked(objData.getCombustible());

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
                    objDiag.setNuevo(true);

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
                    objDiag.setNuevo(true);
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
                    objDiag.setNuevo(true);
                    listaInformeTecnicoFallaCorrectivo.add(objDiag);
                    populateListCorrectivos();

                }

                edtTrabajocorrec.setText("");
            }
        });



    }

    public void populateCasoTecnico(int IdSistema)
    {
        listaCasoTecnico = repository.casoTecnicoRepository().findAllxSistemaFalla(IdSistema);
        casoTecnicoAdapter = new CasoTecnicoAdapter(listaCasoTecnico);

        for (CasoTecnico obj : listaCasoTecnico)
        {
            if (obj.getIdCasoTecnico() ==  Integer.parseInt(objInformeTecnicoFalla.getNroCaso()))
            {
                obj.setSelected(true);
                break;
            }
        }

        lsvCasoTecnico.setAdapter(casoTecnicoAdapter);
        casoTecnicoAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onClikListener(int position) {
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

    private void mostrarNombresTecnicos(List<Empleado> lista)
    {
        List<String> nombres = new ArrayList<>();

        for (Empleado obj: lista)
        {
            if (obj.getSelected())
            {
                nombres.add(obj.getNombre());
            }

        }

        edtTecnicos.setText(TextUtils.join(", ", nombres));
    }
    public void populateListDiagnostico()
    {
        ArrayList<String> listaString = new ArrayList<>();
        for (InformeTecnicoFallaDiagnostico obj: listaInformeTecnicoFallaDiagnosticos)
        {
            listaString.add(obj.getDescripcion());
        }
       ArrayAdapter<String> adapterComentarios = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaString);
        listDiagnostico.setAdapter(adapterComentarios);
    }
    private void populateListCausa() {
        ArrayList<String> listaString = new ArrayList<>();
        for (InformeTecnicoFallaCausa obj: listaInformeTecnicoFallaCausa)
        {
            listaString.add(obj.getDescripcion());
        }
        ArrayAdapter<String> adapterComentarios = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaString);
        listCausaFalla.setAdapter(adapterComentarios);
    }
    private void populateListCorrectivos() {
        ArrayList<String> listaString = new ArrayList<>();
        for (InformeTecnicoFallaCorrectivos obj: listaInformeTecnicoFallaCorrectivo)
        {
            listaString.add(obj.getDescripcion());
        }
        ArrayAdapter<String> adapterComentarios= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaString);
        listTrabajocorrec.setAdapter(adapterComentarios);
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
                        int IdGenerado = repository.informeTecnicoFallaRepository().update(objInforme);

                        // si todo sale bien se guardan los comentarios
                        if (IdGenerado > 0)
                        {
                            for (InformeTecnicoFallaDiagnostico obj: listaInformeTecnicoFallaDiagnosticos)
                            {
                                if (obj.getNuevo())
                                {
                                    obj.setIdInformeTecnicoFalla(IdGenerado);
                                    repository.informeTecnicoFallaDiagnosticoRepository().create(obj);
                                }

                            }

                            for (InformeTecnicoFallaCausa obj: listaInformeTecnicoFallaCausa)
                            {
                                if (obj.getNuevo())
                                { obj.setIdInformeTecnicoFalla(IdGenerado);
                                    repository.informeTecnicoFallaCausaRepository().create(obj);}

                            }
                            for (InformeTecnicoFallaCorrectivos obj: listaInformeTecnicoFallaCorrectivo)
                            {
                                if (obj.getNuevo())
                                {
                                    obj.setIdInformeTecnicoFalla(IdGenerado);
                                    repository.informeTecnicoFallaCorrectivosRepository().create(obj);
                                }

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

                        Intent inte =new Intent(EditarDetalleCausaActivity.this, AnalisisCausaFallaActivity.class);
                        inte.putExtra("IdInformeTecnico", objInformeTecnicoFalla.getIdInformeTecnico());
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


        MaestraArgu sistemafalla = (MaestraArgu) ( (Spinner) findViewById(R.id.spnSistemaFalla) ).getSelectedItem();
        objInformeTecnicoFalla.setIdArguSistema(sistemafalla.getIdArgu());

        MaestraArgu modofalla = (MaestraArgu) ( (Spinner) findViewById(R.id.spnModoFalla) ).getSelectedItem();
        objInformeTecnicoFalla.setIdArguModoFalla(modofalla.getIdArgu());

        objInformeTecnicoFalla.setNroCaso(nrcaso.getText().toString().trim());


        objInformeTecnicoFalla.setScanner(chkScanner.isChecked());
        if (chkScanner.isChecked())
        {
            if ( fileScanner != null)
            {
                Long timestamp = (System.currentTimeMillis() / 1000)+ 1;
                objInformeTecnicoFalla.setArchivoScannerNombre(filesControl.getFileName(fileScanner.getPath()) );
                objInformeTecnicoFalla.setArchivoScannerNombreGenerado( timestamp + "_" + filesControl.getFileName(fileScanner.getPath()));
            }

        }

        objInformeTecnicoFalla.setAceite(chkAceite.isChecked());
        if (chkAceite.isChecked())
        {
            if (fileAceite != null)
            {
                Long timestampAce = (System.currentTimeMillis() / 1000)+2;
                objInformeTecnicoFalla.setArchivoAceiteNombre(filesControl.getFileName(fileAceite.getPath()));
                objInformeTecnicoFalla.setArchivoAceiteNombreGenerado( timestampAce + "_" + filesControl.getFileName(fileAceite.getPath()));
            }

        }
        objInformeTecnicoFalla.setCombustible(chkCombustible.isChecked());
        if (chkCombustible.isChecked())
        {
            if (fileCombustible != null)
            {
                Long timestampComb = (System.currentTimeMillis() / 1000) + 3;
                objInformeTecnicoFalla.setArchivoCombustibleNombre(edtFileMuestCombus.getText().toString().trim().isEmpty() ? null  : filesControl.getFileName(fileCombustible.getPath()));
                objInformeTecnicoFalla.setArchivoCombustibleNombreGenerado( timestampComb + "_" + filesControl.getFileName(fileCombustible.getPath()));
            }

        }





        return  objInformeTecnicoFalla;



    }
}
