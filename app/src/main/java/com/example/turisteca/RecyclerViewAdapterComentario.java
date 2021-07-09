package com.example.turisteca;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterComentario extends RecyclerView.Adapter<RecyclerViewAdapterComentario.ViewHolder> {

    private List<Datos_Comentario> listaCom;
    private List<Datos_Comentario> listaOriginalC;

    public RecyclerViewAdapterComentario(List<Datos_Comentario> listaCom) {
        this.listaCom = listaCom;
        this.listaOriginalC = new ArrayList<>();
        listaOriginalC.addAll(listaCom);
    }

    @NonNull
    @Override
    public RecyclerViewAdapterComentario.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, parent, false);
        return new RecyclerViewAdapterComentario.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterComentario.ViewHolder holder, int position) {
        final Datos_Comentario iteml = listaOriginalC.get(position);
        holder.nombrec.setText(listaCom.get(position).getNombrec());
        holder.comentarioc.setText(listaCom.get(position).getComentarioc());
        holder.fotoc.setImageResource(listaCom.get(position).getFotoc());

    }

    @Override
    public int getItemCount() {
        return listaCom.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombrec;
        private TextView comentarioc;
        private ImageView fotoc;

        public ViewHolder(View itemView) {
            super(itemView);
            nombrec = itemView.findViewById(R.id.id_nombre_usr);
            comentarioc = itemView.findViewById(R.id.id_comentario);
            fotoc = itemView.findViewById(R.id.id_imagen_usr);
        }
    }

}
