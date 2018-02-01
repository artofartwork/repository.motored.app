package com.movil.summmit.motorresapp.LogicMethods;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.movil.summmit.motorresapp.Listeners.OnRequestListener;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.SyncMaestro;
import com.movil.summmit.motorresapp.R;
import com.movil.summmit.motorresapp.Request.ApiClienteMaestros;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.SyncMaestroRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cgonzalez on 01/02/2018.
 */

public class LogicSync {

   private OnRequestListener listener;
   public  View pDialog,container;

    public LogicSync(OnRequestListener listener,View pDialog)
    {
        this.listener = listener;
        this.pDialog = pDialog;
    }

    public void SyncMaestrosAud()  // 1 es exito, 0 es fallado
    {
        pDialog.setVisibility(View.VISIBLE);

        try {

            final List<SyncMaestro> lista = new ArrayList<>();

            Call<List<SyncMaestro>> call= ApiClienteMaestros.getMyApiClient().listaSyncMaestro();
            call.enqueue(new Callback<List<SyncMaestro>>() {
                @Override
                public void onResponse(Call<List<SyncMaestro>> call, Response<List<SyncMaestro>> response) {

                    if(response!=null && response.isSuccessful()){

                        List<SyncMaestro> listaResponse = response.body();

                        for (SyncMaestro obj : listaResponse)
                        {

                            lista.add(obj);
                        }

                        listener.OnRespuestaSyncMaestros(lista);
                        listener.onPrueba("dasasdasd");
                        onMessageExitoSync();

                    }
                }

                @Override
                public void onFailure(Call<List<SyncMaestro>> call, Throwable t) {
                    Log.d("eeror", "asdf");
                   onMessageFalloSync();
                }
            });



        }catch (Exception e)
        {

        }


    }
    public int onMessageExitoSync() {
        Snackbar snackbar = Snackbar
                .make(pDialog,"¡SINCRONIZACION EXITOSA!", Snackbar.LENGTH_LONG);
        pDialog.setVisibility(View.GONE);
        snackbar.show();
        return  1;
    }

    public int onMessageFalloSync() {
        Snackbar snackbar = Snackbar
                .make(pDialog,"¡SINCRONIZACION FALLIDA!", Snackbar.LENGTH_LONG);

        snackbar.show();
        pDialog.setVisibility(View.GONE);
        return  1;
    }
}
