package com.movil.summmit.motorresapp.Models.Enity.Maestro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by cgonzalez on 16/01/2018.
 */
@DatabaseTable
public class MaestraArgu {
    @DatabaseField(id = true)
    private int IdArgu ;
    @DatabaseField
    private String Nombre;
    @DatabaseField
    private String Abreviado;
    @DatabaseField
    private String Descripcion ;
    @DatabaseField
    private int IdMaestra;
    @DatabaseField
    private Boolean Activo;

    public int getIdArgu() {
        return IdArgu;
    }

    public void setIdArgu(int idArgu) {
        IdArgu = idArgu;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getAbreviado() {
        return Abreviado;
    }

    public void setAbreviado(String abreviado) {
        Abreviado = abreviado;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getIdMaestra() {
        return IdMaestra;
    }

    public void setIdMaestra(int idMaestra) {
        IdMaestra = idMaestra;
    }

    public Boolean getActivo() {
        return Activo;
    }

    public void setActivo(Boolean activo) {
        Activo = activo;
    }

    @Override
    public String toString() {
        return this.Nombre;            // What to display in the Spinner list.
    }
}
