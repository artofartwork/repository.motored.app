package com.movil.summmit.motorresapp.Models.Enity.Maestro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by cgonzalez on 18/01/2018.
 */
@DatabaseTable
public class Marca {
    @DatabaseField(id = true)
    private int IdMarca;
    @DatabaseField
    private String Descripcion;
    @DatabaseField
    private String FabricanteUrl;
    @DatabaseField
    private Boolean Activo;

    public int getIdMarca() {
        return IdMarca;
    }

    public void setIdMarca(int idMarca) {
        IdMarca = idMarca;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFabricanteUrl() {
        return FabricanteUrl;
    }

    public void setFabricanteUrl(String fabricanteUrl) {
        FabricanteUrl = fabricanteUrl;
    }

    public Boolean getActivo() {
        return Activo;
    }

    public void setActivo(Boolean activo) {
        Activo = activo;
    }

    @Override
    public String toString() {
        return this.Descripcion;            // What to display in the Spinner list.
    }
}
