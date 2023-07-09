package com.pjimenez.cakeandbake.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pjimenez.cakeandbake.entidades.EntLocal;
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

    public List<EntLocal> listarTodo() {
        List<EntLocal> listaLocales = new ArrayList<>();

        abrirBD();

        Cursor cursor = db.query("Local", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("local_id"));
                String direccion = cursor.getString(cursor.getColumnIndexOrThrow("local_description"));
                double latitud = cursor.getDouble(cursor.getColumnIndexOrThrow("local_latitud"));
                double longitud = cursor.getDouble(cursor.getColumnIndexOrThrow("local_longitud"));
                String telelfono = cursor.getString(cursor.getColumnIndexOrThrow("local_telephone"));

                EntLocal entLocal = new EntLocal(id, direccion, latitud, longitud, telelfono);
                listaLocales.add(entLocal);
            } while (cursor.moveToNext());
        }

        cursor.close();
        cerrarBD();

        return listaLocales;
    }

    public EntLocal listarPorId(int id) {
        abrirBD();

        Cursor cursor = db.query("Local", null, "local_id=?", new String[]{String.valueOf(id)}, null, null, null);

        EntLocal entLocal = null;

        if (cursor.moveToFirst()) {
            String direccion = cursor.getString(cursor.getColumnIndexOrThrow("local_description"));
            double latitud = cursor.getDouble(cursor.getColumnIndexOrThrow("local_latitud"));
            double longitud = cursor.getDouble(cursor.getColumnIndexOrThrow("local_longitud"));
            String telefono = cursor.getString(cursor.getColumnIndexOrThrow("local_telephone"));
            entLocal = new EntLocal(id, direccion, latitud, longitud, telefono);
        }

        cursor.close();
        cerrarBD();

        return entLocal;
    }
}
