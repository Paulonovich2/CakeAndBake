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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pjimenez.cakeandbake.entidades.EntUsuario;

public class Principal extends AppCompatActivity {

    ImageButton btn_locacion, btn_Menu;
    Button btn_productos, btnUbicacionActual, btnDireccion;
    TextView lblUsuario;
    EditText txtUbicacionActual;
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        asignarReferencia();
        mostrarNombreUsuario();
    }

    private void asignarReferencia() {
        btnDireccion = findViewById(R.id.btnDireccion);
        btnUbicacionActual = findViewById(R.id.btnUbicacionActual);
        lblUsuario = findViewById(R.id.lblUsuario);
        txtUbicacionActual = findViewById(R.id.txtUbicacionActual);

        btn_locacion = findViewById(R.id.btn_locacion);
        btn_locacion.setOnClickListener(v -> {
            Intent objPrincipalLocal = new Intent(this, PrincipalLocal.class);
            startActivity(objPrincipalLocal);
        });

        btn_Menu = findViewById(R.id.btnMenu);
        btn_Menu.setOnClickListener(v -> {
            Intent objMenu = new Intent(this, Menu.class);
            startActivity(objMenu);
        });

        btn_productos = findViewById(R.id.btn_Guardar_principal);
        btn_productos.setOnClickListener(v -> {
            Intent objMenu = new Intent(this, Producto.class);
            startActivity(objMenu);
        });

        btnDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        btnUbicacionActual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar los permisos de ubicación
                if (ContextCompat.checkSelfPermission(Principal.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // Permiso concedido, obtener la ubicación actual
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if (lastKnownLocation != null) {
                        double latitude = lastKnownLocation.getLatitude();
                        double longitude = lastKnownLocation.getLongitude();

                        // Actualizar el TextView con la ubicación actual
                        txtUbicacionActual.setText("Latitud: " + latitude + ", Longitud: " + longitude);
                    } else {
                        // No se pudo obtener la ubicación actual
                        Toast.makeText(Principal.this, "No se pudo obtener la ubicación actual", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Permiso denegado, solicitar permiso de ubicación al usuario
                    ActivityCompat.requestPermissions(Principal.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
                }
            }
        });
    }

    private void mostrarNombreUsuario() {
        SessionManager sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.sesionActiva()) {
            EntUsuario entUsuario = sessionManager.obtenerUsuario();
            String userFullName = entUsuario.getNombre() + " " + entUsuario.getApellido();
            lblUsuario.setText("¡Hola " + userFullName + "!");
        }
    }
}