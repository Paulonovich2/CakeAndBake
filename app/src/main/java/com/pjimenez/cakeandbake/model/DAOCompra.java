package com.pjimenez.cakeandbake.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pjimenez.cakeandbake.Pedido;
import com.pjimenez.cakeandbake.entidades.EntPedido;
import com.pjimenez.cakeandbake.util.BaseDatos;

import java.util.ArrayList;
import java.util.List;

public class DAOCompra {

    BaseDatos baseDatos;
    SQLiteDatabase db;
    Context context;

    public DAOCompra(Context context) {
        this.context = context;
        baseDatos = new BaseDatos(context);
    }

    public void abrirDB() {
        db = baseDatos.getWritableDatabase();
    }

    public List<EntPedido> cargarPedidos(){
        List<EntPedido> listaPedidos = new ArrayList<>();
        try {
            Cursor c = db.rawQuery("SELECT * FROM pedido", null);
            while (c.moveToNext()){
                listaPedidos.add(new EntPedido(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        c.getString(3),
                        c.getInt(4),
                        c.getInt(5),
                        c.getInt(6)
                ));
            }
        } catch (Exception e) {
            Log.d("==>", e.toString());
        }
        return listaPedidos;
    }

}
