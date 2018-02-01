package com.movil.summmit.motorresapp;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.movil.summmit.motorresapp.Adapters.AnalisisCausaFallaAdapter;
import com.movil.summmit.motorresapp.LogicMethods.Repository;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFalla;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empleado;
import com.movil.summmit.motorresapp.controllerSwipe.SwipeController;
import com.movil.summmit.motorresapp.controllerSwipe.SwipeControllerActions;

import java.util.ArrayList;
import java.util.List;


public class AnalisisCausaFallaActivity extends AppCompatActivity {

    AnalisisCausaFallaAdapter mAdapter;
    Repository repository;
    Button addDetalleCausaFalla;
    SwipeController swipeController = null;
    int IdInformeTecnico = 0;
    List<InformeTecnicoFalla> players;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis_causa_falla);
        repository = new Repository(this);

        Intent myIntent = getIntent(); // gets the previously created intent
        IdInformeTecnico = myIntent.getIntExtra("IdInformeTecnico", 0);
        //---

        addDetalleCausaFalla = (Button)findViewById(R.id.addDetalleCausaFalla);

        addDetalleCausaFalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openDialogDetalleFalla();
                Intent inte =new Intent(AnalisisCausaFallaActivity.this, DetalleCausaAnalisisFallaActivity.class);
                inte.putExtra("IdInformeTecnico", IdInformeTecnico);
                startActivity(inte);
            }
        });


        setPlayersDataAdapter();
        setupRecyclerView();

    }

    private void setPlayersDataAdapter() {
        players =  repository.informeTecnicoFallaRepository().findAllxInforme(IdInformeTecnico);

        mAdapter = new AnalisisCausaFallaAdapter(players);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.listaanalisiscausa);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        swipeController = new SwipeController(1, new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {

                InformeTecnicoFalla obj =players.get(position);
                repository.informeTecnicoFallaRepository().delete(obj);
                mAdapter.players.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
            }
            @Override
            public void onLeftClicked(int position) {
                //Toast.makeText(AnalisisCausaFallaActivity.this, "hola editame" + position, Toast.LENGTH_SHORT).show();
                Intent inte =new Intent(AnalisisCausaFallaActivity.this, EditarDetalleCausaActivity.class);
                InformeTecnicoFalla obj =players.get(position);
                inte.putExtra("IdInformeTecnicoFalla", obj.getIdInformeTecnicoFalla());
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

        setTitle("Analisis causa fallas");
         getMenuInflater().inflate(R.menu.menu_analisis_causas, menu);
        //super.onCreateOptionsMenu(menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_next:
                Intent inte =new Intent(AnalisisCausaFallaActivity.this, ConclusionesRecomendacionesActivity.class);
                inte.putExtra("IdInformeTecnico", IdInformeTecnico);
                startActivity(inte);
                break;
        }

        //return super.onOptionsItemSelected(item);
        //inte.putExtra("IdInformeTecnico", IdInformeTecnico);
        return true;
    }


    /*public void openDialogDetalleFalla()
    {
       // DialogDetalleCausaAnalisisFalla dialog = new DialogDetalleCausaAnalisisFalla();
       // dialog.show(getSupportFragmentManager(), "dialog antecedente");

    }*/

}
