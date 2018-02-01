package com.movil.summmit.motorresapp.Models.Enity.Maestro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

/**
 * Created by cgonzalez on 16/01/2018.
 */
@DatabaseTable
public class Vin {

    @DatabaseField
    private int IdChasis;
    @DatabaseField
    private int IdMarca;
    @DatabaseField
    private int IdCliente;
    @DatabaseField
    private String Vin;
    @DatabaseField
    private String FechaEntrega;


    public int getIdChasis() {
        return IdChasis;
    }

    public void setIdChasis(int idChasis) {
        IdChasis = idChasis;
    }

    public int getIdMarca() {
        return IdMarca;
    }

    public void setIdMarca(int idMarca) {
        IdMarca = idMarca;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public String getVin() {
        return Vin;
    }

    public void setVin(String vin) {
        Vin = vin;
    }

    public String getFechaEntrega() {
        return FechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        FechaEntrega = fechaEntrega;
    }

    @Override
    public String toString() {
        return this.Vin;            // What to display in the Spinner list.
    }
}
