package com.movil.summmit.motorresapp.Models.Enity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by cgonzalez on 16/01/2018.
 */
@DatabaseTable
public class InformeTecnicoAdjuntosDetalle {
    @DatabaseField(generatedId = true)
    private int IdAdjuntosDetalle;
    @DatabaseField
    private int IdAdjuntos;
    @DatabaseField
    private int IdInformeTecnico;
    @DatabaseField
    private String ArchivoNombre;
    @DatabaseField
    private String ArchivoNombreGenerado;
    @DatabaseField
    private String ArchivoRuta;
    @DatabaseField
    private int ArchivoTamano;

    private String SrcPath;
    private String DestPath;

    public int getIdAdjuntosDetalle() {
        return IdAdjuntosDetalle;
    }

    public void setIdAdjuntosDetalle(int idAdjuntosDetalle) {
        IdAdjuntosDetalle = idAdjuntosDetalle;
    }

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

    public String getArchivoNombreGenerado() {
        return ArchivoNombreGenerado;
    }

    public void setArchivoNombreGenerado(String archivoNombreGenerado) {
        ArchivoNombreGenerado = archivoNombreGenerado;
    }

    public String getSrcPath() {
        return SrcPath;
    }

    public void setSrcPath(String srcPath) {
        SrcPath = srcPath;
    }

    public String getDestPath() {
        return DestPath;
    }

    public void setDestPath(String destPath) {
        DestPath = destPath;
    }
}
