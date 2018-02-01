package com.movil.summmit.motorresapp.Models.Enity.Maestro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by cgonzalez on 17/01/2018.
 */
@DatabaseTable
public class Usuario {
    @DatabaseField(id = true)
    private  int IdUsuario;
    @DatabaseField
    private String UserName;
    @DatabaseField
    private String PasswordHash;
    @DatabaseField
    private String Nombre;
    @DatabaseField
    private String Correo;
    @DatabaseField
    private String IdPersona;
    @DatabaseField
    private Boolean Activo;

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(String idPersona) {
        IdPersona = idPersona;
    }

    public Boolean getActivo() {
        return Activo;
    }

    public void setActivo(Boolean activo) {
        Activo = activo;
    }
}
