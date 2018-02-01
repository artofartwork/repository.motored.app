package com.movil.summmit.motorresapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.movil.summmit.motorresapp.Adapters.AnalisisCausaFallaAdapter;
import com.movil.summmit.motorresapp.LogicMethods.Repository;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnico;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAdjuntosDetalle;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoConclusiones;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFalla;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaCausa;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaCorrectivos;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaDiagnostico;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoRecomendaciones;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoAdjuntosDetalleRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoConclusionesRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoFallaRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoRecomendacionesRepository;
import com.movil.summmit.motorresapp.controllerSwipe.SwipeController;
import com.movil.summmit.motorresapp.controllerSwipe.SwipeControllerActions;

import java.util.ArrayList;
import java.util.List;

public class DetailTwoActivity extends AppCompatActivity {

    ListView lsvAdjuntos, lsvConclusiones, lsvRecomendaciones, listDiagnostico, listCausaFalla, listTrabajocorrec;
    TextView txvTecnicos, txvSistema, txvModo, txvNroCaso;
    EditText edtFileScanner, edtFileMuestAceite, edtFileMuestCombus;
    CheckBox chkScanner, chkAceite, chkCombustible;
    LinearLayout containerDetalle;
    AnalisisCausaFallaAdapter mAdapter;
    Repository repository;
    SwipeController swipeController = null;
    Button btnCerrarDetail;
    int IdInformeTecnico = 0;
    List<InformeTecnicoFalla> players;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_two);
        Intent myIntent = getIntent(); // gets the previously created intent
        IdInformeTecnico = myIntent.getIntExtra("IdInformeTecnico", 0);
        repository = new Repository(this);
        players = repository.informeTecnicoFallaRepository().findAllxInforme(IdInformeTecnico);
        initComponents();
      //  setPlayersDataAdapter();
        setupRecyclerView();
        initComponentsTabs();

        initListaAdjjuntos();
        initConclusiones();
        initRecomendaciones();



    }
    private void initComponents()
    {
        btnCerrarDetail = (Button)findViewById(R.id.btnCerrarDetail);
        btnCerrarDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CerrarDetalle();
            }
        });
        containerDetalle = (LinearLayout)findViewById(R.id.containerDetalle);
        lsvAdjuntos =(ListView)findViewById(R.id.lsvAdjuntos);
        lsvConclusiones =(ListView)findViewById(R.id.lsvConclusiones);
        lsvRecomendaciones =(ListView)findViewById(R.id.lsvRecomendaciones);

        listDiagnostico =(ListView)findViewById(R.id.listDiagnostico);
        listCausaFalla =(ListView)findViewById(R.id.listCausaFalla);
        listTrabajocorrec=(ListView)findViewById(R.id.listTrabajocorrec);

        txvTecnicos = (TextView) findViewById(R.id.txvTecnicos);
        txvSistema = (TextView) findViewById(R.id.txvSistema);
        txvModo = (TextView) findViewById(R.id.txvModo);
        txvNroCaso = (TextView)findViewById(R.id.txvNroCaso);

        edtFileScanner = (EditText) findViewById(R.id.edtFileScanner);
        edtFileMuestAceite = (EditText) findViewById(R.id.edtFileMuestAceite);
        edtFileMuestCombus = (EditText) findViewById(R.id.edtFileMuestCombus);

        chkScanner = (CheckBox) findViewById(R.id.chkScanner);
        chkAceite = (CheckBox) findViewById(R.id.chkAceite);
        chkCombustible = (CheckBox) findViewById(R.id.chkCombustible);
        // CheckBox chkScanner, chkAceite, chkCombustible;


    }
    private void initRecomendaciones()
    {
        List<InformeTecnicoRecomendaciones> lista = repository.informeTecnicoRecomendacionesRepository().findAllxInforme(IdInformeTecnico);
        List<String> datos = new ArrayList<>();
        for (InformeTecnicoRecomendaciones obj : lista)
        {
            datos.add(obj.getDescripcion());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos);
        lsvRecomendaciones.setAdapter(adapter);
    }
    private void initConclusiones()
    {
        List<InformeTecnicoConclusiones> lista = repository.informeTecnicoConclusionesRepository().findAllxInforme(IdInformeTecnico);
        List<String> datos = new ArrayList<>();
        for (InformeTecnicoConclusiones obj : lista)
        {
            datos.add(obj.getDescripcion());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos);
        lsvConclusiones.setAdapter(adapter);
    }
    private void initListaAdjjuntos()
    {
        List<InformeTecnicoAdjuntosDetalle> lista = repository.informeTecnicoAdjuntosDetalleRepository().findAllxInforme(IdInformeTecnico);
        List<String> adjuntosname = new ArrayList<>();
        for (InformeTecnicoAdjuntosDetalle obj : lista)
        {
            adjuntosname.add(obj.getArchivoNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, adjuntosname);
        lsvAdjuntos.setAdapter(adapter);
    }

    private void setupRecyclerView() {

        mAdapter = new AnalisisCausaFallaAdapter(players);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.listaanalisiscausa);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        swipeController = new SwipeController(2, new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {


                ObtenerDatosDetalleCausa(players.get(position).getIdInformeTecnicoFalla());
                CerrarDetalle();
                //aqui poner la funcion para mostrar el fragment con detalles.
            }

            @Override
            public void onLeftClicked(int position) {
               // Toast.makeText(getBaseContext(), "hola mundo" + position, Toast.LENGTH_SHORT).show();
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

    public void initComponentsTabs()
    {
        Resources res = getResources();
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Adjuntos");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Conclusiones");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Recomendaciones");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        TabHost tabsDetail=(TabHost)findViewById(R.id.tabhostDetail);
        tabsDetail.setup();

        TabHost.TabSpec specDetail = tabsDetail.newTabSpec("mitab1 det");
        specDetail.setContent(R.id.tab1Detail);
        specDetail.setIndicator("Trab. Diagnostico");
        tabsDetail.addTab(specDetail);

        specDetail=tabsDetail.newTabSpec("mitab2 det");
        specDetail.setContent(R.id.tab2Detail);
        specDetail.setIndicator("Causa Falla");
        tabsDetail.addTab(specDetail);

        specDetail=tabsDetail.newTabSpec("mitab3 det");
        specDetail.setContent(R.id.tab3Detail);
        specDetail.setIndicator("Trab. Correctivo");
        tabsDetail.addTab(specDetail);

        tabsDetail.setCurrentTab(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        setTitle("Detalles Informe");
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        //super.onCreateOptionsMenu(menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_home:
                Intent inte =new Intent(DetailTwoActivity.this, ListaInformesActivity.class);
                startActivity(inte);
                break;
        }

        //return super.onOptionsItemSelected(item);
        //inte.putExtra("IdInformeTecnico", IdInformeTecnico);
        return true;
    }


    public void CerrarDetalle() {

        if (!containerDetalle.isShown())
        {
            containerDetalle.setVisibility(View.VISIBLE);
        }
        else
        {
            containerDetalle.setVisibility(View.GONE);
        }
    }

    public void ObtenerDatosDetalleCausa(int IdInformeFalla)
    {
        InformeTecnicoFalla objData = repository.informeTecnicoFallaRepository().findxId(IdInformeFalla);
        setearDatosDetalles(objData);
        //return objData;
    }


    public void setearDatosDetalles(InformeTecnicoFalla objData)
    {
        txvTecnicos.setText(objData.getNombresTecnicos());
        txvSistema.setText(objData.getNombreSistema());
        txvModo.setText(objData.getNombreModoFalla());
        txvNroCaso.setText(objData.getNroCaso());

        chkAceite.setChecked(objData.getAceite());
        chkCombustible.setChecked(objData.getCombustible());
        chkScanner.setChecked(objData.getScanner());

        edtFileMuestAceite.setText(objData.getArchivoAceiteNombre());
        edtFileMuestCombus.setText(objData.getArchivoCombustibleNombre());
        edtFileScanner.setText(objData.getArchivoScannerNombre());

        List<String> lista1 = repository.informeTecnicoFallaDiagnosticoRepository().findAllxInformeTecnicoFalla(objData.getIdInformeTecnicoFalla());
        List<String> lista2 = repository.informeTecnicoFallaCausaRepository().findAllxInformeTecnicoFalla(objData.getIdInformeTecnicoFalla());
        List<String> lista3 = repository.informeTecnicoFallaCorrectivosRepository().findAllxInformeTecnicoFalla(objData.getIdInformeTecnicoFalla());

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, lista1);
        listDiagnostico.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, lista2);
        listCausaFalla.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, lista3);
        listTrabajocorrec.setAdapter(adapter3);


    }


}
