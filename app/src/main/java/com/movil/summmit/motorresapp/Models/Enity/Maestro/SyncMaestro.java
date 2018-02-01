package com.movil.summmit.motorresapp.Models.Enity.Maestro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

/**
 * Created by cgonzalez on 01/02/2018.
 */

@DatabaseTable
public class SyncMaestro {
    @DatabaseField(id = true)
    private Integer IdSync;
    @DatabaseField
    private String NombreTabla;
    @DatabaseField
    private String AudFechaModifica;

    public String getNombreTabla() {
        return NombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        NombreTabla = nombreTabla;
    }

    public String getAudFechaModifica() {
        return AudFechaModifica;
    }

    public void setAudFechaModifica(String audFechaModifica) {
        AudFechaModifica = audFechaModifica;
    }

    public Integer getIdSync() {
        return IdSync;
    }

    public void setIdSync(Integer idSync) {
        IdSync = idSync;
    }
}
