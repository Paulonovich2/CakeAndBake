package com.pjimenez.cakeandbake.entidades;

public class EntPedidoConfirmar {

    private int prod_id;
    private String prod_tittle;
    private String prod_description;
    private double prod_price;
    private int pedido_producto_cantidad;

    public EntPedidoConfirmar(int prod_id, String prod_tittle, String prod_description, double prod_price, int pedido_producto_cantidad) {
        this.prod_id = prod_id;
        this.prod_tittle = prod_tittle;
        this.prod_description = prod_description;
        this.prod_price = prod_price;
        this.pedido_producto_cantidad = pedido_producto_cantidad;
    }

    public EntPedidoConfirmar() {

    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_tittle() {
        return prod_tittle;
    }

    public void setProd_tittle(String prod_tittle) {
        this.prod_tittle = prod_tittle;
    }

    public String getProd_description() {
        return prod_description;
    }

    public void setProd_description(String prod_description) {
        this.prod_description = prod_description;
    }

    public double getProd_price() {
        return prod_price;
    }

    public void setProd_price(double prod_price) {
        this.prod_price = prod_price;
    }

    public int getPedido_producto_cantidad() {
        return pedido_producto_cantidad;
    }

    public void setPedido_producto_cantidad(int pedido_producto_cantidad) {
        this.pedido_producto_cantidad = pedido_producto_cantidad;
    }
}
