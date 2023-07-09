package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pjimenez.cakeandbake.entidades.EntUsuario;
import com.pjimenez.cakeandbake.model.DAOUsuario;

public class Registrarse extends AppCompatActivity {

    EditText txtNombre, txtApellidos, txtEmail, txtPassword;
    Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        asignarReferencias();
        configurarBotonRegistrarse();
    }

    private void asignarReferencias() {
        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnRegistrarse = findViewById(R.id.btn_registrarse_principal);
    }

    private void configurarBotonRegistrarse() {
        btnRegistrarse.setOnClickListener(v -> {
            String nombre = txtNombre.getText().toString();
            String apellidos = txtApellidos.getText().toString();
            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();

            // Crear un nuevo objeto Usuario con los datos ingresados
            EntUsuario nuevoEntUsuario = new EntUsuario(nombre, apellidos, email, password, "");

            // Crear una instancia de DAOUsuario
            DAOUsuario daoUsuario = new DAOUsuario(getApplicationContext());

            // Registrar el nuevo usuario en la base de datos
            boolean exito = daoUsuario.registrarUsuario(nuevoEntUsuario);

            if (exito) {
                Toast.makeText(Registrarse.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();

                // Guardar sesión activa
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                sessionManager.guardarSesionActiva(nuevoEntUsuario);

                // Redirigir a la actividad principal
                Intent intent = new Intent(Registrarse.this, Principal.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual para evitar volver atrás con el botón "Atrás"
            } else {
                Toast.makeText(Registrarse.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
