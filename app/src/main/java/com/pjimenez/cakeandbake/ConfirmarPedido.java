package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.pjimenez.cakeandbake.entidades.EntLocal;
import com.pjimenez.cakeandbake.entidades.EntPedidoConfirmar;
import com.pjimenez.cakeandbake.entidades.EntUsuario;
import com.pjimenez.cakeandbake.model.DAOPedidoProducto;
import com.pjimenez.cakeandbake.util.BaseDatos;

import java.util.ArrayList;
import java.util.List;

public class ConfirmarPedido extends AppCompatActivity {

    TextView lblUsuario, lblDireccionLocal;
    Button boton_pagar;
    private RecyclerView recyclerView;
    private PedidoProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        asignarReferencia();
        mostrarNombreUsuario();
        mostrarNombreDireccion();

        recyclerView = findViewById(R.id.lst_pedidos);
        adapter = new PedidoProductoAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        boton_pagar = findViewById(R.id.btn_pagar);
        boton_pagar.setOnClickListener(v -> {
            Intent objeto = new Intent(this, PedidoRegistrado.class);
            startActivity(objeto);
        });

        cargarProductosPedido(); // Llama al método para cargar los productos del pedido
    }

    private void asignarReferencia(){
        lblUsuario = findViewById(R.id.lblUsuario);
        lblDireccionLocal=findViewById(R.id.lblDireccionLocal);
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
        // Obtener una instancia de la base de datos en modo lectura
        BaseDatos dbHelper = new BaseDatos(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Obtener los productos del pedido a través del DAO DAOPedidoProducto
        DAOPedidoProducto daoPedidoProducto = new DAOPedidoProducto(db);
        List<EntPedidoConfirmar> pedidoProductoList = daoPedidoProducto.getPedidoProductos(1);

        // Cerrar la conexión con la base de datos
        db.close();

        // Actualizar los datos del adaptador y notificar los cambios
        adapter.setPedidoProductoList(pedidoProductoList);
        adapter.notifyDataSetChanged();
    }

}