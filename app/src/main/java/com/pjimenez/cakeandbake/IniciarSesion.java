package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pjimenez.cakeandbake.entidades.Usuario;
import com.pjimenez.cakeandbake.model.DAOUsuario;

public class IniciarSesion extends AppCompatActivity {

    EditText txtEmail,txtPassword;
    Button boton_ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        asignarReferencias();
    }

    private void asignarReferencias() {
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword= findViewById(R.id.txtPassword);
        boton_ingresar = findViewById(R.id.btn_ingresar);
        boton_ingresar.setOnClickListener(v -> {
            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();

            DAOUsuario daoUsuario = new DAOUsuario(getApplicationContext());
            Usuario usuarioValido = daoUsuario.validarExistenciaUsuario(email, password);

            if (usuarioValido != null) {
                Intent intent = new Intent(IniciarSesion.this, Principal.class);
                intent.putExtra("u_id",usuarioValido.getId());
                intent.putExtra("u_nombres",usuarioValido.getNombre() + " " + usuarioValido.getApellido());
                startActivity(intent);
            } else {
                Toast.makeText(IniciarSesion.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}