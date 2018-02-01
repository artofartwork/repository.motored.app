package com.movil.summmit.motorresapp.Models.Enity.Maestro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by cgonzalez on 17/01/2018.
 */
@DatabaseTable
public class Maestra {
    @DatabaseField(id = true)
    private int IdMaestra;
    @DatabaseField
    private String Nombre;
    @DatabaseField
    private String Descripcion;
    @DatabaseField
    private Boolean EsMantenible;
    @DatabaseField
    private int Orden;

    public int getIdMaestra() {
        return IdMaestra;
    }

    public void setIdMaestra(int idMaestra) {
        IdMaestra = idMaestra;
    }


    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Boolean getEsMantenible() {
        return EsMantenible;
    }

    public void setEsMantenible(Boolean esMantenible) {
        EsMantenible = esMantenible;
    }

    public int getOrden() {
        return Orden;
    }

    public void setOrden(int orden) {
        Orden = orden;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
