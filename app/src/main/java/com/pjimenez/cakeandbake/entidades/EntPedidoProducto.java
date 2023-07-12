package com.pjimenez.cakeandbake.entidades;

public class EntPedidoProducto {
    private int pedidoId;
    private int prodId;
    private int cantidad;

    public EntPedidoProducto(){

    }

    public EntPedidoProducto(int pedidoId, int prodId, int cantidad) {
        this.pedidoId = pedidoId;
        this.prodId = prodId;
        this.cantidad = cantidad;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
