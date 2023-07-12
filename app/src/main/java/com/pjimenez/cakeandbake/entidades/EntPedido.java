package com.pjimenez.cakeandbake.entidades;

public class EntPedido {
    private int pedidoId;
    private int userId;
    private int localId;
    private int dirId;
    private boolean realizado;

    public EntPedido(){

    }

    public EntPedido(int pedidoId, int userId, int localId, int dirId, boolean realizado) {
        this.pedidoId = pedidoId;
        this.userId = userId;
        this.localId = localId;
        this.dirId = dirId;
        this.realizado = realizado;
    }

    public EntPedido(int userId, int localId, int dirId, boolean realizado) {
        this.userId = userId;
        this.localId = localId;
        this.dirId = dirId;
        this.realizado = realizado;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocalId() {
        return localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    public int getDirId() {
        return dirId;
    }

    public void setDirId(int dirId) {
        this.dirId = dirId;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }
}
