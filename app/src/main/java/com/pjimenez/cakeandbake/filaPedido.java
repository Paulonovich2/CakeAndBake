package com.pjimenez.cakeandbake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pjimenez.cakeandbake.entidades.EntPedido;

import java.util.ArrayList;
import java.util.List;

public class filaPedido extends RecyclerView.Adapter<filaPedido.MyViewHolder> {

    Context context;
    List<EntPedido> listsPedidos = new ArrayList<>();

    public filaPedido(Context context, List<EntPedido> listsPedidos) {
        this.context = context;
        this.listsPedidos = listsPedidos;
    }

    @NonNull
    @Override
    public filaPedido.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.activity_fila_pedido, parent, false);
        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull filaPedido.MyViewHolder holder, int position) {
        holder.filaProducto.setText(listsPedidos.get(position).getProd_description());
    }

    @Override
    public int getItemCount() {
        return listsPedidos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView filaProducto, filaPrecio;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            filaProducto = itemView.findViewById(R.id.fila_producto_pedido);
            filaPrecio = itemView.findViewById(R.id.fila_producto_precio);
        }
    }
}
