package com.movil.summmit.motorresapp.Models.Enity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Modelo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by cgonzalez on 11/01/2018.
 */

@DatabaseTable
public class InformeTecnico implements Serializable {


        //private byte[] archivo;
    @DatabaseField(generatedId = true)
        private int IdInformeTecnico;
    @DatabaseField
        private int IdEmpresa;
        private String NombreEmpresa;
    @DatabaseField
        private int IdArguSucursal;
        private String NombreSucursal;


    @DatabaseField
        private int IdArguArea;
        private String NombreArea;
    @DatabaseField
        private String NroOT;
    @DatabaseField
        private  int IdMarca;
        private String NombreMarca;
    @DatabaseField
        private int IdModelo;
        private String NombreModelo;
    @DatabaseField
        private  int IdCliente;
        private String NombreCliente;
    @DatabaseField
        private String VIN;
        private String NombreVin;

    @DatabaseField
        private Double Kilometraje;
    @DatabaseField
        private Double Horometro;
    @DatabaseField
        private int IdArguComponente;
        private String NombreComponente;
    @DatabaseField
        private String Serie;
    @DatabaseField
        private int IdArguAplicacion;
        private String NombreAplicacion;
    @DatabaseField
        private Date FechaInicio;
    @DatabaseField
    private Date FechaFalla;
    @DatabaseField
        private String Observacion;
    @DatabaseField
        private String Estado;
    @DatabaseField
        private Boolean Activo;
    @DatabaseField
        private int AudUsuarioRegistro;
    @DatabaseField
        private Date AudFechaRegistro;
    @DatabaseField
        private int AudUsuarioModifica;
    @DatabaseField
        private Date AudFechaModifica;

    private List<InformeTecnicoAntecedente> ListaAntecedentes;
    private List<InformeTecnicoFalla> ListaFallas;
    private List<InformeTecnicoConclusiones> ListaConclusiones;
    private List<InformeTecnicoRecomendaciones> ListaRecomendaciones;
    private InformeTecnicoAdjuntos informeTecnicoAdjuntos;

    public InformeTecnico(){
    }

    public int getIdInformeTecnico() {
        return IdInformeTecnico;
    }

    public void setIdInformeTecnico(int idInformeTecnico) {
        IdInformeTecnico = idInformeTecnico;
    }

    public int getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        IdEmpresa = idEmpresa;
    }

    public int getIdArguSucursal() {
        return IdArguSucursal;
    }

    public void setIdArguSucursal(int idArguSucursal) {
        IdArguSucursal = idArguSucursal;
    }

    public int getIdArguArea() {
        return IdArguArea;
    }

    public void setIdArguArea(int idArguArea) {
        IdArguArea = idArguArea;
    }

    public String getNroOT() {
        return NroOT;
    }

    public void setNroOT(String nroOT) {
        NroOT = nroOT;
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

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public Double getKilometraje() {
        return Kilometraje;
    }

    public void setKilometraje(Double kilometraje) {
        Kilometraje = kilometraje;
    }

    public Double getHorometro() {
        return Horometro;
    }

    public void setHorometro(Double horometro) {
        Horometro = horometro;
    }

    public int getIdArguComponente() {
        return IdArguComponente;
    }

    public void setIdArguComponente(int idArguComponente) {
        IdArguComponente = idArguComponente;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String serie) {
        Serie = serie;
    }

    public int getIdArguAplicacion() {
        return IdArguAplicacion;
    }

    public void setIdArguAplicacion(int idArguAplicacion) {
        IdArguAplicacion = idArguAplicacion;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        FechaInicio = fechaInicio;
    }



    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public Boolean getActivo() {
        return Activo;
    }

    public void setActivo(Boolean activo) {
        Activo = activo;
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

    public int getAudUsuarioModifica() {
        return AudUsuarioModifica;
    }

    public void setAudUsuarioModifica(int audUsuarioModifica) {
        AudUsuarioModifica = audUsuarioModifica;
    }

    public Date getAudFechaModifica() {
        return AudFechaModifica;
    }

    public void setAudFechaModifica(Date audFechaModifica) {
        AudFechaModifica = audFechaModifica;
    }

    public String getNombreEmpresa() {
        return NombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        NombreEmpresa = nombreEmpresa;
    }

    public String getNombreSucursal() {
        return NombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        NombreSucursal = nombreSucursal;
    }

    public String getNombreArea() {
        return NombreArea;
    }

    public void setNombreArea(String nombreArea) {
        NombreArea = nombreArea;
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

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public String getNombreAplicacion() {
        return NombreAplicacion;
    }

    public void setNombreAplicacion(String nombreAplicacion) {
        NombreAplicacion = nombreAplicacion;
    }

    public Date getFechaFalla() {
        return FechaFalla;
    }

    public void setFechaFalla(Date fechaFalla) {
        FechaFalla = fechaFalla;
    }

    public String getNombreVin() {
        return NombreVin;
    }

    public void setNombreVin(String nombreVin) {
        NombreVin = nombreVin;
    }

    public String getNombreComponente() {
        return NombreComponente;
    }

    public void setNombreComponente(String nombreComponente) {
        NombreComponente = nombreComponente;
    }




    public List<InformeTecnicoAntecedente> getListaAntecedentes() {
        return ListaAntecedentes;
    }

    public void setListaAntecedentes(List<InformeTecnicoAntecedente> listaAntecedentes) {
        ListaAntecedentes = listaAntecedentes;
    }

    public List<InformeTecnicoFalla> getListaFallas() {
        return ListaFallas;
    }

    public void setListaFallas(List<InformeTecnicoFalla> listaFallas) {
        ListaFallas = listaFallas;
    }

    public List<InformeTecnicoConclusiones> getListaConclusiones() {
        return ListaConclusiones;
    }

    public void setListaConclusiones(List<InformeTecnicoConclusiones> listaConclusiones) {
        ListaConclusiones = listaConclusiones;
    }

    public List<InformeTecnicoRecomendaciones> getListaRecomendaciones() {
        return ListaRecomendaciones;
    }

    public void setListaRecomendaciones(List<InformeTecnicoRecomendaciones> listaRecomendaciones) {
        ListaRecomendaciones = listaRecomendaciones;
    }

    public InformeTecnicoAdjuntos getInformeTecnicoAdjuntos() {
        return informeTecnicoAdjuntos;
    }

    public void setInformeTecnicoAdjuntos(InformeTecnicoAdjuntos informeTecnicoAdjuntos) {
        this.informeTecnicoAdjuntos = informeTecnicoAdjuntos;
    }
}
