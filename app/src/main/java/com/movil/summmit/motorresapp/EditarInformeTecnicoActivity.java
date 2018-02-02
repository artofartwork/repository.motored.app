package com.movil.summmit.motorresapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.movil.summmit.motorresapp.Dialogs.DialogAntecedente;
import com.movil.summmit.motorresapp.Listeners.OnAntecedenteListener;
import com.movil.summmit.motorresapp.LogicMethods.Repository;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnico;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAntecedente;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Cliente;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empresa;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.HelpMaestro;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.MaestraArgu;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Marca;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Modelo;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Vin;
import com.movil.summmit.motorresapp.Storage.Files.FilesControl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EditarInformeTecnicoActivity extends AppCompatActivity implements OnAntecedenteListener {
    Spinner spnEmpresa, spnSucursal, spnArea, spnMarca, spnModelo, spnCliente, spnVin, spnComponente, spnAplicacion;
    EditText edtNroOT, edtKm, edtHorometro,edtSerie,edtFechIniGarantia, edtReclamo,edtFechafalla;
    TextInputLayout tmpEmpresa, tmpSucursal, tmpArea, tmpOt, tmpMarca, tmpModelo, tmpCliente, tmpVin, tmpKm, tmpHorometro, tmpComponente, tmpSerie,tmpAplicacion, tmpFechafalla, tmpReclamo ;
    ListView listaAntecedentes;
    Button addAntecedente;
    List<InformeTecnicoAntecedente> lstInformeTecnicoAntecedentesAgregados;
    ArrayAdapter<String> adapterStringAntecedentes;
    private Repository repository;
    private FilesControl controlFile;
    int IdInformeTecnico = 0;
    InformeTecnico objInforme;
    java.util.Date utilDatehoy = new java.util.Date();
    java.sql.Date sqlDatehoy = new java.sql.Date(utilDatehoy.getTime());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_informe_tecnico);
        repository = new Repository(this);
        Intent myIntent = getIntent();
        IdInformeTecnico = myIntent.getIntExtra("IdInformeTecnico", 0);
        objInforme = repository.informeTecnicoRepository().getById(IdInformeTecnico);
        controlFile = new FilesControl();

        initComponents();
        initBindComponentsData(objInforme);
        lstInformeTecnicoAntecedentesAgregados = new ArrayList<>();

    }

    public void initComponents()
    {
        //inicalizo componentes
        tmpEmpresa = (TextInputLayout) findViewById(R.id.tmpEmpresa);
        tmpSucursal = (TextInputLayout) findViewById(R.id.tmpSucursal);
        tmpArea = (TextInputLayout) findViewById(R.id.tmpArea);
        tmpOt = (TextInputLayout) findViewById(R.id.tmpOt);
        tmpMarca = (TextInputLayout) findViewById(R.id.tmpMarca);
        tmpModelo = (TextInputLayout) findViewById(R.id.tmpModelo);
        tmpCliente = (TextInputLayout) findViewById(R.id.tmpCliente);
        tmpVin = (TextInputLayout) findViewById(R.id.tmpVin);
        tmpKm = (TextInputLayout) findViewById(R.id.tmpKm);
        tmpHorometro = (TextInputLayout) findViewById(R.id.tmpHorometro);
        tmpComponente = (TextInputLayout) findViewById(R.id.tmpComponente);
        tmpSerie = (TextInputLayout) findViewById(R.id.tmpSerie);
        tmpAplicacion = (TextInputLayout) findViewById(R.id.tmpAplicacion);
        tmpFechafalla = (TextInputLayout) findViewById(R.id.tmpFechafalla);
        tmpReclamo = (TextInputLayout) findViewById(R.id.tmpReclamo);

        listaAntecedentes = (ListView) findViewById(R.id.listaAntecedentes);

        spnEmpresa = (Spinner)findViewById(R.id.spnEmpresa);
        spnSucursal = (Spinner)findViewById(R.id.spnSucursal);
        spnArea = (Spinner)findViewById(R.id.spnArea);
        spnMarca = (Spinner)findViewById(R.id.spnMarca);
        spnModelo = (Spinner)findViewById(R.id.spnModelo);
        spnCliente = (Spinner)findViewById(R.id.spnCliente);
        spnVin = (Spinner)findViewById(R.id.spnVin);
        spnComponente = (Spinner)findViewById(R.id.spnComponente);
        spnAplicacion = (Spinner)findViewById(R.id.spnAplicacion);

        edtNroOT = (EditText)findViewById(R.id.edtNroOT);
        edtKm = (EditText)findViewById(R.id.edtKm);
        edtHorometro = (EditText)findViewById(R.id.edtHorometro);
        edtSerie = (EditText)findViewById(R.id.edtSerie);
        edtFechIniGarantia = (EditText)findViewById(R.id.edtFechIniGarantia);
        edtReclamo = (EditText)findViewById(R.id.edtReclamo);
        edtFechafalla = (EditText)findViewById(R.id.edtFechafalla);

        addAntecedente = (Button)findViewById(R.id.addAntecedente);
        addAntecedente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialosAntecedente();
            }
        });



        //-------------------------
    }
    private void initBindComponentsData(InformeTecnico objData) {

        List<Empresa> lstEmpresa = repository.empresaRepository().findAll();
        ArrayAdapter empresaAdapter = new ArrayAdapter(this, R.layout.spinner, lstEmpresa);
        spnEmpresa.setAdapter(empresaAdapter);

        for (Empresa obj : lstEmpresa)
        {
            if(obj.getIdEmpresa() == objData.getIdEmpresa())
            {
                spnEmpresa.setSelection(empresaAdapter.getPosition(obj));
                break;
            }
        }

        List<MaestraArgu> lstSucursal = repository.maestraArguRepository().findAll(HelpMaestro.getSucursal());
        ArrayAdapter sucursalAdapter = new ArrayAdapter(this, R.layout.spinner, lstSucursal);
        spnSucursal.setAdapter(sucursalAdapter);

        for (MaestraArgu obj : lstSucursal)
        {
            if (obj.getIdArgu() == objData.getIdArguSucursal())
            {
                spnSucursal.setSelection(sucursalAdapter.getPosition(obj));
                break;
            }
        }


        List<MaestraArgu> lstArea = repository.maestraArguRepository().findAll(HelpMaestro.getArea());
        ArrayAdapter areaAdapter = new ArrayAdapter(this, R.layout.spinner, lstArea);
        spnArea.setAdapter(areaAdapter);

        for (MaestraArgu obj : lstArea)
        {
            if (obj.getIdArgu() == objData.getIdArguArea())
            {
                spnArea.setSelection(areaAdapter.getPosition(obj));
                break;
            }
        }

        final List<Marca> lstMarca = repository.marcaRepository().findAll();
        ArrayAdapter marcaAdapter = new ArrayAdapter(this, R.layout.spinner, lstMarca);
        spnMarca.setAdapter(marcaAdapter);

        for (Marca obj : lstMarca)
        {
            if (obj.getIdMarca() == objData.getIdMarca())
            {
                spnMarca.setSelection(marcaAdapter.getPosition(obj));
                break;
            }
        }

        spnMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Marca objMarca =  lstMarca.get(position);
                populateModelo(objMarca.getIdMarca());
                populateVin();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final List<Cliente>lstCliente = repository.clienteRepository().findAll();
        ArrayAdapter clienteAdapter = new ArrayAdapter(this, R.layout.spinner, lstCliente);
        spnCliente.setAdapter(clienteAdapter);

        for (Cliente obj : lstCliente)
        {
            if (obj.getIdCliente() == objData.getIdCliente())
            {
                spnCliente.setSelection(clienteAdapter.getPosition(obj));
                break;
            }
        }

        spnCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                populateVin();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<MaestraArgu> lstComponente = repository.maestraArguRepository().findAll(HelpMaestro.getComponente());
        ArrayAdapter componenteAdapter = new ArrayAdapter(this, R.layout.spinner, lstComponente);
        spnComponente.setAdapter(componenteAdapter);
        for (MaestraArgu obj : lstComponente)
        {
            if (obj.getIdArgu() == objData.getIdArguComponente())
            {
                spnComponente.setSelection(componenteAdapter.getPosition(obj));
                break;
            }
        }

        List<MaestraArgu> lstAplicacion = repository.maestraArguRepository().findAll(HelpMaestro.getAplicacion());
        ArrayAdapter aplicacionAdapter = new ArrayAdapter(this, R.layout.spinner, lstAplicacion);
        spnAplicacion.setAdapter(aplicacionAdapter);

        for (MaestraArgu obj : lstAplicacion)
        {
            if (obj.getIdArgu() == objData.getIdArguAplicacion())
            {
                spnAplicacion.setSelection(aplicacionAdapter.getPosition(obj));
                break;
            }
        }

        edtNroOT.setText(objData.getNroOT());
        edtKm.setText(objData.getKilometraje().toString());
        edtHorometro.setText(objData.getHorometro().toString());
        edtSerie.setText(objData.getSerie());
        edtFechafalla.setText(objData.getFechaFalla().toString());
        edtReclamo.setText(objData.getObservacion());

        lstInformeTecnicoAntecedentesAgregados = repository.informeTecnicoAntecedenteRepository().findAllxInforme(objData.getIdInformeTecnico());
        populateListAntecedentes();


    }

    private void populateModelo(int IdMarca)
    {
        List<Modelo> lstModelo = repository.modeloRepository().findAllporMarca(IdMarca);
        ArrayAdapter modeloAdapter = new ArrayAdapter(this, R.layout.spinner, lstModelo);
        spnModelo.setAdapter(modeloAdapter);
    }
    private void populateVin()
    {

        Marca marca = (Marca) ( (Spinner) findViewById(R.id.spnMarca) ).getSelectedItem();
        Cliente cliente = (Cliente) ( (Spinner) findViewById(R.id.spnCliente) ).getSelectedItem();

        if(marca != null && cliente != null)
        {
            final List<Vin> lstvin = repository.vinRepository().findAllporMarcaCliente(marca.getIdMarca(), cliente.getIdCliente());
            ArrayAdapter vinAdapter = new ArrayAdapter(this, R.layout.spinner, lstvin);
            spnVin.setAdapter(vinAdapter);

            spnVin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Vin objVin = lstvin.get(position);
                    edtFechIniGarantia.setText(objVin.getFechaEntrega().toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }

    }
    public void openDialosAntecedente()
    {
        DialogAntecedente example = new DialogAntecedente();
        example.show(getSupportFragmentManager(), "dialogAntecedente");
    }

    @Override
    public void recibiryEnviardesdeFragment(String message) {
        InformeTecnicoAntecedente objInfAnt = new InformeTecnicoAntecedente();
        objInfAnt.setAudUsuarioRegistro(1);
        objInfAnt.setAudFechaRegistro(sqlDatehoy);
        objInfAnt.setDescripcion(message);
        objInfAnt.setEsNuevo(true);
        lstInformeTecnicoAntecedentesAgregados.add(objInfAnt);
        populateListAntecedentes();
    }

    public void populateListAntecedentes()
    {
        ArrayList<String> listaString = new ArrayList<>();
        for (InformeTecnicoAntecedente obj: lstInformeTecnicoAntecedentesAgregados)
        {
            listaString.add(obj.getDescripcion());
        }
        adapterStringAntecedentes = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaString);
        listaAntecedentes.setAdapter(adapterStringAntecedentes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        setTitle("Editar Informe");
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

                if (ValidateForm())
                {
                    InformeTecnico objInforme = mapperInformeTecnico();
                    try
                    {
                        int IdGenerado = repository.informeTecnicoRepository().update(objInforme);
                        // si todo sale bien se guardan los antecedentes

                            //String carpeta = "INFORME_" + IdGenerado;
                            //controlFile.getAlbumStorageDirEstacion(carpeta);
                            for (InformeTecnicoAntecedente obj: lstInformeTecnicoAntecedentesAgregados)
                            {
                                if (obj.getEsNuevo())
                                {
                                    obj.setIdInformeTecnico(IdGenerado);
                                    repository.informeTecnicoAntecedenteRepository().create(obj);
                                }

                            }


                        Intent inte =new Intent(EditarInformeTecnicoActivity.this, AnalisisCausaFallaActivity.class);
                        inte.putExtra("IdInformeTecnico", IdGenerado);
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
    public Boolean ValidateForm()
    {
        Empresa empresa = (Empresa) ( (Spinner) findViewById(R.id.spnEmpresa) ).getSelectedItem();
        if (empresa == null) {
            tmpEmpresa.setError("Este campo es obligatorio");
            return false;
        }
        MaestraArgu sucursal = (MaestraArgu) ( (Spinner) findViewById(R.id.spnSucursal) ).getSelectedItem();
        if (sucursal == null) {
            tmpSucursal.setError("Este campo es obligatorio");
            return false;
        }
        MaestraArgu area = (MaestraArgu) ( (Spinner) findViewById(R.id.spnArea) ).getSelectedItem();
        if (area == null) {
            tmpArea.setError("Este campo es obligatorio");
            return false;
        }

        if ( edtNroOT.getText().toString().trim().isEmpty())
        {
            tmpOt.setError("Este campo es obligatorio");
            return false;
        }

        Marca marca = (Marca) ( (Spinner) findViewById(R.id.spnMarca) ).getSelectedItem();
        if (marca == null) {
            tmpMarca.setError("Este campo es obligatorio");
            return false;
        }
        Modelo modelo = (Modelo) ( (Spinner) findViewById(R.id.spnModelo) ).getSelectedItem();
        if (modelo == null) {
            tmpModelo.setError("Este campo es obligatorio");
            return false;
        }
        Cliente cliente = (Cliente) ( (Spinner) findViewById(R.id.spnCliente) ).getSelectedItem();
        if (cliente == null) {
            tmpCliente.setError("Este campo es obligatorio");
            return false;
        }

        Vin vin = (Vin) ( (Spinner) findViewById(R.id.spnVin) ).getSelectedItem();
        if (vin == null) {
            tmpVin.setError("Este campo es obligatorio");
            return false;
        }

        if ( edtKm.getText().toString().trim().isEmpty())
        {
            tmpKm.setError("Este campo es obligatorio");
            return false;
        }
        if ( edtHorometro.getText().toString().trim().isEmpty())
        {
            tmpHorometro.setError("Este campo es obligatorio");
            return false;
        }
        MaestraArgu componente = (MaestraArgu) ( (Spinner) findViewById(R.id.spnComponente) ).getSelectedItem();
        if (componente == null) {
            tmpComponente.setError("Este campo es obligatorio");
            return false;
        }

        if ( edtSerie.getText().toString().trim().isEmpty())
        {
            tmpSerie.setError("Este campo es obligatorio");
            return false;
        }
        MaestraArgu aplicacion = (MaestraArgu) ( (Spinner) findViewById(R.id.spnAplicacion) ).getSelectedItem();
        if (aplicacion == null) {
            tmpAplicacion.setError("Este campo es obligatorio");
            return false;
        }

        if ( edtFechafalla.getText().toString().trim().isEmpty())
        {
            tmpFechafalla.setError("Este campo es obligatorio");
            return false;
        }

        if ( edtReclamo.getText().toString().trim().isEmpty())
        {
            tmpReclamo.setError("Este campo es obligatorio");
            return false;
        }

        return true;
    }
    public InformeTecnico mapperInformeTecnico() {

        try
        {

            Empresa empresa = (Empresa) ( (Spinner) findViewById(R.id.spnEmpresa) ).getSelectedItem();
            objInforme.setIdEmpresa(empresa.getIdEmpresa());

            MaestraArgu sucursal = (MaestraArgu) ( (Spinner) findViewById(R.id.spnSucursal) ).getSelectedItem();
            objInforme.setIdArguSucursal(sucursal.getIdArgu());

            MaestraArgu area = (MaestraArgu) ( (Spinner) findViewById(R.id.spnArea) ).getSelectedItem();
            objInforme.setIdArguArea(area.getIdArgu());

            String nroOt= edtNroOT.getText().toString().trim();
            objInforme.setNroOT(nroOt);

            Marca marca = (Marca) ( (Spinner) findViewById(R.id.spnMarca) ).getSelectedItem();
            objInforme.setIdMarca(marca.getIdMarca());

            Modelo modelo = (Modelo) ( (Spinner) findViewById(R.id.spnModelo) ).getSelectedItem();
            objInforme.setIdModelo(modelo.getIdModelo());

            Cliente cliente = (Cliente) ( (Spinner) findViewById(R.id.spnCliente) ).getSelectedItem();
            objInforme.setIdCliente(cliente.getIdCliente());

            Vin vin = (Vin) ( (Spinner) findViewById(R.id.spnVin) ).getSelectedItem();
            objInforme.setVIN(vin.getIdChasis() + "");

            String km = edtKm.getText().toString().trim();
            objInforme.setKilometraje(Double.parseDouble(km));

            String horometro = edtHorometro.getText().toString().trim();
            objInforme.setHorometro(Double.parseDouble(horometro));

            MaestraArgu componente = (MaestraArgu) ( (Spinner) findViewById(R.id.spnComponente) ).getSelectedItem();
            objInforme.setIdArguComponente(componente.getIdArgu());

            String serie = edtSerie.getText().toString().trim();
            objInforme.setSerie(serie);

            MaestraArgu aplicacion = (MaestraArgu) ( (Spinner) findViewById(R.id.spnAplicacion) ).getSelectedItem();
            objInforme.setIdArguAplicacion(aplicacion.getIdArgu());

            String fechafalla = edtFechafalla.getText().toString().trim();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date utilDate = format.parse(fechafalla);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            objInforme.setFechaFalla(sqlDate);


            String reclamo =  edtReclamo.getText().toString().trim();
            objInforme.setObservacion(reclamo);



            objInforme.setAudFechaModifica(sqlDatehoy);

            objInforme.setAudUsuarioModifica(1);

            String fechaini = edtFechIniGarantia.getText().toString().trim();
            java.util.Date utilDatefechaini = format.parse(fechaini);
            java.sql.Date sqlDatefechaini = new java.sql.Date(utilDatefechaini.getTime());
            objInforme.setFechaInicio(sqlDatefechaini);

            return objInforme;
        }
        catch (Exception ex)
        {
            return  null;
        }


    }
}
