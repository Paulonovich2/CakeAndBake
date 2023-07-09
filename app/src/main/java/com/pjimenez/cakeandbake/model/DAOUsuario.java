package com.pjimenez.cakeandbake.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pjimenez.cakeandbake.entidades.EntUsuario;
import com.pjimenez.cakeandbake.util.BaseDatos;

public class DAOUsuario {
    private BaseDatos baseDatos;
    private SQLiteDatabase db;
    private Context context;

    public DAOUsuario(Context context) {
        baseDatos = new BaseDatos(context);
        this.context = context;
    }

    public void abrirBD() {
        db = baseDatos.getWritableDatabase();
    }

    public void cerrarBD() {
        db.close();
    }

    public EntUsuario validarExistenciaUsuario(String email, String password) {
        abrirBD();

        String[] projection = {"user_id", "user_name", "user_lastname", "user_mail", "user_password", "user_image"};
        String selection = "user_mail = ? AND user_password = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(
                "Usuario",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        EntUsuario entUsuario = null;
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("user_name"));
            String apellido = cursor.getString(cursor.getColumnIndexOrThrow("user_lastname"));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow("user_password"));
            String imagen = cursor.getString(cursor.getColumnIndexOrThrow("user_image"));

            entUsuario = new EntUsuario(id, nombre, apellido, email, pass, imagen);
        }

        cursor.close();
        cerrarBD();

        return entUsuario;
    }

    public boolean registrarUsuario(EntUsuario entUsuario) {
        abrirBD();

        ContentValues values = new ContentValues();
        values.put("user_name", entUsuario.getNombre());
        values.put("user_lastname", entUsuario.getApellido());
        values.put("user_mail", entUsuario.getEmail());
        values.put("user_password", entUsuario.getPassword());
        values.put("user_image", entUsuario.getImagen());

        long resultado = db.insert("Usuario", null, values);

        cerrarBD();

        return resultado != -1;
    }

    public boolean actualizarUsuario(EntUsuario entUsuario) {
        abrirBD();

        ContentValues values = new ContentValues();
        values.put("user_name", entUsuario.getNombre());
        values.put("user_lastname", entUsuario.getApellido());
        values.put("user_mail", entUsuario.getEmail());
        values.put("user_password", entUsuario.getPassword());
        values.put("user_image", entUsuario.getImagen());

        String selection = "user_id = ?";
        String[] selectionArgs = {String.valueOf(entUsuario.getId())};

        int filasActualizadas = db.update("Usuario", values, selection, selectionArgs);

        cerrarBD();

        return filasActualizadas > 0;
    }
}