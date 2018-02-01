package com.movil.summmit.motorresapp.Models.Enity.Maestro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by cgonzalez on 16/01/2018.
 */
@DatabaseTable
public class Cliente {
    @DatabaseField(id = true)
    private int IdCliente;
    @DatabaseField
    private String Nombres;
    @DatabaseField
    private String RazonSocial;
    @DatabaseField
    private String Ruc;

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        RazonSocial = razonSocial;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
    }

    @Override
    public String toString() {
        return this.Nombres;            // What to display in the Spinner list.
    }
}
