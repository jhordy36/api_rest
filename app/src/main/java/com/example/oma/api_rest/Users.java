package com.example.oma.api_rest;

public class Users {
    private String id;
    private String apellido;
    private String nombre;
    private String sueldo;

    public Users() {
    }

    public Users(String id, String apellido, String nombre, String sueldo) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }
}
