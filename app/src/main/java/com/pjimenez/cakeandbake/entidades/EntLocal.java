package com.pjimenez.cakeandbake.entidades;

public class EntLocal {
    private int id;
    private String direccion;
    private double latitud;
    private double longitud;
    private String telefono;

    public EntLocal(int id, String direccion, double latitud, double longitud, String telefono) {
        this.id = id;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.telefono = telefono;
    }

    public EntLocal() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    public String getTelefono(){return telefono;}

    public void setTelefono(String telefono){this.telefono=telefono;}
}