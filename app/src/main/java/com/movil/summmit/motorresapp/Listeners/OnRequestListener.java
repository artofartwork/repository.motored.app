package com.movil.summmit.motorresapp.Listeners;

import com.movil.summmit.motorresapp.Models.Enity.Maestro.SyncMaestro;

import java.util.List;

/**
 * Created by cgonzalez on 01/02/2018.
 */

public interface OnRequestListener {

    void OnRespuestaSyncMaestros(List<SyncMaestro> lista);
    void onPrueba(String data);
}
