package com.movil.summmit.motorresapp.Models.Enity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

/**
 * Created by cgonzalez on 16/01/2018.
 */
@DatabaseTable
public class InformeTecnicoConclusiones {
    @DatabaseField(generatedId = true)
    private int IdInformeConclusiones;
    @DatabaseField
    private int IdInformeTecnico;
    @DatabaseField
    private String Descripcion;
    @DatabaseField
    private int AudUsuarioRegistro;
    @DatabaseField
    private Date AudFechaRegistro;

    public int getIdInformeConclusiones() {
        return IdInformeConclusiones;
    }

    public void setIdInformeConclusiones(int idInformeConclusiones) {
        IdInformeConclusiones = idInformeConclusiones;
    }

    public int getIdInformeTecnico() {
        return IdInformeTecnico;
    }

    public void setIdInformeTecnico(int idInformeTecnico) {
        IdInformeTecnico = idInformeTecnico;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getAudUsuarioRegistro() {
        return AudUsuarioRegistro;
    }

    public void setAudUsuarioRegistro(int audUsuarioRegistro) {
        AudUsuarioRegistro = audUsuarioRegistro;
    }

    public Date getAudFechaRegistro() {
        return AudFechaRegistro;
    }

    public void setAudFechaRegistro(Date audFechaRegistro) {
        AudFechaRegistro = audFechaRegistro;
    }
}
