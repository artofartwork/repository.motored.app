package com.movil.summmit.motorresapp.Models.Enity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

/**
 * Created by cgonzalez on 16/01/2018.
 */
@DatabaseTable
public class InformeTecnicoAntecedente {
    @DatabaseField(generatedId = true)
    private int IdInformeTecnicoAntecedente;
    @DatabaseField
    private int IdInformeTecnico;
    @DatabaseField
    private String Descripcion;
    @DatabaseField
    private int AudUsuarioRegistro;
    @DatabaseField
    private Date AudFechaRegistro;

    private Boolean EsNuevo;

    public int getIdInformeTecnicoAntecedente() {
        return IdInformeTecnicoAntecedente;
    }

    public void setIdInformeTecnicoAntecedente(int idInformeTecnicoAntecedente) {
        IdInformeTecnicoAntecedente = idInformeTecnicoAntecedente;
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

    public Boolean getEsNuevo() {
        return EsNuevo;
    }

    public void setEsNuevo(Boolean esNuevo) {
        EsNuevo = esNuevo;
    }
}
