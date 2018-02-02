package com.movil.summmit.motorresapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.movil.summmit.motorresapp.LogicMethods.Repository;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAdjuntos;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAdjuntosDetalle;
import com.movil.summmit.motorresapp.Storage.Files.FilesControl;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.NormalFilePickActivity;
import com.vincent.filepicker.filter.entity.NormalFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EditarAdjuntosActivity extends AppCompatActivity {
    LinearLayout layout;
    ListView lstAdjuntosDetalle;
    EditText edtVin, edtKmHoras;
    TextInputLayout tmpVin, tmpKmHoras;
    List<EditText> listaEditTextFiles;
    Integer inputFileSelected = 0;
    FilesControl filesControl;
    String estacionPath;
    int IdInformeTecnico = 0;
    Repository repository;
    EditText edtGeneradoClikeado;
    Integer Seleccion = 0;
    static NormalFile fileVin;
    static NormalFile fileKmHoras;
    InformeTecnicoAdjuntos informeTecnicoAdjuntos;

    int actualizoVin = 0;
    int actualizoKm = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_adjuntos);

        Intent myIntent = getIntent(); // gets the previously created intent
        IdInformeTecnico = myIntent.getIntExtra("IdInformeTecnico", 0);

        repository = new Repository(this);

        informeTecnicoAdjuntos = repository.informeTecnicoAdjuntosRepository().findxInforme(IdInformeTecnico);

        init();

        filesControl = new FilesControl();
        estacionPath =  filesControl.getAlbumStorageDirEstacion("INFORME_" + IdInformeTecnico) + File.separator;


        List<InformeTecnicoAdjuntosDetalle> listaDetalles = repository.informeTecnicoAdjuntosDetalleRepository().findAllxInforme(IdInformeTecnico, informeTecnicoAdjuntos.getIdAdjuntos());
        ArrayList<String> detalles = new ArrayList<>();
        for (InformeTecnicoAdjuntosDetalle obj : listaDetalles)
        {
            detalles.add(obj.getArchivoNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, detalles);
        lstAdjuntosDetalle.setAdapter(adapter);

        edtVin.setText(informeTecnicoAdjuntos.getArchivoNombreVin());
        edtKmHoras.setText(informeTecnicoAdjuntos.getArchivoNombreKm());
    }

    public void init()
    {
        lstAdjuntosDetalle = (ListView)findViewById(R.id.lstAdjuntosDetalle);
        tmpVin = (TextInputLayout)findViewById(R.id.tmpVin);
        tmpKmHoras = (TextInputLayout)findViewById(R.id.tmpKmHoras);

        listaEditTextFiles = new ArrayList<>();
        edtVin = (EditText)findViewById(R.id.edtVin);
        edtVin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seleccion = 0;
                inputFileSelected = 1;
                openFileExplorer();
            }
        });
        edtKmHoras = (EditText)findViewById(R.id.edtKmHoras);
        edtKmHoras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seleccion = 0;
                inputFileSelected = 2;
                openFileExplorer();
            }
        });
        layout =(LinearLayout)findViewById(R.id.agregados);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        setTitle("Adjuntos");
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

                    InformeTecnicoAdjuntos objMapper = mapperInformeTecnicoAdjuntos();
                    try
                    {
                        int IdGenerado = repository.informeTecnicoAdjuntosRepository().update(objMapper);

                        if (actualizoVin == 1)
                        {
                            if (objMapper.getArchivoNombreVin() != null)
                            {
                                File srcVin = new File(fileVin.getPath());
                                File destScaner = new File(estacionPath + objMapper.getArchivoNombreVinGenerado());
                                filesControl.copy(srcVin, destScaner);

                            }
                        }

                        if (actualizoKm == 1)
                        {
                            if (objMapper.getArchivoNombreKm() != null)
                            {
                                File srcKm = new File(fileKmHoras.getPath());
                                File destKM = new File(estacionPath + objMapper.getArchivoNombreKmGenerado());
                                filesControl.copy(srcKm, destKM);
                            }
                        }



                        List<InformeTecnicoAdjuntosDetalle> listaDetalle =   mapperAdjuntoDetalleLis();
                        if (IdGenerado > 0)
                        {

                            for (InformeTecnicoAdjuntosDetalle obj: listaDetalle)
                            {
                                obj.setIdAdjuntos(IdGenerado);
                                repository.informeTecnicoAdjuntosDetalleRepository().create(obj);

                                File src = new File(obj.getSrcPath());
                                File destin = new File(obj.getDestPath());
                                filesControl.copy(src,destin);

                            }

                        }

                        Intent inte =new Intent(EditarAdjuntosActivity.this, GuardarEnviarActivity.class);
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

        return true;
    }

    public void agregaFiles(View view) {

        final EditText edt = new EditText(this);
        edt.setLayoutParams(layout.getLayoutParams());
        edt.setTextSize(10);
        edt.setHint("Seleccionar archivo");
        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seleccion = 1;
                edtGeneradoClikeado = edt;
                openFileExplorer();
            }
        });
        listaEditTextFiles.add(edt);
        layout.addView(edt);


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
            ArrayList<NormalFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE);
            if (Seleccion == 0)
            {
                switch (inputFileSelected)
                {
                    case 1:
                        fileVin = list.get(0);
                        actualizoVin = 1;
                        // filename = "vin_" + filename;
                        edtVin.setText(fileVin.getPath());
                        break;
                    case 2:
                        fileKmHoras = list.get(0);
                        actualizoKm = 1;
                        // filename = "kmh_" + filename;
                        edtKmHoras.setText(fileKmHoras.getPath());
                        break;
                }
            }
            else if (Seleccion == 1)
            {
                NormalFile objnormal = list.get(0);
                //filename = "det_" + filename;
                edtGeneradoClikeado.setText(objnormal.getPath());
            }
        }
    }

    public Boolean validateForm(){

        if(edtVin.getText().toString().trim().isEmpty())
        {
            tmpVin.setError("Este campo es obligatorio");
            return false;
        }

        if(edtKmHoras.getText().toString().trim().isEmpty())
        {
            tmpKmHoras.setError("Este campo es obligatorio");
            return false;
        }

        for (EditText obj : listaEditTextFiles)
        {
            if (obj.getText().toString().isEmpty())
            {
                obj.setError("Este campo es obligatorio");
                return false;
            }
        }

        return true;
    }

    public InformeTecnicoAdjuntos mapperInformeTecnicoAdjuntos()
    {

        if (actualizoVin == 1)
        {
            Long timesVin = (System.currentTimeMillis() / 1000)+1;
            informeTecnicoAdjuntos.setArchivoNombreVin(filesControl.getFileName(fileVin.getPath()) );
            informeTecnicoAdjuntos.setArchivoNombreVinGenerado( timesVin + "_" + filesControl.getFileName(fileVin.getPath()));
        }


        if (actualizoKm == 1)
        {
            Long timesKm = (System.currentTimeMillis() / 1000)+2;
            informeTecnicoAdjuntos.setArchivoNombreKm(filesControl.getFileName(fileKmHoras.getPath()) );
            informeTecnicoAdjuntos.setArchivoNombreKmGenerado( timesKm + "_" + filesControl.getFileName(fileKmHoras.getPath()));
        }



        return  informeTecnicoAdjuntos;
    }

    public List<InformeTecnicoAdjuntosDetalle>  mapperAdjuntoDetalleLis(){

        List<InformeTecnicoAdjuntosDetalle> lista = new ArrayList<>();

        int count = 18;
        for (EditText edtx : listaEditTextFiles)
        {

            InformeTecnicoAdjuntosDetalle objDet = new InformeTecnicoAdjuntosDetalle();
            objDet.setIdInformeTecnico(IdInformeTecnico);
            objDet.setArchivoNombre(filesControl.getFileName(edtx.getText().toString().trim()));

            Long time = (System.currentTimeMillis() / 1000)+ count;
            objDet.setArchivoNombreGenerado(time + "_" + filesControl.getFileName(edtx.getText().toString().trim()));

            objDet.setSrcPath(edtx.getText().toString().trim());
            objDet.setDestPath(estacionPath + objDet.getArchivoNombreGenerado());

            lista.add(objDet);
            count ++;
        }

        return lista;

    }
}
