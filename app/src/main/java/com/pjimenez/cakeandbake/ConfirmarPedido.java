package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.pjimenez.cakeandbake.entidades.EntPedido;
import com.pjimenez.cakeandbake.model.DAOCompra;

import java.util.ArrayList;
import java.util.List;

public class ConfirmarPedido extends AppCompatActivity {

    Button boton_pagar;
    DAOCompra daoCompra = new DAOCompra(this);
    List<EntPedido> listaPedidos = new ArrayList<>();
    RecyclerView rvPedidos;
    filaPedido adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);
        asignarReferencias();
        cargarDatos();

        boton_pagar = findViewById(R.id.btn_pagar);
        boton_pagar.setOnClickListener(v -> {
            Intent objeto = new Intent(this, PedidoRegistrado.class);
            startActivity(objeto);
        });
    }

    private void cargarDatos(){
        daoCompra.abrirDB();
        listaPedidos = daoCompra.cargarPedidos();
        adaptador = new filaPedido(this, listaPedidos);
        rvPedidos.setAdapter(adaptador);
        rvPedidos.setLayoutManager(new LinearLayoutManager(this));
    }

    private void asignarReferencias(){
        rvPedidos = findViewById(R.id.lst_pedidos);
    }
}