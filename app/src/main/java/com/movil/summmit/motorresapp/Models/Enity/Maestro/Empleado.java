package com.movil.summmit.motorresapp.Models.Enity.Maestro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by cgonzalez on 16/01/2018.
 */
@DatabaseTable
public class Empleado {
    @DatabaseField(id = true)
    private int IdEmpleado;
    @DatabaseField
    private String Codigo;
    @DatabaseField
    private String Paterno;
    @DatabaseField
    private String Materno;
    @DatabaseField
    private String Nombre;
    @DatabaseField
    private int IdEmpresa;

    private Boolean Selected = false;

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        IdEmpleado = idEmpleado;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getPaterno() {
        return Paterno;
    }

    public void setPaterno(String paterno) {
        Paterno = paterno;
    }

    public String getMaterno() {
        return Materno;
    }

    public void setMaterno(String materno) {
        Materno = materno;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        IdEmpresa = idEmpresa;
    }

    public Boolean getSelected() {
        return Selected;
    }

    public void setSelected(Boolean selected) {
        Selected = selected;
    }
}
