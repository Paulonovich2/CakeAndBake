package com.pjimenez.cakeandbake;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.pjimenez.cakeandbake.entidades.EntLocal;
import com.pjimenez.cakeandbake.entidades.EntUsuario;

public class SessionManager {
    private static final String PREF_NAME = "Session";
    private static final String KEY_USER = "user";
    private static final String KEY_LOCAL = "local";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void guardarSesionActiva(EntUsuario entUsuario) {
        Gson gson = new Gson();
        String usuarioJson = gson.toJson(entUsuario);
        editor.putString(KEY_USER, usuarioJson);
        editor.apply();
    }

    public boolean sesionActiva() {
        return sharedPreferences.contains(KEY_USER);
    }

    public EntUsuario obtenerUsuario() {
        Gson gson = new Gson();
        String usuarioJson = sharedPreferences.getString(KEY_USER, "");
        return gson.fromJson(usuarioJson, EntUsuario.class);
    }

    public void cerrarSesion() {
        editor.remove(KEY_USER);
        editor.apply();
    }

    public void guardarLocalSeleccionado(EntLocal localSeleccionado) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SessionManager", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("local_id", String.valueOf(localSeleccionado.getId()));
        editor.putString("local_direccion", localSeleccionado.getDireccion());
        editor.putString("local_latitud", String.valueOf(localSeleccionado.getLatitud()));
        editor.putString("local_longitud", String.valueOf(localSeleccionado.getLongitud()));
        editor.apply();
    }

    public EntLocal obtenerLocalSeleccionado() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SessionManager", Context.MODE_PRIVATE);
        String localId = sharedPreferences.getString("local_id", "");
        String localDireccion = sharedPreferences.getString("local_direccion", "");
        String localLatitud = sharedPreferences.getString("local_latitud", "");
        String localLongitud = sharedPreferences.getString("local_longitud", "");

        // Crear una instancia de EntLocal con los datos obtenidos
        EntLocal localSeleccionado = new EntLocal();
        localSeleccionado.setId(Integer.parseInt(localId));
        localSeleccionado.setDireccion(localDireccion);
        localSeleccionado.setLatitud(Double.parseDouble(localLatitud));
        localSeleccionado.setLongitud(Double.parseDouble(localLongitud));

        return localSeleccionado;
    }
}