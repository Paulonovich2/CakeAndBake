package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pjimenez.cakeandbake.entidades.EntLocal;
import com.pjimenez.cakeandbake.entidades.EntUsuario;
import com.pjimenez.cakeandbake.model.DAOProducto;
import com.pjimenez.cakeandbake.entidades.EntProducto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Producto extends AppCompatActivity {

    ImageButton btnMenu, btnShoppingCar;
    CheckBox checkboxTorta, checkboxHelado, checkboxCheesecake;
    private ListView listView;
    private List<EntProducto> productos, productosFiltrados;
    TextView lblUsuario, lblDireccionLocal;
    private String nombreCliente, direccionLocal;
    private int pedidoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        asignarReferencia();
        mostrarNombreUsuario();
        mostrarNombreDireccion();

        // Crear una lista vacía para los productos filtrados
        productosFiltrados = new ArrayList<>();

        Intent intent = getIntent();
        if (intent != null) {
            pedidoId = intent.getIntExtra("pedidoId", 0);
        }
    }

    private void asignarReferencia() {
        lblUsuario = findViewById(R.id.lblUsuario);
        lblDireccionLocal=findViewById(R.id.lblDireccionLocal);
        checkboxTorta = findViewById(R.id.checkboxTorta);
        checkboxHelado = findViewById(R.id.checkboxHelado);
        checkboxCheesecake = findViewById(R.id.checkboxCheesecake);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        if (intent != null) {
            nombreCliente = intent.getStringExtra("nombreCliente");
            direccionLocal = intent.getStringExtra("direccionLocal");
        }

        // Inicializar el ListView
        listView = findViewById(R.id.lst_pasteles);

        // Obtener la lista de productos desde el DAO
        DAOProducto daoProducto = new DAOProducto(this);
        productos = daoProducto.listarTodo();

        // Crear una lista de nombres de productos
        List<String> nombresProductos = new ArrayList<>();
        for (EntProducto producto : productos) {
            nombresProductos.add(producto.getTitulo());
        }

        // Configurar el adaptador del ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombresProductos);
        listView.setAdapter(adapter);

        // Establecer un listener de clic para los elementos del ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el producto seleccionado del ListView
                EntProducto productoSeleccionado = productos.get(position);

                // Crear un Intent para abrir la otra actividad
                Intent intent = new Intent(Producto.this, ProductoPedido.class);
                // Pasa cualquier dato adicional a la otra actividad si es necesario
                intent.putExtra("prod_id", String.valueOf(productoSeleccionado.getId()));
                startActivity(intent);
            }
        });

        List<EntProducto> productosFiltrados = new ArrayList<>();

        checkboxTorta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                filtrarProductosPorTipo(1, isChecked);
                mostrarProductosFiltrados();
            }
        });

        checkboxHelado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                filtrarProductosPorTipo(2, isChecked);
                mostrarProductosFiltrados();
            }
        });

        checkboxCheesecake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                filtrarProductosPorTipo(3, isChecked);
                mostrarProductosFiltrados();
            }
        });

        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, Menu.class);
            startActivity(intent1);
        });

        btnShoppingCar= findViewById(R.id.btnShoppingCar);
        btnShoppingCar.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, ConfirmarPedido.class);
            intent1.putExtra("pedidoId", pedidoId);
            startActivity(intent1);
        });
    }

    private void filtrarProductosPorTipo(int tipoId, boolean isChecked) {
        if (isChecked) {
            // Agregar los productos de tipo "Torta" a la lista de productos filtrados
            List<EntProducto> productosPorTipo = new ArrayList<>();
            for (EntProducto producto : productos) {
                if (producto.getTipoId() == tipoId) {
                    productosPorTipo.add(producto);
                }
            }
            productosFiltrados.addAll(productosPorTipo);
        } else {
            // Remover los productos de tipo "Torta" de la lista de productos filtrados
            List<EntProducto> productosPorTipo = new ArrayList<>();
            for (EntProducto producto : productosFiltrados) {
                if (producto.getTipoId() == tipoId) {
                    productosPorTipo.add(producto);
                }
            }
            productosFiltrados.removeAll(productosPorTipo);
        }
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

    private void mostrarProductosFiltrados() {
        // Crear una lista de nombres de productos filtrados
        List<String> nombresProductosFiltrados = new ArrayList<>();
        for (EntProducto producto : productosFiltrados) {
            nombresProductosFiltrados.add(producto.getTitulo());
        }

        // Crear un nuevo adaptador con la lista de nombres de productos filtrados
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombresProductosFiltrados);

        // Establecer el nuevo adaptador en el ListView
        listView.setAdapter(adapter);
    }
}