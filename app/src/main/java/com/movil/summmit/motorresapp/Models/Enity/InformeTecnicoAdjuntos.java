package com.movil.summmit.motorresapp.Models.Enity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by cgonzalez on 16/01/2018.
 */
@DatabaseTable
public class InformeTecnicoAdjuntos {
    @DatabaseField(generatedId = true)
    private int IdAdjuntos;
    @DatabaseField
    private int IdInformeTecnico;
    @DatabaseField
    private String ArchivoNombreVin;
    @DatabaseField
    private String ArchivoNombreVinGenerado;
    @DatabaseField
    private String ArchivoRutaVin;
    @DatabaseField
    private int ArchivoTamanoVin;
    @DatabaseField
    private String ArchivoNombreKm;
    @DatabaseField
    private String ArchivoNombreKmGenerado;
    @DatabaseField
    private String ArchivoRutaKm;
    @DatabaseField
    private int ArchivoTamanoKm;
    @DatabaseField
    private String ArchivoNombre;
    @DatabaseField
    private String ArchivoRuta;
    @DatabaseField
    private int ArchivoTamano;

    public int getIdAdjuntos() {
        return IdAdjuntos;
    }

    public void setIdAdjuntos(int idAdjuntos) {
        IdAdjuntos = idAdjuntos;
    }

    public int getIdInformeTecnico() {
        return IdInformeTecnico;
    }

    public void setIdInformeTecnico(int idInformeTecnico) {
        IdInformeTecnico = idInformeTecnico;
    }

    public String getArchivoNombreVin() {
        return ArchivoNombreVin;
    }

    public void setArchivoNombreVin(String archivoNombreVin) {
        ArchivoNombreVin = archivoNombreVin;
    }

    public String getArchivoRutaVin() {
        return ArchivoRutaVin;
    }

    public void setArchivoRutaVin(String archivoRutaVin) {
        ArchivoRutaVin = archivoRutaVin;
    }

    public int getArchivoTamanoVin() {
        return ArchivoTamanoVin;
    }

    public void setArchivoTamanoVin(int archivoTamanoVin) {
        ArchivoTamanoVin = archivoTamanoVin;
    }

    public String getArchivoNombreKm() {
        return ArchivoNombreKm;
    }

    public void setArchivoNombreKm(String archivoNombreKm) {
        ArchivoNombreKm = archivoNombreKm;
    }

    public String getArchivoRutaKm() {
        return ArchivoRutaKm;
    }

    public void setArchivoRutaKm(String archivoRutaKm) {
        ArchivoRutaKm = archivoRutaKm;
    }

    public int getArchivoTamanoKm() {
        return ArchivoTamanoKm;
    }

    public void setArchivoTamanoKm(int archivoTamanoKm) {
        ArchivoTamanoKm = archivoTamanoKm;
    }

    public String getArchivoNombre() {
        return ArchivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        ArchivoNombre = archivoNombre;
    }

    public String getArchivoRuta() {
        return ArchivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        ArchivoRuta = archivoRuta;
    }

    public int getArchivoTamano() {
        return ArchivoTamano;
    }

    public void setArchivoTamano(int archivoTamano) {
        ArchivoTamano = archivoTamano;
    }

    public String getArchivoNombreVinGenerado() {
        return ArchivoNombreVinGenerado;
    }

    public void setArchivoNombreVinGenerado(String archivoNombreVinGenerado) {
        ArchivoNombreVinGenerado = archivoNombreVinGenerado;
    }

    public String getArchivoNombreKmGenerado() {
        return ArchivoNombreKmGenerado;
    }

    public void setArchivoNombreKmGenerado(String archivoNombreKmGenerado) {
        ArchivoNombreKmGenerado = archivoNombreKmGenerado;
    }
}
