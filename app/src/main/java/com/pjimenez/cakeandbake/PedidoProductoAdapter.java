package com.pjimenez.cakeandbake;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pjimenez.cakeandbake.entidades.EntPedidoConfirmar;
import com.pjimenez.cakeandbake.entidades.EntPedidoProducto;

import java.util.List;

public class PedidoProductoAdapter extends RecyclerView.Adapter<PedidoProductoAdapter.PedidoProductoViewHolder> {

    private List<EntPedidoConfirmar> pedidoProductoList;

    public PedidoProductoAdapter(List<EntPedidoConfirmar> pedidoProductoList) {
        this.pedidoProductoList = pedidoProductoList;
    }

    @NonNull
    @Override
    public PedidoProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_pedido_producto, parent, false);
        return new PedidoProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoProductoViewHolder holder, int position) {
        EntPedidoConfirmar pedidoProducto = pedidoProductoList.get(position);
        // Configurar los datos del producto en el ViewHolder
        holder.bind(pedidoProducto);
    }

    @Override
    public int getItemCount() {
        return pedidoProductoList.size();
    }

    public void setPedidoProductoList(List<EntPedidoConfirmar> pedidoProductoList) {
        this.pedidoProductoList = pedidoProductoList;
    }

    public static class PedidoProductoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtProductoNombre;
        private TextView txtProductoPrecio;
        private TextView txtProductoCantidad;

        public PedidoProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductoNombre = itemView.findViewById(R.id.txt_producto_nombre);
            txtProductoPrecio = itemView.findViewById(R.id.txt_producto_precio);
            txtProductoCantidad = itemView.findViewById(R.id.txt_producto_cantidad);
        }

        public void bind(EntPedidoConfirmar pedidoProducto) {
            txtProductoNombre.setText(pedidoProducto.getProd_tittle());
            int cantidad = pedidoProducto.getPedido_producto_cantidad();
            double precio = pedidoProducto.getProd_price();
            double precioTotal = cantidad*precio;

            txtProductoPrecio.setText(String.valueOf(precioTotal));
        }
    }
}