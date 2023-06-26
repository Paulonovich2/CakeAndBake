package com.pjimenez.cakeandbake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Producto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        List<String> elementos = new ArrayList<>();
        elementos.add("Tarta de fresa");
        elementos.add("Pastel de manzana");
        elementos.add("Pie de limon");

        ListView listView = findViewById(R.id.lst_pasteles);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, elementos);
        listView.setAdapter(adapter);
    }
}