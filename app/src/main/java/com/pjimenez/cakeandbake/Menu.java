package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.pjimenez.cakeandbake.entidades.EntUsuario;

public class Menu extends AppCompatActivity {

    Button mi_perfil1;
    Button mi_pedido1;
    Button contactanos1;
    Button inicio1;
    TextView lblUsuario;

    SessionManager sessionManager; // Nueva instancia de SessionManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        sessionManager = new SessionManager(this); // Inicializar el SessionManager

        // Verificar si hay una sesión activa
        if (!sessionManager.sesionActiva()) {
            // No hay sesión activa, redirigir a la actividad de inicio de sesión
            Intent intent = new Intent(this, IniciarSesion.class);
            startActivity(intent);
            finish(); // Finalizar la actividad actual para evitar volver atrás con el botón "Atrás"
        }

        asignarReferencias();
        configurarBotones();
        mostrarNombreUsuario();
    }

    private void asignarReferencias() {
        lblUsuario = findViewById(R.id.lblUsuario);
        inicio1 = findViewById(R.id.btnInicio);
        mi_perfil1 = findViewById(R.id.btnPerfil);
        mi_pedido1 = findViewById(R.id.btnMisPedidos);
        contactanos1 = findViewById(R.id.btnContactenos);
    }

    private void configurarBotones() {
        inicio1.setOnClickListener(v -> {
            Intent intent = new Intent(this, Principal.class);
            startActivity(intent);
        });

        mi_perfil1.setOnClickListener(v -> {
            Intent intent = new Intent(this, Perfil.class);
            startActivity(intent);
        });

        mi_pedido1.setOnClickListener(v -> {
            Intent intent = new Intent(this, Pedido.class);
            startActivity(intent);
        });

        contactanos1.setOnClickListener(v -> {
            Intent intent = new Intent(this, Contactenos.class);
            startActivity(intent);
        });
    }

    private void mostrarNombreUsuario() {
        EntUsuario entUsuario = sessionManager.obtenerUsuario();
        if (entUsuario != null) {
            String userFullName = entUsuario.getNombre() + " " + entUsuario.getApellido();
            lblUsuario.setText("¡Hola " + userFullName + "!");
        }
    }
}