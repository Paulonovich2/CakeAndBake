package com.pjimenez.cakeandbake.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pjimenez.cakeandbake.entidades.EntPedido;
import com.pjimenez.cakeandbake.entidades.EntProducto;
import com.pjimenez.cakeandbake.util.BaseDatos;

import java.util.ArrayList;
import java.util.List;

public class DAOPedido {
    private BaseDatos baseDatos;
    private SQLiteDatabase db;
    private Context context;

    public DAOPedido(Context context) {
        baseDatos = new BaseDatos(context);
        this.context = context;
    }

    public void abrirBD() {
        db = baseDatos.getWritableDatabase();
    }

    public void cerrarBD() {
        db.close();
    }

    public long insertPedido(EntPedido pedido) {
        abrirBD();

        ContentValues values = new ContentValues();
        values.put("user_id", pedido.getUserId());
        values.put("local_id", pedido.getLocalId());
        values.put("dir_id", pedido.getDirId());
        values.put("realizado", pedido.isRealizado() ? 1 : 0);
        long resultado = db.insert("Pedido", null, values);

        cerrarBD();
        return resultado;
    }

    public int updatePedidoRealizado(int pedidoId, boolean realizado) {
        abrirBD();

        ContentValues values = new ContentValues();
        values.put("realizado", realizado ? 1 : 0);
        String whereClause = "pedido_id = ?";
        String[] whereArgs = {String.valueOf(pedidoId)};
        int resultado = db.update("Pedido", values, whereClause, whereArgs);

        cerrarBD();
        return resultado;
    }
}