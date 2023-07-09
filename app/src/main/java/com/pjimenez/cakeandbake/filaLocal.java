package com.pjimenez.cakeandbake;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pjimenez.cakeandbake.entidades.EntLocal;

import java.util.ArrayList;
import java.util.List;
public class filaLocal extends RecyclerView.Adapter<filaLocal.MyViewHolder>{

    Context context;
    List<EntLocal> listaEntLocal = new ArrayList<>();
    SessionManager sessionManager;

    public filaLocal(Context context, List<EntLocal> listaEntLocal){
        this.context=context;
        this.listaEntLocal = listaEntLocal;
        sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public filaLocal.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.activity_fila_local,parent,false);
        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull filaLocal.MyViewHolder holder, int position) {
        holder.filaDireccion.setText(listaEntLocal.get(position).getDireccion()+"");
        holder.filaSeleccionar.setOnClickListener(v -> {
            EntLocal localSeleccionado = listaEntLocal.get(position);
            sessionManager.guardarLocalSeleccionado(localSeleccionado);

            Intent intent = new Intent(context, Producto.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaEntLocal.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView filaDireccion;
        ImageButton filaSeleccionar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            filaDireccion=itemView.findViewById(R.id.fila_local_direccion);
            filaSeleccionar=itemView.findViewById(R.id.fila_local_seleccionar);
        }
    }
}