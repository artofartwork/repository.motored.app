package com.movil.summmit.motorresapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoConclusiones;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoRecomendaciones;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoConclusionesRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoRecomendacionesRepository;

import java.util.ArrayList;

public class ConclusionesRecomendacionesActivity extends AppCompatActivity {

    Button btnConclusiones, btnRecomendaciones;
    EditText edtConclusiones, edtRecomendaciones;
    ListView lsvConclusiones, lsvRecomendaciones;
    int IdInformeTecnico = 0;
    InformeTecnicoConclusionesRepository informeTecnicoConclusionesRepository;
    InformeTecnicoRecomendacionesRepository informeTecnicoRecomendacionesRepository;
    ArrayList<String> lstConclusiones;
    ArrayList<String> lstRecomendaciones;
    ArrayAdapter<String> adaptatadorComentario;
    java.util.Date utilDatehoy;
    java.sql.Date sqlDatehoy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusiones_recomendaciones);

        utilDatehoy = new java.util.Date();
        sqlDatehoy =  new java.sql.Date(utilDatehoy.getTime());

        Intent myIntent = getIntent();
        IdInformeTecnico = myIntent.getIntExtra("IdInformeTecnico", 0);

        informeTecnicoConclusionesRepository = new InformeTecnicoConclusionesRepository(this);
        informeTecnicoRecomendacionesRepository = new InformeTecnicoRecomendacionesRepository(this);

        init();
    }

    public void init()
    {
        edtRecomendaciones = (EditText)findViewById(R.id.edtRecomendaciones);
        edtConclusiones = (EditText)findViewById(R.id.edtConclusiones);

        btnConclusiones = (Button)findViewById(R.id.btnConclusiones);
        btnConclusiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AgregaComentario("C", edtConclusiones.getText().toString().trim());
                edtConclusiones.setText("");
            }
        });

        btnRecomendaciones = (Button)findViewById(R.id.btnRecomendaciones);
        btnRecomendaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregaComentario("R", edtRecomendaciones.getText().toString().trim());
                edtRecomendaciones.setText("");

            }
        });

        lstConclusiones = new ArrayList<>();
        lstRecomendaciones = new ArrayList<>();

        lsvConclusiones =(ListView)findViewById(R.id.lsvConclusiones);
        lsvRecomendaciones =(ListView)findViewById(R.id.lsvRecomendaciones);

        Resources res = getResources();
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Conclusiones");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Recomendaciones");
        tabs.addTab(spec);

        tabs.setCurrentTab(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        setTitle("Conclusiones y Recom.");
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
                for (String texto: lstConclusiones)
                {
                    InformeTecnicoConclusiones objnew = new InformeTecnicoConclusiones();
                    objnew.setIdInformeTecnico(IdInformeTecnico);
                    objnew.setAudFechaRegistro(sqlDatehoy);
                    objnew.setAudUsuarioRegistro(1);
                    objnew.setDescripcion(texto);
                    informeTecnicoConclusionesRepository.create(objnew);
                }
                for (String texto: lstRecomendaciones)
                {
                    InformeTecnicoRecomendaciones objnew = new InformeTecnicoRecomendaciones();
                    objnew.setIdInformeTecnico(IdInformeTecnico);
                    objnew.setAudFechaRegistro(sqlDatehoy);
                    objnew.setAudUsuarioRegistro(1);
                    objnew.setDescripcion(texto);
                    informeTecnicoRecomendacionesRepository.create(objnew);
                }

                Intent inte =new Intent(ConclusionesRecomendacionesActivity.this, AdjuntosActivity.class);
                inte.putExtra("IdInformeTecnico", IdInformeTecnico);
                startActivity(inte);

                break;
        }



        return true;
    }

    public void AgregaComentario(String tipo, String comentario)
    {
        if (comentario.length() > 2)
        {
            switch (tipo)
            {
                case "C":
                    lstConclusiones.add(comentario);
                    adaptatadorComentario = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, lstConclusiones);
                    lsvConclusiones.setAdapter(adaptatadorComentario);
                    adaptatadorComentario = null;
                    break;
                case "R":
                    lstRecomendaciones.add(comentario);
                    adaptatadorComentario = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, lstRecomendaciones);
                    lsvRecomendaciones.setAdapter(adaptatadorComentario);
                    adaptatadorComentario = null;
                    break;

            }
        }


    }

}
