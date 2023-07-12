package com.pjimenez.cakeandbake.entidades;

public class EntProducto {
    private int id;
    private String titulo;
    private String descripcion;
    private double precio;
    private int tipoId;
    private String imagen;

    public EntProducto(int id,String titulo, String descripcion, double precio, int tipoId, String imagen) {
        this.id = id;
        this.titulo=titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipoId = tipoId;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
