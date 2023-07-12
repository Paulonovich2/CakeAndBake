package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pjimenez.cakeandbake.entidades.EntLocal;
import com.pjimenez.cakeandbake.entidades.EntPedidoConfirmar;
import com.pjimenez.cakeandbake.entidades.EntUsuario;
import com.pjimenez.cakeandbake.model.DAOPedido;
import com.pjimenez.cakeandbake.model.DAOPedidoProducto;
import com.pjimenez.cakeandbake.model.DAOProducto;
import com.pjimenez.cakeandbake.util.BaseDatos;

import java.util.ArrayList;
import java.util.List;

public class ConfirmarPedido extends AppCompatActivity {

    TextView lblUsuario, lblDireccionLocal, txtPrecioFinal;
    Button boton_pagar;
    private double precioFinal = 0.0;
    private int cantidad = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        asignarReferencia();
        mostrarNombreUsuario();
        mostrarNombreDireccion();
        cargarProductosPedido();

        boton_pagar = findViewById(R.id.btn_pagar);
        boton_pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resto del código...

                int pedidoId = getIntent().getIntExtra("pedidoId", -1);
                if (pedidoId != -1) {
                    // El producto se guardó correctamente en el pedido
                    Toast.makeText(ConfirmarPedido.this, "Pedido guardado correctamente", Toast.LENGTH_SHORT).show();

                    // Actualizar el flag "realizado" a true en la base de datos
                    DAOPedido daoPedido = new DAOPedido(ConfirmarPedido.this);
                    long updated = daoPedido.updatePedidoRealizado(pedidoId, true);

                    if (updated>-1) {
                        // El flag "realizado" se actualizó correctamente
                        Intent objeto = new Intent(ConfirmarPedido.this, PedidoRegistrado.class);
                        startActivity(objeto);
                    } else {
                        // Ocurrió un error al actualizar el flag "realizado"
                        Toast.makeText(ConfirmarPedido.this, "Error al confirmar el pago'", Toast.LENGTH_SHORT).show();
                    }

                    // Resto del código...
                } else {
                    // Ocurrió un error al guardar el producto en el pedido
                    Toast.makeText(ConfirmarPedido.this, "Error al confirmar el pago", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void asignarReferencia() {
        lblUsuario = findViewById(R.id.lblUsuario);
        lblDireccionLocal = findViewById(R.id.lblDireccionLocal);
        txtPrecioFinal = findViewById(R.id.txtPrecioFinal);
    }

    private void mostrarNombreUsuario() {
        SessionManager sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.sesionActiva()) {
            EntUsuario entUsuario = sessionManager.obtenerUsuario();
            String userFullName = entUsuario.getNombre() + " " + entUsuario.getApellido();
            lblUsuario.setText("¡Hola " + userFullName + "!");
        }
    }

    private void mostrarNombreDireccion() {
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        EntLocal localSeleccionado = sessionManager.obtenerLocalSeleccionado();

        if (localSeleccionado != null) {
            String direccionLocal = localSeleccionado.getDireccion();
            lblDireccionLocal.setText("Tienda: " + direccionLocal);
        }
    }

    private void cargarProductosPedido() {
        int pedidoId = getIntent().getIntExtra("pedidoId", 0);

        // Obtener una instancia de la base de datos en modo lectura
        BaseDatos dbHelper = new BaseDatos(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Obtener los productos del pedido a través del DAO DAOPedidoProducto
        DAOPedidoProducto daoPedidoProducto = new DAOPedidoProducto(db);
        List<EntPedidoConfirmar> pedidoProductoList = daoPedidoProducto.getPedidoProductos(pedidoId);

        // Cerrar la conexión con la base de datos
        db.close();

        // Configurar el adaptador en el RecyclerView
        PedidoProductoAdapter adapter = new PedidoProductoAdapter(pedidoProductoList);
        RecyclerView recyclerView = findViewById(R.id.lst_pedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        double precioParcial=0.0;
        // Calcular el precio final
        for (EntPedidoConfirmar pedidoProducto : pedidoProductoList) {
            double precio = pedidoProducto.getProd_price();
            int cantidad = pedidoProducto.getPedido_producto_cantidad();
            precioParcial=precio*cantidad;
            precioFinal += precioParcial;
        }
        txtPrecioFinal.setText(String.valueOf(precioFinal));
    }
}
