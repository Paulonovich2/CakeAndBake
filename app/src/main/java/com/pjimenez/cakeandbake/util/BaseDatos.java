package com.pjimenez.cakeandbake.util;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cake_bake.db";
    private static final int DATABASE_VERSION = 1;

    // Tablas y columnas de la base de datos
    private static final String TABLE_USUARIO = "Usuario";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_LASTNAME = "user_lastname";
    private static final String COLUMN_USER_MAIL = "user_mail";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_IMAGE = "user_image";

    private static final String TABLE_DIRECCION_USUARIO = "DireccionUsuario";
    private static final String COLUMN_DIR_ID = "dir_id";
    private static final String COLUMN_USER_ID_FK = "user_id";
    private static final String COLUMN_DIR_LATITUD = "dir_latitud";
    private static final String COLUMN_DIR_LONGITUD = "dir_longitud";
    private static final String COLUMN_DIR_DESCRIPTION = "dir_description";

    private static final String TABLE_LOCAL = "Local";
    private static final String COLUMN_LOCAL_ID = "local_id";
    private static final String COLUMN_LOCAL_DESCRIPTION = "local_description";
    private static final String COLUMN_LOCAL_LATITUD = "local_latitud";
    private static final String COLUMN_LOCAL_LONGITUD = "local_longitud";
    private static final String COLUMN_LOCAL_TELEPHONE = "local_telephone";

    private static final String TABLE_PRODUCTO = "Producto";
    private static final String COLUMN_PROD_ID = "prod_id";
    private static final String COLUMN_PROD_DESCRIPTION = "prod_description";
    private static final String COLUMN_PROD_PRICE = "prod_price";
    private static final String COLUMN_TIPO_ID_FK = "tipo_id";
    private static final String COLUMN_PROD_IMAGE = "prod_image";

    private static final String TABLE_TIPO_PRODUCTO = "TipoProducto";
    private static final String COLUMN_TIPO_ID = "tipo_id";
    private static final String COLUMN_TIPO_DESCRIPTION = "tipo_description";
    private static final String COLUMN_TIPO_IMAGE = "tipo_image";

    private static final String TABLE_PEDIDO = "Pedido";
    private static final String COLUMN_PEDIDO_USER_ID = "user_id";
    private static final String COLUMN_PEDIDO_PROD_ID = "prod_id";
    private static final String COLUMN_PEDIDO_LOCAL_ID = "local_id";
    private static final String COLUMN_PEDIDO_DIR_ID = "dir_id";
    private static final String COLUMN_PEDIDO_CANTIDAD = "pedido_cantidad";

    public BaseDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla "Usuario"
        String queryUsuario = "CREATE TABLE " + TABLE_USUARIO + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_NAME + " TEXT NOT NULL, "
                + COLUMN_USER_LASTNAME + " TEXT NOT NULL, "
                + COLUMN_USER_MAIL + " TEXT NOT NULL, "
                + COLUMN_USER_PASSWORD + " TEXT NOT NULL, "
                + COLUMN_USER_IMAGE + " TEXT"
                + ");";
        db.execSQL(queryUsuario);

        // Insertar datos de ejemplo en la tabla "usuario"
        String insertUsuario = "INSERT INTO " + TABLE_USUARIO + " VALUES(NULL, 'Paul', 'Jimenez', 'paul@gmail.com','paul','');";
        db.execSQL(insertUsuario);

        insertUsuario = "INSERT INTO " + TABLE_USUARIO + " VALUES(NULL, 'Jessica', 'Zarate', 'jessica@gmail.com','jess','');";
        db.execSQL(insertUsuario);

        insertUsuario = "INSERT INTO " + TABLE_USUARIO + " VALUES(NULL, 'Josue', 'Torre', 'josue@gmail.com','josu','');";
        db.execSQL(insertUsuario);

        // Crear la tabla "DireccionUsuario"
        String queryDireccionUsuario = "CREATE TABLE " + TABLE_DIRECCION_USUARIO + " ("
                + COLUMN_DIR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_ID_FK + " INTEGER NOT NULL, "
                + COLUMN_DIR_LATITUD + " REAL NOT NULL, "
                + COLUMN_DIR_LONGITUD + " REAL NOT NULL, "
                + COLUMN_DIR_DESCRIPTION + " TEXT NOT NULL, "
                + "FOREIGN KEY (" + COLUMN_USER_ID_FK + ") REFERENCES " + TABLE_USUARIO + "(" + COLUMN_USER_ID + ")"
                + ");";
        db.execSQL(queryDireccionUsuario);

        // Crear la tabla "Local"
        String queryLocal = "CREATE TABLE " + TABLE_LOCAL + " ("
                + COLUMN_LOCAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_LOCAL_DESCRIPTION + " TEXT NOT NULL, "
                + COLUMN_LOCAL_LATITUD + " REAL NOT NULL, "
                + COLUMN_LOCAL_LONGITUD + " REAL NOT NULL, "
                + COLUMN_LOCAL_TELEPHONE + " TEXT"
                + ");";
        db.execSQL(queryLocal);

        // Insertar datos de ejemplo en la tabla "local"
        String insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'San Miguel', -12.020202, -77.550030, '(51) 300-1000');";
        db.execSQL(insertLocal);

        insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'San Isidro', -12.020214, -77.550030, '(51) 300-2000');";
        db.execSQL(insertLocal);

        insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'Miraflores', -12.020263, -77.550030, '(51) 300-3000');";
        db.execSQL(insertLocal);

        insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'San Juan de Lurigancho', -12.0202609, -77.550030, '(51) 300-4000');";
        db.execSQL(insertLocal);

        insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'Los Olivos', -12.0212334, -77.550030, '(51) 300-5000');";
        db.execSQL(insertLocal);

        // Crear la tabla "TipoProducto"
        String queryTipoProducto = "CREATE TABLE " + TABLE_TIPO_PRODUCTO + " ("
                + COLUMN_TIPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TIPO_DESCRIPTION + " TEXT NOT NULL, "
                + COLUMN_TIPO_IMAGE + " TEXT"
                + ");";
        db.execSQL(queryTipoProducto);

        // Insertar datos de ejemplo en la tabla "tipo producto"
        String insertTipoProducto = "INSERT INTO " + TABLE_TIPO_PRODUCTO + " VALUES(NULL, 'Torta', 'torta');";
        db.execSQL(insertTipoProducto);

        insertTipoProducto = "INSERT INTO " + TABLE_TIPO_PRODUCTO + " VALUES(NULL, 'Helado', 'helado');";
        db.execSQL(insertTipoProducto);

        insertTipoProducto = "INSERT INTO " + TABLE_TIPO_PRODUCTO + " VALUES(NULL, 'Cheesecake', 'cheesecake');";
        db.execSQL(insertTipoProducto);

        // Crear la tabla "Producto"
        String queryProducto = "CREATE TABLE " + TABLE_PRODUCTO + " ("
                + COLUMN_PROD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PROD_DESCRIPTION + " TEXT NOT NULL, "
                + COLUMN_PROD_PRICE + " REAL NOT NULL, "
                + COLUMN_TIPO_ID_FK + " INTEGER NOT NULL, "
                + COLUMN_PROD_IMAGE + " TEXT, "
                + "FOREIGN KEY (" + COLUMN_TIPO_ID_FK + ") REFERENCES " + TABLE_TIPO_PRODUCTO + "(" + COLUMN_TIPO_ID + ")"
                + ");";
        db.execSQL(queryProducto);

        // Insertar datos de ejemplo en la tabla "tipo producto"
        String insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Torta helada', 12, 1, 'tortahelada');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Selva negra', 17, 1, 'selvanegra');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Helado con frutas', 10, 2, 'heladoconfruta');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Banana Split', 8, 2, 'bananasplit');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Tres leches', 14, 1, 'tresleches');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Torta de zanahoria', 16, 1, 'tortazanahoria');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Torta helada', 12, 1, 'tortahelada');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Cheesecake de durazno', 20, 3, 'cheesecakedurazno');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Cheesecake de fresa', 22, 1, 'cheesecakefresa');";
        db.execSQL(insertProducto);

        // Crear la tabla "Pedido"
        String queryPedido = "CREATE TABLE " + TABLE_PEDIDO + " ("
                + COLUMN_PEDIDO_USER_ID + " INTEGER NOT NULL, "
                + COLUMN_PEDIDO_PROD_ID + " INTEGER NOT NULL, "
                + COLUMN_PEDIDO_LOCAL_ID + " INTEGER NOT NULL, "
                + COLUMN_PEDIDO_DIR_ID + " INTEGER NOT NULL, "
                + COLUMN_PEDIDO_CANTIDAD + " INTEGER NOT NULL, "
                + "FOREIGN KEY (" + COLUMN_PEDIDO_USER_ID + ") REFERENCES " + TABLE_USUARIO + "(" + COLUMN_USER_ID + "), "
                + "FOREIGN KEY (" + COLUMN_PEDIDO_PROD_ID + ") REFERENCES " + TABLE_PRODUCTO + "(" + COLUMN_PROD_ID + "), "
                + "FOREIGN KEY (" + COLUMN_PEDIDO_LOCAL_ID + ") REFERENCES " + TABLE_LOCAL + "(" + COLUMN_LOCAL_ID + "), "
                + "FOREIGN KEY (" + COLUMN_PEDIDO_DIR_ID + ") REFERENCES " + TABLE_DIRECCION_USUARIO + "(" + COLUMN_DIR_ID + ")"
                + ");";
        db.execSQL(queryPedido);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
