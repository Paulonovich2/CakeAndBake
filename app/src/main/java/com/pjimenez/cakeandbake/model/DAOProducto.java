package com.pjimenez.cakeandbake.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pjimenez.cakeandbake.entidades.EntProducto;
import com.pjimenez.cakeandbake.util.BaseDatos;

import java.util.ArrayList;
import java.util.List;

public class DAOProducto {
    private BaseDatos baseDatos;
    private SQLiteDatabase db;
    private Context context;

    public DAOProducto(Context context) {
        baseDatos = new BaseDatos(context);
        this.context = context;
    }

    public void abrirBD() {
        db = baseDatos.getWritableDatabase();
    }

    public void cerrarBD() {
        db.close();
    }

    public List<EntProducto> listarTodo() {
        List<EntProducto> listaEntProductos = new ArrayList<>();

        abrirBD();

        Cursor cursor = db.query("Producto", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("prod_id"));
                String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("prod_description"));
                double precio = cursor.getDouble(cursor.getColumnIndexOrThrow("prod_price"));
                int tipoId = cursor.getInt(cursor.getColumnIndexOrThrow("tipo_id"));
                String imagen = cursor.getString(cursor.getColumnIndexOrThrow("prod_image"));

                EntProducto entProducto = new EntProducto(id, descripcion, precio, tipoId, imagen);
                listaEntProductos.add(entProducto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        cerrarBD();

        return listaEntProductos;
    }

    public EntProducto listarPorId(int id) {
        abrirBD();

        Cursor cursor = db.query("Producto", null, "prod_id=?", new String[]{String.valueOf(id)}, null, null, null);

        EntProducto entProducto = null;

        if (cursor.moveToFirst()) {
            String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("prod_description"));
            double precio = cursor.getDouble(cursor.getColumnIndexOrThrow("prod_price"));
            int tipoId = cursor.getInt(cursor.getColumnIndexOrThrow("tipo_id"));
            String imagen = cursor.getString(cursor.getColumnIndexOrThrow("prod_image"));

            entProducto = new EntProducto(id, descripcion, precio, tipoId, imagen);
        }

        cursor.close();
        cerrarBD();

        return entProducto;
    }

    public List<EntProducto> filtrarProductosPorTipo(int tipoId) {
        List<EntProducto> listaEntProductos = new ArrayList<>();

        abrirBD();

        Cursor cursor = db.query("Producto", null, "tipo_id=?", new String[]{String.valueOf(tipoId)}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("prod_id"));
                String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("prod_description"));
                double precio = cursor.getDouble(cursor.getColumnIndexOrThrow("prod_price"));
                String imagen = cursor.getString(cursor.getColumnIndexOrThrow("prod_image"));

                EntProducto entProducto = new EntProducto(id, descripcion, precio, tipoId, imagen);
                listaEntProductos.add(entProducto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        cerrarBD();

        return listaEntProductos;
    }
}
