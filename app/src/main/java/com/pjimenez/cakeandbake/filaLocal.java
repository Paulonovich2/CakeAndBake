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

import com.pjimenez.cakeandbake.entidades.Local;

import java.util.ArrayList;
import java.util.List;
public class filaLocal extends RecyclerView.Adapter<filaLocal.MyViewHolder>{

    Context context;
    List<Local> listaLocal = new ArrayList<>();

    public filaLocal(Context context, List<Local> listaLocal){
        this.context=context;
        this.listaLocal=listaLocal;
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
        holder.filaDireccion.setText(listaLocal.get(position).getDireccion()+"");
        holder.filaSeleccionar.setOnClickListener(v -> {
            Intent intent=new Intent(context,Producto.class);
            intent.putExtra("l_id",listaLocal.get(position).getId()+"");
            intent.putExtra("l_direccion",listaLocal.get(position).getDireccion()+"");
            intent.putExtra("l_latitud",listaLocal.get(position).getLatitud()+"");
            intent.putExtra("l_longitud",listaLocal.get(position).getLongitud()+"");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaLocal.size();
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