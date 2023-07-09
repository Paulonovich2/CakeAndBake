package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pjimenez.cakeandbake.entidades.EntLocal;
import com.pjimenez.cakeandbake.entidades.EntUsuario;
import com.pjimenez.cakeandbake.model.DAOLocal;

import java.util.ArrayList;
import java.util.List;

public class PrincipalLocal extends AppCompatActivity {

    ImageButton btnDelivery;
    ImageButton btnMenu;
    Button btnProductos;
    RecyclerView lstUbicacionLocal;
    filaLocal adaptador;
    List<EntLocal> listaEntLocal = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_local);

        asignarReferencias();
        cargarDatos();
        mostrarNombreUsuario();
    }

    private void asignarReferencias() {
        btnDelivery = findViewById(R.id.btn_delivery);
        btnDelivery.setOnClickListener(v -> {
            Intent intent = new Intent(this, Principal.class);
            startActivity(intent);
        });

        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> {
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
        });

        lstUbicacionLocal = findViewById(R.id.lstUbicacionLocal);
    }

    private void cargarDatos() {
        DAOLocal daoLocal = new DAOLocal(getApplicationContext());
        daoLocal.abrirBD();
        listaEntLocal = daoLocal.listarTodo();
        adaptador = new filaLocal(this, listaEntLocal);
        lstUbicacionLocal.setAdapter(adaptador);
        lstUbicacionLocal.setLayoutManager(new LinearLayoutManager(this));
    }

    private void mostrarNombreUsuario() {
        SessionManager sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.sesionActiva()) {
            EntUsuario entUsuario = sessionManager.obtenerUsuario();
            String userFullName = entUsuario.getNombre() + " " + entUsuario.getApellido();
            TextView lblUsuario = findViewById(R.id.lblUsuario);
            lblUsuario.setText("¡Hola " + userFullName + "!");
        }
    }
}