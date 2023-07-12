package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.pjimenez.cakeandbake.entidades.EntLocal;
import com.pjimenez.cakeandbake.model.DAOLocal;

import java.util.ArrayList;
import java.util.List;

public class Contactenos extends AppCompatActivity {

    ImageButton btnMenu;
    RecyclerView lstUbicacionLocal;
    filaContacto adaptador;
    List<EntLocal> listaEntLocal = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactenos);
        lstUbicacionLocal = findViewById(R.id.lstLocal);
        cargarDatos();
        asignarReferencias();
    }

    private void asignarReferencias() {
        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> {
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
        });
    }

    private void cargarDatos() {
        DAOLocal daoLocal = new DAOLocal(getApplicationContext());
        daoLocal.abrirBD();
        listaEntLocal = daoLocal.listarTodo();
        adaptador = new filaContacto(this, listaEntLocal);
        lstUbicacionLocal.setAdapter(adaptador);
        lstUbicacionLocal.setLayoutManager(new LinearLayoutManager(this));
    }
}