package com.pjimenez.cakeandbake.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pjimenez.cakeandbake.entidades.EntPedidoConfirmar;
import com.pjimenez.cakeandbake.entidades.EntPedidoProducto;

import java.util.ArrayList;
import java.util.List;

public class DAOPedidoProducto {
    private SQLiteDatabase db;

    public DAOPedidoProducto(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertPedidoProducto(EntPedidoProducto pedidoProducto) {
        ContentValues values = new ContentValues();
        values.put("pedido_id", pedidoProducto.getPedidoId());
        values.put("prod_id", pedidoProducto.getProdId());
        values.put("pedido_producto_cantidad", pedidoProducto.getCantidad());

        return db.insert("PedidoProducto", null, values);
    }

    public List<EntPedidoConfirmar> getPedidoProductos(long pedidoId) {
        List<EntPedidoConfirmar> pedidoProductoList = new ArrayList<>();

        // Consulta SQL con JOIN entre las tablas producto y pedido_producto
        String query = "SELECT Producto.prod_id, Producto.prod_tittle, Producto.prod_description, " +
                "Producto.prod_price, PedidoProducto.pedido_producto_cantidad " +
                "FROM Producto " +
                "INNER JOIN PedidoProducto ON Producto.prod_id = PedidoProducto.prod_id " +
                "WHERE PedidoProducto.pedido_id = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(pedidoId)});

        if (cursor.moveToFirst()) {
            do {
                // Obtener los datos de cada columna del cursor
                long prodId = cursor.getLong(cursor.getColumnIndex("prod_id"));
                String prodTittle = cursor.getString(cursor.getColumnIndex("prod_tittle"));
                String prodDescription = cursor.getString(cursor.getColumnIndex("prod_description"));
                double prodPrice = cursor.getDouble(cursor.getColumnIndex("prod_price"));
                int cantidad = cursor.getInt(cursor.getColumnIndex("pedido_producto_cantidad"));

                // Crear una instancia de EntPedidoProducto con los datos obtenidos
                EntPedidoConfirmar pedidoProducto = new EntPedidoConfirmar();
                pedidoProducto.setProd_id((int) prodId);
                pedidoProducto.setProd_tittle(prodTittle);
                pedidoProducto.setProd_description(prodDescription);
                pedidoProducto.setProd_price(prodPrice);
                pedidoProducto.setPedido_producto_cantidad(cantidad);

                // Agregar el EntPedidoProducto a la lista
                pedidoProductoList.add(pedidoProducto);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return pedidoProductoList;
    }


    // Otros métodos para obtener productos en un pedido, actualizar la cantidad, etc.
}
