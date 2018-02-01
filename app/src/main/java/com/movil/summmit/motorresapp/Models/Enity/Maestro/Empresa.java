package com.movil.summmit.motorresapp.Models.Enity.Maestro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by cgonzalez on 16/01/2018.
 */
@DatabaseTable
public class Empresa {
    @DatabaseField(id = true)
    private int IdEmpresa;
    @DatabaseField
    private String Nombre;
    @DatabaseField
    private String RazonSocial;
    @DatabaseField
    private String RUC;



    public int getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        IdEmpresa = idEmpresa;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        RazonSocial = razonSocial;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    @Override
    public String toString() {
        return this.Nombre;            // What to display in the Spinner list.
    }
}
