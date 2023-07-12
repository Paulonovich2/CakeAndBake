package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pjimenez.cakeandbake.entidades.EntLocal;
import com.pjimenez.cakeandbake.entidades.EntPedido;
import com.pjimenez.cakeandbake.entidades.EntPedidoProducto;
import com.pjimenez.cakeandbake.entidades.EntProducto;
import com.pjimenez.cakeandbake.entidades.EntUsuario;
import com.pjimenez.cakeandbake.model.DAOPedido;
import com.pjimenez.cakeandbake.model.DAOPedidoProducto;
import com.pjimenez.cakeandbake.model.DAOProducto;
import com.pjimenez.cakeandbake.util.BaseDatos;

public class ProductoPedido extends AppCompatActivity {

    TextView txtProducto, txtPrecio, txtDescripcion, txtPrecioTotal;
    EditText editTextCantidad;
    Button buttonGuardarPedido, buttonMenos, buttonMas;
    double precioProducto;
    int userId, localId, dirId, pedidoId, prodId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_pedido);

        asignarReferencias();
        botonesNumericos();
        guardarPedido();
    }

    private int obtenerIdUsuario() {
        SessionManager sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.sesionActiva()) {
            EntUsuario entUsuario = sessionManager.obtenerUsuario();
            return entUsuario.getId();
        }else{
            return 0;
        }
    }

    private int obtenerIdDireccion() {
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        EntLocal localSeleccionado = sessionManager.obtenerLocalSeleccionado();

        if (localSeleccionado != null) {
            return localSeleccionado.getId();
        }else{
            return 0;
        }
    }

    private void guardarPedido() {
        buttonGuardarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la cantidad ingresada por el usuario
                String cantidad = editTextCantidad.getText().toString();
                userId = obtenerIdUsuario();
                localId = obtenerIdDireccion();
                dirId = 0;

                // Verificar si existe un pedido activo para el usuario y la dirección
                EntPedido pedidoActivo = obtenerPedidoActivo(userId, localId);

                if (pedidoActivo != null) {
                    // Obtener el ID del pedido activo
                    int pedidoIdActivo = pedidoActivo.getPedidoId();

                    GuardarProductoPedido(pedidoIdActivo,prodId,cantidad);
                } else {
                    // Crear una instancia de la clase EntPedido y establecer los valores necesarios
                    EntPedido pedido = new EntPedido();
                    pedido.setUserId(userId); // Establecer el ID del usuario actual
                    pedido.setLocalId(localId); // Establecer el ID del local seleccionado
                    pedido.setDirId(dirId); // Establecer el ID de la dirección seleccionada
                    pedido.setRealizado(false); // El pedido aún no está realizado

                    // Obtener una instancia de la base de datos en modo escritura
                    BaseDatos dbHelper = new BaseDatos(ProductoPedido.this);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    // Crear una instancia del DAO DAOPedido y guardar el pedido en la base de datos
                    DAOPedido daoPedido = new DAOPedido(db);
                    pedidoId = (int) daoPedido.insertPedido(pedido);

                    GuardarProductoPedido(pedidoId,prodId,cantidad);
                    db.close();
                }
            }
        });
    }

    private void GuardarProductoPedido(int pedidoId, int prodId, String cantidad) {
        // Obtener una instancia de la base de datos en modo escritura
        BaseDatos dbHelper = new BaseDatos(ProductoPedido.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Crear una instancia de la clase EntPedidoProducto y establecer los valores necesarios
        EntPedidoProducto pedidoProducto = new EntPedidoProducto();
        pedidoProducto.setPedidoId(pedidoId); // Establecer el ID del pedido generado
        pedidoProducto.setProdId(prodId); // Establecer el ID del producto seleccionado
        pedidoProducto.setCantidad(Integer.parseInt(cantidad)); // Establecer la cantidad ingresada

        if (pedidoId != -1) {
            // El pedido se guardó correctamente, ahora guardar el producto en el pedido
            pedidoProducto.setPedidoId((int) pedidoId); // Actualizar el ID del pedido generado

            // Crear una instancia del DAO DAOPedidoProducto y guardar el producto en el pedido en la base de datos
            DAOPedidoProducto daoPedidoProducto = new DAOPedidoProducto(db);
            long pedidoProductoId = daoPedidoProducto.insertPedidoProducto(pedidoProducto);

            if (pedidoProductoId != -1) {
                // El producto se guardó correctamente en el pedido
                Toast.makeText(ProductoPedido.this, "Pedido guardado correctamente", Toast.LENGTH_SHORT).show();

                // Redirigir al usuario a la actividad principal o a otra pantalla
                Intent intent = new Intent(ProductoPedido.this, Producto.class);
                startActivity(intent);
            } else {
                // Ocurrió un error al guardar el producto en el pedido
                Toast.makeText(ProductoPedido.this, "Error al guardar el producto en el pedido", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Ocurrió un error al guardar el pedido
            Toast.makeText(ProductoPedido.this, "Error al guardar el pedido", Toast.LENGTH_SHORT).show();
        }

        // Cerrar la conexión con la base de datos
        db.close();
    }


    private void botonesNumericos() {
        buttonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cantidadStr = editTextCantidad.getText().toString();
                if (!cantidadStr.isEmpty()) {
                    int cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad > 1) {
                        cantidad--;
                        double cantidadTotal = precioProducto*cantidad;
                        editTextCantidad.setText(String.valueOf(cantidad));
                        txtPrecioTotal.setText(String.valueOf(cantidadTotal));
                    }
                }
            }
        });

        buttonMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cantidadStr = editTextCantidad.getText().toString();
                if (!cantidadStr.isEmpty()) {
                    int cantidad = Integer.parseInt(cantidadStr);
                    cantidad++;
                    double cantidadTotal = precioProducto*cantidad;
                    editTextCantidad.setText(String.valueOf(cantidad));
                    txtPrecioTotal.setText(String.valueOf(cantidadTotal));
                }
            }
        });
    }

    private void asignarReferencias() {
        txtProducto = findViewById(R.id.txtTitulo);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtPrecioTotal = findViewById(R.id.txtPrecioTotal);
        txtDescripcion=findViewById(R.id.txtDescripcion);
        editTextCantidad = findViewById(R.id.txtCantidad);
        buttonGuardarPedido = findViewById(R.id.buttonGuardarPedido);
        buttonMenos = findViewById(R.id.btnMenos);
        buttonMas = findViewById(R.id.btnMas);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        if (intent != null) {
            String prod_id = intent.getStringExtra("prod_id");
            prodId= Integer.parseInt(prod_id);

            // Obtener la lista de productos desde el DAO
            DAOProducto daoProducto = new DAOProducto(this);
            EntProducto productoSeleccionado = daoProducto.listarPorId(Integer.parseInt(prod_id));

            // Establecer los valores en los elementos de la interfaz
            txtProducto.setText(productoSeleccionado.getTitulo());
            txtDescripcion.setText(productoSeleccionado.getDescripcion());
            precioProducto=productoSeleccionado.getPrecio();
            txtPrecio.setText(String.valueOf(precioProducto));
            txtPrecioTotal.setText(String.valueOf(precioProducto));

            ImageView imageViewProducto = findViewById(R.id.ivImmagen);
            String nombreArchivoImagen = productoSeleccionado.getImagen();

            int resourceId = getResources().getIdentifier(nombreArchivoImagen, "drawable", getPackageName());
            if (resourceId != 0) {
                Glide.with(this)
                        .load(resourceId)
                        .placeholder(R.drawable.pastel_1)
                        .into(imageViewProducto);
            } else {
                // Si no se encuentra el recurso, cargar una imagen de error o mostrar un mensaje
                Glide.with(this)
                        .load(R.drawable.pastel_1)
                        .into(imageViewProducto);
            }
        }
    }

    private EntPedido obtenerPedidoActivo(int userId, int dirId) {
        BaseDatos dbHelper = new BaseDatos(ProductoPedido.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {"pedido_id", "user_id", "local_id", "dir_id", "realizado"};
        String selection = "user_id = ? AND dir_id = ? AND realizado = ?";
        String[] selectionArgs = {String.valueOf(userId), String.valueOf(dirId), "0"};

        Cursor cursor = db.query("Pedido", projection, selection, selectionArgs, null, null, null);
        EntPedido pedidoActivo = null;

        if (cursor.moveToFirst()) {
            int pedidoId = cursor.getInt(cursor.getColumnIndexOrThrow("pedido_id"));
            int usuarioId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
            int localId = cursor.getInt(cursor.getColumnIndexOrThrow("local_id"));
            int direccionId = cursor.getInt(cursor.getColumnIndexOrThrow("dir_id"));
            int realizado = cursor.getInt(cursor.getColumnIndexOrThrow("realizado"));

            pedidoActivo = new EntPedido();
            pedidoActivo.setPedidoId(pedidoId);
            pedidoActivo.setUserId(usuarioId);
            pedidoActivo.setLocalId(localId);
            pedidoActivo.setDirId(direccionId);
            pedidoActivo.setRealizado(realizado == 1);
        }

        cursor.close();
        db.close();

        return pedidoActivo;
    }


}