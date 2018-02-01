package com.movil.summmit.motorresapp.Models.Enity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

/**
 * Created by cgonzalez on 16/01/2018.
 */
@DatabaseTable
public class InformeTecnicoFallaCorrectivos {
    @DatabaseField(generatedId = true)
    private int IdFallaCorrectivos;
    @DatabaseField
    private int IdInformeTecnicoFalla;
    @DatabaseField
    private String Descripcion;
    @DatabaseField
    private int AudUsuarioRegistro;
    @DatabaseField
    private Date AudFechaRegistro;

    private Boolean Nuevo = false;

    public int getIdFallaCorrectivos() {
        return IdFallaCorrectivos;
    }

    public void setIdFallaCorrectivos(int idFallaCorrectivos) {
        IdFallaCorrectivos = idFallaCorrectivos;
    }

    public int getIdInformeTecnicoFalla() {
        return IdInformeTecnicoFalla;
    }

    public void setIdInformeTecnicoFalla(int idInformeTecnicoFalla) {
        IdInformeTecnicoFalla = idInformeTecnicoFalla;
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

    public Boolean getNuevo() {
        return Nuevo;
    }

    public void setNuevo(Boolean nuevo) {
        Nuevo = nuevo;
    }
}
