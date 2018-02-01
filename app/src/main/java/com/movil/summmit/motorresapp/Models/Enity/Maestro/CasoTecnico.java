package com.movil.summmit.motorresapp.Models.Enity.Maestro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

/**
 * Created by cgonzalez on 12/01/2018.
 */
@DatabaseTable
public class CasoTecnico {
    @DatabaseField(id = true)
    private int IdCasoTecnico;
    @DatabaseField
    private int IdEmpresa;
    @DatabaseField
    private int IdSucursal;
    @DatabaseField
    private int IdArea;
    @DatabaseField
    private int IdLocalizacion;
    @DatabaseField
    private int IdTipoCasoTecnico;
    @DatabaseField
    private int IdMarca;
    private String NombreMarca;
    @DatabaseField
    private int IdModelo;
    private String NombreModelo;
    @DatabaseField
    private int Kilometros;
    @DatabaseField
    private int Horometro;
    @DatabaseField
    private String Codigo;

    @DatabaseField
    private String FechaFalla;

    @DatabaseField
    private String Criticidad;

    @DatabaseField
    private int IdComponente;

    private Boolean Selected = false;

    public CasoTecnico(){}

    public int getIdCasoTecnico() {
        return IdCasoTecnico;
    }

    public void setIdCasoTecnico(int idCasoTecnico) {
        IdCasoTecnico = idCasoTecnico;
    }

    public int getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        IdEmpresa = idEmpresa;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        IdSucursal = idSucursal;
    }

    public int getIdArea() {
        return IdArea;
    }

    public void setIdArea(int idArea) {
        IdArea = idArea;
    }

    public int getIdLocalizacion() {
        return IdLocalizacion;
    }

    public void setIdLocalizacion(int idLocalizacion) {
        IdLocalizacion = idLocalizacion;
    }

    public int getIdTipoCasoTecnico() {
        return IdTipoCasoTecnico;
    }

    public void setIdTipoCasoTecnico(int idTipoCasoTecnico) {
        IdTipoCasoTecnico = idTipoCasoTecnico;
    }

    public int getIdMarca() {
        return IdMarca;
    }

    public void setIdMarca(int idMarca) {
        IdMarca = idMarca;
    }

    public int getIdModelo() {
        return IdModelo;
    }

    public void setIdModelo(int idModelo) {
        IdModelo = idModelo;
    }

    public int getKilometros() {
        return Kilometros;
    }

    public void setKilometros(int kilometros) {
        Kilometros = kilometros;
    }

    public int getHorometro() {
        return Horometro;
    }

    public void setHorometro(int horometro) {
        Horometro = horometro;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public Boolean getSelected() {
        return Selected;
    }

    public void setSelected(Boolean selected) {
        Selected = selected;
    }

    public String getNombreMarca() {
        return NombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        NombreMarca = nombreMarca;
    }

    public String getNombreModelo() {
        return NombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        NombreModelo = nombreModelo;
    }




    public String getCriticidad() {
        return Criticidad;
    }

    public void setCriticidad(String criticidad) {
        Criticidad = criticidad;
    }

    public String getFechaFalla() {
        return FechaFalla;
    }

    public void setFechaFalla(String fechaFalla) {
        FechaFalla = fechaFalla;
    }

    public int getIdComponente() {
        return IdComponente;
    }

    public void setIdComponente(int idComponente) {
        IdComponente = idComponente;
    }
}
