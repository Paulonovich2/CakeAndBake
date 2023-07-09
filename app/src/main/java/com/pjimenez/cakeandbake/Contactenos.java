package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pjimenez.cakeandbake.entidades.EntLocal;
import com.pjimenez.cakeandbake.model.DAOLocal;

import java.util.ArrayList;
import java.util.List;

public class Contactenos extends AppCompatActivity {

    RecyclerView lstUbicacionLocal;
    filaContacto adaptador;
    List<EntLocal> listaEntLocal = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactenos);
        lstUbicacionLocal = findViewById(R.id.lstLocal);
        cargarDatos();
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