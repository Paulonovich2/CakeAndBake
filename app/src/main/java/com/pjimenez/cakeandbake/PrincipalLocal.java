package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PrincipalLocal extends AppCompatActivity {

    ImageButton btn_delivery;
    ImageButton btn_Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_local);

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

        cargaListaUbicacion();
    }

    public void cargaListaUbicacion(){
        List<String> elementos = new ArrayList<>();
        elementos.add("Local San Isidro");
        elementos.add("Local San Miguel");
        elementos.add("Local Ate");

        ListView listView = findViewById(R.id.lstUbicacionLocal);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, elementos);
        listView.setAdapter(adapter);
    }
}