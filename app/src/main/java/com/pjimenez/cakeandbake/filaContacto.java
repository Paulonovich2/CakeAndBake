package com.pjimenez.cakeandbake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.Manifest;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pjimenez.cakeandbake.entidades.EntLocal;

import java.util.ArrayList;
import java.util.List;
public class filaContacto extends RecyclerView.Adapter<filaContacto.MyViewHolder>{

    Context context;
    List<EntLocal> listaEntLocal = new ArrayList<>();
    SessionManager sessionManager;

    public filaContacto(Context context, List<EntLocal> listaEntLocal){
        this.context=context;
        this.listaEntLocal = listaEntLocal;
        sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public filaContacto.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.activity_fila_contacto,parent,false);
        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull filaContacto.MyViewHolder holder, int position) {
        // Obtener la latitud y longitud del local seleccionado
        double latitud = listaEntLocal.get(position).getLatitud();
        double longitud = listaEntLocal.get(position).getLongitud();

        holder.filaDireccion.setText(listaEntLocal.get(position).getDireccion()+"");
        holder.filaSeleccionar.setOnClickListener(v -> {
            EntLocal localSeleccionado = listaEntLocal.get(position);
            sessionManager.guardarLocalSeleccionado(localSeleccionado);

            Intent intent = new Intent(context, Producto.class);
            context.startActivity(intent);
        });

        // Agregar el marcador de ubicación en el mapa
        holder.mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // Mover la cámara al marcador de ubicación
                LatLng ubicacion = new LatLng(latitud, longitud);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15f));

                // Agregar el marcador rojo en la ubicación
                MarkerOptions markerOptions = new MarkerOptions().position(ubicacion).title("Ubicación");
                Marker marker = googleMap.addMarker(markerOptions);
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            }
        });

        String telefono = listaEntLocal.get(position).getTelefono();

        // Configurar el clic del botón para realizar la llamada telefónica
        holder.filaSeleccionar.setOnClickListener(v -> {
            // Verificar si se ha otorgado el permiso de llamada telefónica
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                // Realizar la llamada telefónica
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono));
                context.startActivity(intent);
            } else {
                // Solicitar permiso de llamada telefónica al usuario
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaEntLocal.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView filaDireccion;
        ImageButton filaSeleccionar;
        MapView mapView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            filaDireccion=itemView.findViewById(R.id.fila_local_direccion);
            filaSeleccionar=itemView.findViewById(R.id.fila_local_seleccionar);
            mapView = itemView.findViewById(R.id.mapsGoogle);
            mapView.onCreate(null);
            mapView.onResume();
        }
    }
}