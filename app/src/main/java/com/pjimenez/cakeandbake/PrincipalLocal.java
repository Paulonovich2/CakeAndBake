package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.pjimenez.cakeandbake.entidades.Local;
import com.pjimenez.cakeandbake.model.DAOLocal;
import com.pjimenez.cakeandbake.model.DAOUsuario;

import java.util.ArrayList;
import java.util.List;

public class PrincipalLocal extends AppCompatActivity {

    ImageButton btn_delivery;
    ImageButton btn_Menu;
    Button btn_productos;
    RecyclerView lstUbicacionLocal;
    filaLocal adaptador;
    List<Local> listaLocal =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_local);

        asignarReferencias();
        cargarDatos();
    }

    private void asignarReferencias() {
        btn_delivery = findViewById(R.id.btn_delivery);
        btn_delivery.setOnClickListener(v -> {
            Intent objeto = new Intent(this, Principal.class);
            startActivity(objeto);
        });

        btn_Menu = findViewById(R.id.btnMenu);
        btn_Menu.setOnClickListener(v -> {
            Intent objMenu = new Intent(this, Menu.class);
            startActivity(objMenu);
        });

        btn_productos = findViewById(R.id.btn_Guardar_local);
        btn_productos.setOnClickListener(v -> {
            Intent objMenu = new Intent(this, Producto.class);
            startActivity(objMenu);
        });

        lstUbicacionLocal = findViewById(R.id.lstUbicacionLocal);
    }

    private void cargarDatos() {
        DAOLocal daoLocal = new DAOLocal(getApplicationContext());
        daoLocal.abrirBD();
        listaLocal =daoLocal.listarTodo();
        adaptador=new filaLocal(this, listaLocal);
        lstUbicacionLocal.setAdapter(adaptador);
        lstUbicacionLocal.setLayoutManager(new LinearLayoutManager(this));
    }
}