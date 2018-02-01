package com.movil.summmit.motorresapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bm.library.Info;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnico;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoRecomendacionesRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoRepository;

public class DetailInformeTecnicoActivity extends AppCompatActivity {
    int IdInformeTecnico = 0;
    TextView txvEmpresa, txvSucursal, txvArea, txvNroOT,txvMarca, txvModelo, txvCliente, txvVin, txvKm, txvHorom, txvComponente, txvSerie, txvAplicacion, txvFechaGarantia, txvFechaFalla, txvReclamo;
    InformeTecnicoRepository informeTecnicoRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_informe_tecnico);
        Intent myIntent = getIntent(); // gets the previously created intent
        IdInformeTecnico = myIntent.getIntExtra("IdInformeTecnico", 0);
        informeTecnicoRepository = new InformeTecnicoRepository(this);
        initElements();
        initDataElements();
    }
    public void initElements()
    {
        txvEmpresa = (TextView)findViewById(R.id.txvEmpresa);
        txvSucursal = (TextView)findViewById(R.id.txvSucursal);
        txvArea = (TextView)findViewById(R.id.txvArea);
        txvNroOT = (TextView)findViewById(R.id.txvNroOT);
        txvMarca = (TextView)findViewById(R.id.txvMarca);
        txvModelo = (TextView)findViewById(R.id.txvModelo);
        txvCliente = (TextView)findViewById(R.id.txvCliente);
        txvVin = (TextView)findViewById(R.id.txvVin);
        txvKm = (TextView)findViewById(R.id.txvKm);
        txvHorom = (TextView)findViewById(R.id.txvHorom);
        txvComponente = (TextView)findViewById(R.id.txvComponente);
        txvSerie = (TextView)findViewById(R.id.txvSerie);
        txvAplicacion = (TextView)findViewById(R.id.txvAplicacion);
        txvFechaGarantia = (TextView)findViewById(R.id.txvFechaGarantia);
        txvFechaFalla =(TextView)findViewById(R.id.txvFechaFalla);
        txvReclamo =(TextView)findViewById(R.id.txvReclamo);
    }

    public void initDataElements()
    {
        InformeTecnico objInforme = informeTecnicoRepository.findEntidad(IdInformeTecnico);

        txvEmpresa.setText(objInforme.getNombreEmpresa());
        txvSucursal.setText(objInforme.getNombreSucursal());
        txvArea.setText(objInforme.getNombreArea());
        txvNroOT.setText(objInforme.getNroOT());
        txvMarca.setText(objInforme.getNombreMarca());
        txvModelo.setText(objInforme.getNombreModelo());
        txvCliente.setText(objInforme.getNombreCliente());
        txvVin.setText(objInforme.getNombreVin());
        txvKm.setText(objInforme.getKilometraje().toString());
        txvHorom.setText(objInforme.getHorometro().toString());
        txvComponente.setText(objInforme.getNombreComponente());
        txvSerie.setText(objInforme.getSerie().toString());
        txvAplicacion.setText(objInforme.getNombreAplicacion().toString());
        txvFechaGarantia.setText(objInforme.getFechaInicio().toString());
        txvFechaFalla.setText(objInforme.getFechaFalla().toString());
        txvReclamo.setText(objInforme.getObservacion());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        setTitle("Detalle Informe");
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent inte;
        switch (id)
        {
            case R.id.action_home:
              inte = new Intent(DetailInformeTecnicoActivity.this, ListaInformesActivity.class);
                startActivity(inte);
                break;
            case R.id.action_next:
                inte =new Intent(DetailInformeTecnicoActivity.this, DetailTwoActivity.class);
                inte.putExtra("IdInformeTecnico", IdInformeTecnico);
                startActivity(inte);
                break;
        }
        return true;
    }
}
