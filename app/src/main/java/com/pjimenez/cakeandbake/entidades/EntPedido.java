package com.pjimenez.cakeandbake.entidades;

public class EntPedido {

    private int id;
    private int user_id;
    private int prod_id;
    private String prod_description;
    private int local_id;
    private int dir_id;
    private int pedido_cantidad;

    public EntPedido(int id, int user_id, int prod_id, String prod_description, int local_id, int dir_id, int pedido_cantidad) {
        this.id = id;
        this.user_id = user_id;
        this.prod_id = prod_id;
        this.prod_description = prod_description;
        this.local_id = local_id;
        this.dir_id = dir_id;
        this.pedido_cantidad = pedido_cantidad;
    }

    public EntPedido(int user_id, int prod_id, String prod_description, int local_id, int dir_id, int pedido_cantidad) {
        this.user_id = user_id;
        this.prod_id = prod_id;
        this.prod_description = prod_description;
        this.local_id = local_id;
        this.dir_id = dir_id;
        this.pedido_cantidad = pedido_cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_description() {
        return prod_description;
    }

    public void setProd_description(String prod_description) {
        this.prod_description = prod_description;
    }

    public int getLocal_id() {
        return local_id;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }

    public int getDir_id() {
        return dir_id;
    }

    public void setDir_id(int dir_id) {
        this.dir_id = dir_id;
    }

    public int getPedido_cantidad() {
        return pedido_cantidad;
    }

    public void setPedido_cantidad(int pedido_cantidad) {
        this.pedido_cantidad = pedido_cantidad;
    }
}
