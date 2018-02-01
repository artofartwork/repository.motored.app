package com.movil.summmit.motorresapp.Request;

/**
 * Created by cgonzalez on 30/01/2018.
 */

public class ReturnValue {

    private String Argument;
    private String Code;
    private String Message;
    private Boolean Succes;

    public String getArgument() {
        return Argument;
    }

    public void setArgument(String argument) {
        Argument = argument;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Boolean getSucces() {
        return Succes;
    }

    public void setSucces(Boolean succes) {
        Succes = succes;
    }
}
