package com.movil.summmit.motorresapp.Models.Enity.Maestro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by cgonzalez on 16/01/2018.
 */
@DatabaseTable
public class Modelo {
    @DatabaseField(id = true)
    private int IdModelo;
    @DatabaseField
    private int IdMarca;
    @DatabaseField
    private String Descripcion;

    public int getIdModelo() {
        return IdModelo;
    }

    public void setIdModelo(int idModelo) {
        IdModelo = idModelo;
    }

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
    @Override
    public String toString() {
        return this.Descripcion;            // What to display in the Spinner list.
    }
}
