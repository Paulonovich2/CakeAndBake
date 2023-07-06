package com.pjimenez.cakeandbake.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pjimenez.cakeandbake.entidades.Local;
import com.pjimenez.cakeandbake.util.BaseDatos;

import java.util.ArrayList;
import java.util.List;

public class DAOLocal {
    private BaseDatos baseDatos;
    private SQLiteDatabase db;
    private Context context;

    public DAOLocal(Context context) {
        baseDatos = new BaseDatos(context);
        this.context = context;
    }

    public void abrirBD() {
        db = baseDatos.getWritableDatabase();
    }

    public void cerrarBD() {
        db.close();
    }

    public List<Local> listarTodo() {
        List<Local> listaLocales = new ArrayList<>();

        abrirBD();

        Cursor cursor = db.query("local", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"));
                double latitud = cursor.getDouble(cursor.getColumnIndexOrThrow("latitud"));
                double longitud = cursor.getDouble(cursor.getColumnIndexOrThrow("longitud"));

                Local local = new Local(id, direccion, latitud, longitud);
                listaLocales.add(local);
            } while (cursor.moveToNext());
        }

        cursor.close();
        cerrarBD();

        return listaLocales;
    }

    public Local listarPorId(int id) {
        abrirBD();

        Cursor cursor = db.query("local", null, "id=?", new String[]{String.valueOf(id)}, null, null, null);

        Local local = null;

        if (cursor.moveToFirst()) {
            String direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"));
            double latitud = cursor.getDouble(cursor.getColumnIndexOrThrow("latitud"));
            double longitud = cursor.getDouble(cursor.getColumnIndexOrThrow("longitud"));

            local = new Local(id, direccion, latitud, longitud);
        }

        cursor.close();
        cerrarBD();

        return local;
    }
}
