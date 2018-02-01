package com.movil.summmit.motorresapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.movil.summmit.motorresapp.Request.VerifyInternet;

public class GuardarEnviarActivity extends AppCompatActivity {
    Button btnGuardar, btnEnviar;
    VerifyInternet verifyInternet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_enviar);
        verifyInternet = new VerifyInternet(this);

        btnEnviar = (Button)findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean stateInternet = verifyInternet.isInternetConnected();
                if (stateInternet)
                {
                    //aqui lanzar operacion de enviar, conectandose al servicio, aqui manda un mail, este mail lo mandara el servicio
                }
            }
        });

        btnGuardar = (Button)findViewById(R.id.btnGuardar);

    }

    public void finalizar(View view) {

        Intent inte =new Intent(GuardarEnviarActivity.this, ListaInformesActivity.class);
        startActivity(inte);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        setTitle("Guardar o Enviar");
      //  getMenuInflater().inflate(R.menu.menu_nuevo, menu);
        //super.onCreateOptionsMenu(menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       // Intent inte =new Intent(AdjuntosActivity.this, GuardarEnviarActivity.class);
        //startActivity(inte);
        //return super.onOptionsItemSelected(item);
        return true;
    }




}
