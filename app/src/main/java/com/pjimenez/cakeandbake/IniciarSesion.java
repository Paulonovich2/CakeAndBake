package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pjimenez.cakeandbake.entidades.EntUsuario;
import com.pjimenez.cakeandbake.model.DAOUsuario;

public class IniciarSesion extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button botonIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        asignarReferencias();
    }

    private void asignarReferencias() {
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        botonIngresar = findViewById(R.id.btn_ingresar);
        botonIngresar.setOnClickListener(v -> {
            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();

            DAOUsuario daoUsuario = new DAOUsuario(getApplicationContext());
            EntUsuario entUsuarioValido = daoUsuario.validarExistenciaUsuario(email, password);

            if (entUsuarioValido != null) {
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                sessionManager.guardarSesionActiva(entUsuarioValido);

                Intent intent = new Intent(IniciarSesion.this, Principal.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(IniciarSesion.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardarSesionActiva(EntUsuario entUsuarioValido) {
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        sessionManager.guardarSesionActiva(entUsuarioValido);
    }
}
