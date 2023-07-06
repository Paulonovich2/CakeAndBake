package com.pjimenez.cakeandbake.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cake_bake.db";
    private static final int DATABASE_VERSION = 2;

    // Nombres de las tablas y sus columnas
    private static final String TABLE_USUARIO = "usuario";
    private static final String TABLE_LOCAL = "local";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_APELLIDO = "apellido";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_IMAGEN = "imagen";
    private static final String COLUMN_DIRECCION = "direccion";
    private static final String COLUMN_LATITUD = "latitud";
    private static final String COLUMN_LONGITUD = "longitud";

    public BaseDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla "usuario"
        String queryUsuario = "CREATE TABLE " + TABLE_USUARIO + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOMBRE + " TEXT NOT NULL, "
                + COLUMN_APELLIDO + " TEXT NOT NULL, "
                + COLUMN_EMAIL + " TEXT NOT NULL, "
                + COLUMN_PASSWORD + " TEXT NOT NULL, "
                + COLUMN_IMAGEN + " TEXT NOT NULL"
                + ");";
        db.execSQL(queryUsuario);

        // Insertar datos de ejemplo en la tabla "usuario"
        String insertUsuario = "INSERT INTO " + TABLE_USUARIO + " VALUES(NULL, 'Paul', 'Jimenez', 'paul.jimenez.pozo@gmail.com','paul','');";
        db.execSQL(insertUsuario);

        // Crear tabla "local"
        String queryLocal = "CREATE TABLE " + TABLE_LOCAL + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DIRECCION + " TEXT NOT NULL, "
                + COLUMN_LATITUD + " REAL NOT NULL, "
                + COLUMN_LONGITUD + " REAL NOT NULL"
                + ");";
        db.execSQL(queryLocal);

        // Insertar datos de ejemplo en la tabla "usuario"
        String insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'San Miguel', -12.020202, -77.550030);";
        db.execSQL(insertLocal);

        insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'San Isidro', -12.020214, -77.550030);";
        db.execSQL(insertLocal);

        insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'Miraflores', -12.020263, -77.550030);";
        db.execSQL(insertLocal);

        insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'San Juan de Lurigancho', -12.0202609, -77.550030);";
        db.execSQL(insertLocal);

        insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'Los Olivos', -12.0212334, -77.550030);";
        db.execSQL(insertLocal);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí puedes implementar la lógica de actualización de la base de datos si es necesario
    }
}