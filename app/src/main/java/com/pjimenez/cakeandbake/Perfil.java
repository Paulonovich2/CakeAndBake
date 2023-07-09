package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pjimenez.cakeandbake.entidades.EntUsuario;
import com.pjimenez.cakeandbake.model.DAOUsuario;

public class Perfil extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtCorreo;
    Button btnActualizarPerfil;

    SessionManager sessionManager; // Nueva instancia de SessionManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        sessionManager = new SessionManager(this); // Inicializar el SessionManager

        asignarReferencias();
        cargarDatosPerfil();
        configurarBotonActualizarPerfil();
    }

    private void asignarReferencias() {
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnActualizarPerfil = findViewById(R.id.btnActualizarPerfil);
    }

    private void cargarDatosPerfil() {
        // Obtener el usuario actual desde el SessionManager
        EntUsuario entUsuario = sessionManager.obtenerUsuario();

        // Mostrar los datos del usuario en los campos de texto
        txtNombre.setText(entUsuario.getNombre());
        txtApellido.setText(entUsuario.getApellido());
        txtCorreo.setText(entUsuario.getEmail());
    }

    private void configurarBotonActualizarPerfil() {
        btnActualizarPerfil.setOnClickListener(v -> {
            // Obtener los nuevos valores de los campos de texto
            String nombre = txtNombre.getText().toString();
            String apellido = txtApellido.getText().toString();
            String correo = txtCorreo.getText().toString();

            // Obtener el usuario actual desde el SessionManager
            EntUsuario entUsuario = sessionManager.obtenerUsuario();

            // Actualizar los datos del usuario
            entUsuario.setNombre(nombre);
            entUsuario.setApellido(apellido);
            entUsuario.setEmail(correo);

            // Crear una instancia de DAOUsuario
            DAOUsuario daoUsuario = new DAOUsuario(getApplicationContext());

            // Actualizar el usuario en la base de datos
            boolean exito = daoUsuario.actualizarUsuario(entUsuario);

            if (exito) {
                Toast.makeText(Perfil.this, "Perfil actualizado exitosamente", Toast.LENGTH_SHORT).show();
                // Actualizar los datos del usuario en el SessionManager
                sessionManager.guardarSesionActiva(entUsuario);
            } else {
                Toast.makeText(Perfil.this, "Error al actualizar el perfil", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
