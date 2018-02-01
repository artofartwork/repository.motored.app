package com.movil.summmit.motorresapp.Models.Enity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.util.List;

/**
 * Created by cgonzalez on 12/01/2018.
 */
@DatabaseTable
public class InformeTecnicoFalla {
    @DatabaseField(generatedId = true)
    private int IdInformeTecnicoFalla;
    @DatabaseField
    private int IdInformeTecnico;
    @DatabaseField
    private int IdArguSistema;

    private String NombreSistema;

    @DatabaseField
    private int IdArguModoFalla;

    private String NombreModoFalla;

    @DatabaseField
    private String NroCaso;
    @DatabaseField
    private Boolean Scanner;
    @DatabaseField
    private String ArchivoScannerNombre;
    @DatabaseField
    private String ArchivoScannerRuta;
    @DatabaseField
    private int ArchivoScannerTamano;
    @DatabaseField
    private String ArchivoScannerNombreGenerado;
    @DatabaseField
    private Boolean Aceite;
    @DatabaseField
    private  String ArchivoAceiteNombre;
    @DatabaseField
    private  String ArchivoAceiteNombreGenerado;
    @DatabaseField
    private String ArchivoAceiteRuta;
    @DatabaseField
    private  int ArchivoAceiteTamano;
    @DatabaseField
    private Boolean Combustible;
    @DatabaseField
    private String ArchivoCombustibleNombre;
    @DatabaseField
    private String ArchivoCombustibleNombreGenerado;
    @DatabaseField
    private String ArchivoCombustiblerRuta;
    @DatabaseField
    private int ArchivoCombustibleTamano;
    @DatabaseField
    private Boolean Activo;
    @DatabaseField
    private int AudUsuarioRegistro;
    @DatabaseField
    private Date AudFechaRegistro;

    private String NombresTecnicos;



    public int getIdInformeTecnicoFalla() {
        return IdInformeTecnicoFalla;
    }

    public void setIdInformeTecnicoFalla(int idInformeTecnicoFalla) {
        IdInformeTecnicoFalla = idInformeTecnicoFalla;
    }

    public int getIdInformeTecnico() {
        return IdInformeTecnico;
    }

    public void setIdInformeTecnico(int idInformeTecnico) {
        IdInformeTecnico = idInformeTecnico;
    }

    public int getIdArguSistema() {
        return IdArguSistema;
    }

    public void setIdArguSistema(int idArguSistema) {
        IdArguSistema = idArguSistema;
    }

    public int getIdArguModoFalla() {
        return IdArguModoFalla;
    }

    public void setIdArguModoFalla(int idArguModoFalla) {
        IdArguModoFalla = idArguModoFalla;
    }

    public String getNroCaso() {
        return NroCaso;
    }

    public void setNroCaso(String nroCaso) {
        NroCaso = nroCaso;
    }

    public Boolean getScanner() {
        return Scanner;
    }

    public void setScanner(Boolean scanner) {
        Scanner = scanner;
    }

    public String getArchivoScannerNombre() {
        return ArchivoScannerNombre;
    }

    public void setArchivoScannerNombre(String archivoScannerNombre) {
        ArchivoScannerNombre = archivoScannerNombre;
    }

    public String getArchivoScannerRuta() {
        return ArchivoScannerRuta;
    }

    public void setArchivoScannerRuta(String archivoScannerRuta) {
        ArchivoScannerRuta = archivoScannerRuta;
    }

    public int getArchivoScannerTamano() {
        return ArchivoScannerTamano;
    }

    public void setArchivoScannerTamano(int archivoScannerTamano) {
        ArchivoScannerTamano = archivoScannerTamano;
    }

    public Boolean getAceite() {
        return Aceite;
    }

    public void setAceite(Boolean aceite) {
        Aceite = aceite;
    }

    public Boolean getCombustible() {
        return Combustible;
    }

    public void setCombustible(Boolean combustible) {
        Combustible = combustible;
    }

    public String getArchivoAceiteNombre() {
        return ArchivoAceiteNombre;
    }

    public void setArchivoAceiteNombre(String archivoAceiteNombre) {
        ArchivoAceiteNombre = archivoAceiteNombre;
    }

    public String getArchivoAceiteRuta() {
        return ArchivoAceiteRuta;
    }

    public void setArchivoAceiteRuta(String archivoAceiteRuta) {
        ArchivoAceiteRuta = archivoAceiteRuta;
    }

    public int getArchivoAceiteTamano() {
        return ArchivoAceiteTamano;
    }

    public void setArchivoAceiteTamano(int archivoAceiteTamano) {
        ArchivoAceiteTamano = archivoAceiteTamano;
    }

    public String getArchivoCombustibleNombre() {
        return ArchivoCombustibleNombre;
    }

    public void setArchivoCombustibleNombre(String archivoCombustibleNombre) {
        ArchivoCombustibleNombre = archivoCombustibleNombre;
    }

    public String getArchivoCombustiblerRuta() {
        return ArchivoCombustiblerRuta;
    }

    public void setArchivoCombustiblerRuta(String archivoCombustiblerRuta) {
        ArchivoCombustiblerRuta = archivoCombustiblerRuta;
    }

    public int getArchivoCombustibleTamano() {
        return ArchivoCombustibleTamano;
    }

    public void setArchivoCombustibleTamano(int archivoCombustibleTamano) {
        ArchivoCombustibleTamano = archivoCombustibleTamano;
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

    public String getNombreSistema() {
        return NombreSistema;
    }

    public void setNombreSistema(String nombreSistema) {
        NombreSistema = nombreSistema;
    }

    public String getNombreModoFalla() {
        return NombreModoFalla;
    }

    public void setNombreModoFalla(String nombreModoFalla) {
        NombreModoFalla = nombreModoFalla;
    }

    public String getArchivoScannerNombreGenerado() {
        return ArchivoScannerNombreGenerado;
    }

    public void setArchivoScannerNombreGenerado(String archivoScannerNombreGenerado) {
        ArchivoScannerNombreGenerado = archivoScannerNombreGenerado;
    }

    public String getArchivoAceiteNombreGenerado() {
        return ArchivoAceiteNombreGenerado;
    }

    public void setArchivoAceiteNombreGenerado(String archivoAceiteNombreGenerado) {
        ArchivoAceiteNombreGenerado = archivoAceiteNombreGenerado;
    }

    public String getArchivoCombustibleNombreGenerado() {
        return ArchivoCombustibleNombreGenerado;
    }

    public void setArchivoCombustibleNombreGenerado(String archivoCombustibleNombreGenerado) {
        ArchivoCombustibleNombreGenerado = archivoCombustibleNombreGenerado;
    }

    public String getNombresTecnicos() {
        return NombresTecnicos;
    }

    public void setNombresTecnicos(String nombresTecnicos) {
        NombresTecnicos = nombresTecnicos;
    }


}
