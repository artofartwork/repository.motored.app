package com.movil.summmit.motorresapp.Models.Enity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

/**
 * Created by cgonzalez on 26/01/2018.
 */

@DatabaseTable
public class InformeTecnicoFallaxEmpleado {

    @DatabaseField
    private int IdInformeTecnicoFalla;
    @DatabaseField
    private int IdEmpleado;
    @DatabaseField
    private int AudUsuarioRegistro;
    @DatabaseField
    private Date AudFechaRegistro;

    public int getIdInformeTecnicoFalla() {
        return IdInformeTecnicoFalla;
    }

    public void setIdInformeTecnicoFalla(int idInformeTecnicoFalla) {
        IdInformeTecnicoFalla = idInformeTecnicoFalla;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        IdEmpleado = idEmpleado;
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
