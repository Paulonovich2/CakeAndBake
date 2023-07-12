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
    private static final String COLUMN_PROD_TITTLE ="prod_tittle";
    private static final String COLUMN_PROD_DESCRIPTION = "prod_description";
    private static final String COLUMN_PROD_PRICE = "prod_price";
    private static final String COLUMN_TIPO_ID_FK = "tipo_id";
    private static final String COLUMN_PROD_IMAGE = "prod_image";

    private static final String TABLE_TIPO_PRODUCTO = "TipoProducto";
    private static final String COLUMN_TIPO_ID = "tipo_id";
    private static final String COLUMN_TIPO_DESCRIPTION = "tipo_description";
    private static final String COLUMN_TIPO_IMAGE = "tipo_image";

    private static final String TABLE_PEDIDO = "Pedido";
    private static final String COLUMN_PEDIDO_PEDIDO_ID = "pedido_id";
    private static final String COLUMN_PEDIDO_USER_ID = "user_id";
    private static final String COLUMN_PEDIDO_LOCAL_ID = "local_id";
    private static final String COLUMN_PEDIDO_DIR_ID = "dir_id";
    private static final String COLUMN_PEDIDO_REALIZADO = "realizado";

    private static final String TABLE_PEDIDO_PRODUCTO = "PedidoProducto";
    private static final String COLUMN_PEDIDO_PRODUCTO_PEDIDO_ID = "pedido_id";
    private static final String COLUMN_PEDIDO_PRODUCTO_PROD_ID = "prod_id";
    private static final String COLUMN_PEDIDO_PRODUCTO_CANTIDAD = "pedido_producto_cantidad";

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
        String insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'San Miguel', -12.0408714,-77.0742315, '(51) 300-1000');";
        db.execSQL(insertLocal);

        insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'San Isidro', -12.095041,-77.0404459, '(51) 300-2000');";
        db.execSQL(insertLocal);

        insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'Miraflores', -12.1292492,-77.0343758, '(51) 300-3000');";
        db.execSQL(insertLocal);

        insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'San Juan de Lurigancho', -12.006077,-76.9965056, '(51) 300-4000');";
        db.execSQL(insertLocal);

        insertLocal = "INSERT INTO " + TABLE_LOCAL + " VALUES(NULL, 'Los Olivos', -11.9341929,-77.0770731, '(51) 300-5000');";
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
                + COLUMN_PROD_TITTLE + " TEXT NOT NULL, "
                + COLUMN_PROD_DESCRIPTION + " TEXT NOT NULL, "
                + COLUMN_PROD_PRICE + " REAL NOT NULL, "
                + COLUMN_TIPO_ID_FK + " INTEGER NOT NULL, "
                + COLUMN_PROD_IMAGE + " TEXT, "
                + "FOREIGN KEY (" + COLUMN_TIPO_ID_FK + ") REFERENCES " + TABLE_TIPO_PRODUCTO + "(" + COLUMN_TIPO_ID + ")"
                + ");";
        db.execSQL(queryProducto);

        // Insertar datos de ejemplo en la tabla "tipo producto"
        String insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Torta helada','Tarta elaborada con helado y pastel o helado en forma de tarta. Puede incluir múltiples ingredientes, como bizcocho, crema o chocolate, entre otros.', 12, 1, 'tortahelada');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Selva negra','La tarta o torta Selva Negra (en alemán, Schwarzwälder Kirschtorte) es una tarta típica de la cocina de Baden y uno de los dulces más característicos de la gastronomía alemana.', 17, 1, 'selvanegra');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Helado artesanal','Los helados artesanales tienen bajo contenido de grasa, un 6 por ciento. Fortalece los huesos y regula la presión arterial a través de su aporte de calcio.', 10, 2, 'heladoartesanal');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Banana Split','Banana split es un postre que lleva un plátano cortado en dos y bolas de helado de vainilla, chocolate y fresa. Se le agrega caramelo, sirope de chocolate y sirope de fresa. Se decora con crema batida, nueces y una cereza encima.', 8, 2, 'bananasplit');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Tres leches','El Tres leches es un pastel húmedo y denso originario de Latinoamérica, bañado con una mezcla de tres leches: leche condensada, leche evaporada y crema para batir.', 14, 1, 'tresleches');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Pastel de zanahoria','Posee vitaminas A, K1, Potasio y vitamina B6 ideales para aportar energía al organismo y fortalecer el sistema inmunológico.', 16, 1, 'pastelzanahoria');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Torta Red Velvet','“Red Velvet cake” es un pastel de color rojo oscuro, rojo brillante o rojo-marron. Se prepara como un pastel en capas con sabor a vainilla o chocolate cubierto con un glaseado de queso cremoso o roux cocinado.', 12, 1, 'tortaredvelvet');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Cheesecake de durazno','Su origen se dio hace miles de años, pero muchos son los que piensan que viene de New York, la realidad es que el origen del cheesecake está en la antigua Grecia y el Imperio Romano.', 20, 3, 'cheesecakedurazno');";
        db.execSQL(insertProducto);

        insertProducto = "INSERT INTO " + TABLE_PRODUCTO + " VALUES(NULL, 'Cheesecake de fresa','Su origen se dio hace miles de años, pero muchos son los que piensan que viene de New York, la realidad es que el origen del cheesecake está en la antigua Grecia y el Imperio Romano.', 22, 3, 'cheesecakefresa');";
        db.execSQL(insertProducto);

        // Crear la tabla "Pedido"
        String queryPedido = "CREATE TABLE " + TABLE_PEDIDO + " ("
                + COLUMN_PEDIDO_PEDIDO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PEDIDO_USER_ID + " INTEGER NOT NULL, "
                + COLUMN_PEDIDO_LOCAL_ID + " INTEGER, "
                + COLUMN_PEDIDO_DIR_ID + " INTEGER, "
                + COLUMN_PEDIDO_REALIZADO + " INTEGER NOT NULL DEFAULT 0, " // Por defecto, el pedido no está realizado
                + "FOREIGN KEY (" + COLUMN_PEDIDO_USER_ID + ") REFERENCES " + TABLE_USUARIO + "(" + COLUMN_USER_ID + "), "
                + "FOREIGN KEY (" + COLUMN_PEDIDO_LOCAL_ID + ") REFERENCES " + TABLE_LOCAL + "(" + COLUMN_LOCAL_ID + "), "
                + "FOREIGN KEY (" + COLUMN_PEDIDO_DIR_ID + ") REFERENCES " + TABLE_DIRECCION_USUARIO + "(" + COLUMN_DIR_ID + ")"
                + ");";
        db.execSQL(queryPedido);

        // Crear la tabla "Pedido Producto"
        String queryPedidoProducto = "CREATE TABLE " + TABLE_PEDIDO_PRODUCTO + " ("
                + COLUMN_PEDIDO_PRODUCTO_PEDIDO_ID + " INTEGER NOT NULL, "
                + COLUMN_PEDIDO_PRODUCTO_PROD_ID + " INTEGER NOT NULL, "
                + COLUMN_PEDIDO_PRODUCTO_CANTIDAD + " INTEGER NOT NULL, "
                + "FOREIGN KEY (" + COLUMN_PEDIDO_PRODUCTO_PEDIDO_ID + ") REFERENCES " + TABLE_PEDIDO + "(" + COLUMN_PEDIDO_PEDIDO_ID + "), "
                + "FOREIGN KEY (" + COLUMN_PEDIDO_PRODUCTO_PROD_ID + ") REFERENCES " + TABLE_PRODUCTO + "(" + COLUMN_PROD_ID + ")"
                + ");";
        db.execSQL(queryPedidoProducto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
