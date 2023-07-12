package com.pjimenez.cakeandbake.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.pjimenez.cakeandbake.entidades.EntPedido;

public class DAOPedido {
        private SQLiteDatabase db;

        public DAOPedido(SQLiteDatabase db) {
            this.db = db;
        }

        public long insertPedido(EntPedido pedido) {
            ContentValues values = new ContentValues();
            values.put("user_id", pedido.getUserId());
            values.put("local_id", pedido.getLocalId());
            values.put("dir_id", pedido.getDirId());
            values.put("realizado", pedido.isRealizado() ? 1 : 0);

            return db.insert("Pedido", null, values);
        }

        public int updatePedidoRealizado(int pedidoId, boolean realizado) {
            ContentValues values = new ContentValues();
            values.put("realizado", realizado ? 1 : 0);

            String whereClause = "pedido_id" + " = ?";
            String[] whereArgs = {String.valueOf(pedidoId)};

            return db.update("Pedido", values, whereClause, whereArgs);
        }

        // Otros métodos para obtener pedidos, eliminar pedidos, etc.
    }