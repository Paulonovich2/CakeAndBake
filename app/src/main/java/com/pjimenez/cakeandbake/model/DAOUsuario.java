package com.pjimenez.cakeandbake.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pjimenez.cakeandbake.entidades.Usuario;
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

    public Usuario validarExistenciaUsuario(String email, String password) {
        abrirBD();

        String[] projection = {"id", "nombre", "apellido", "email", "password", "imagen"};
        String selection = "email = ? AND password = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(
                "usuario",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Usuario usuario = null;
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido"));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            String imagen = cursor.getString(cursor.getColumnIndexOrThrow("imagen"));

            usuario = new Usuario(id, nombre, apellido, email, pass, imagen);
        }

        cursor.close();
        cerrarBD();

        return usuario;
    }

    public boolean registrarUsuario(Usuario usuario) {
        abrirBD();

        ContentValues values = new ContentValues();
        values.put("nombre", usuario.getNombre());
        values.put("apellido", usuario.getApellido());
        values.put("email", usuario.getEmail());
        values.put("password", usuario.getPassword());
        values.put("imagen", usuario.getImagen());

        long resultado = db.insert("usuario", null, values);

        cerrarBD();

        return resultado != -1;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        abrirBD();

        ContentValues values = new ContentValues();
        values.put("nombre", usuario.getNombre());
        values.put("apellido", usuario.getApellido());
        values.put("email", usuario.getEmail());
        values.put("password", usuario.getPassword());
        values.put("imagen", usuario.getImagen());

        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(usuario.getId())};

        int filasActualizadas = db.update("usuario", values, selection, selectionArgs);

        cerrarBD();

        return filasActualizadas > 0;
    }
}
