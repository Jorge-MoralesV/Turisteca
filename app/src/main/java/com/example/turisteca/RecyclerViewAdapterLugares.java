package com.example.turisteca;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerViewAdapterLugares extends RecyclerView.Adapter<RecyclerViewAdapterLugares.ViewHolder> {

    private List<Datos_Lugar> listaLugar;
    private List<Datos_Lugar> listaOriginalL;
    private RecyclerViewAdapterLugares.RecyclerItemClick itemClickL;

    public RecyclerViewAdapterLugares(List<Datos_Lugar> listaLugar, RecyclerViewAdapterLugares.RecyclerItemClick itemClick) {
        this.listaLugar = listaLugar;
        this.listaOriginalL = new ArrayList<>();
        this.itemClickL = itemClick;
        listaOriginalL.addAll(listaLugar);
    }

    @NonNull
    @Override
    public RecyclerViewAdapterLugares.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lugar, parent, false);
        //ViewHolder viewHolder = new ViewHolder(view);
        return new RecyclerViewAdapterLugares.ViewHolder(view);//viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterLugares.ViewHolder holder, int position) {
        final Datos_Lugar iteml = listaOriginalL.get(position);
        holder.nombrel.setText(listaLugar.get(position).getNombrel());
        holder.fotol.setImageResource(listaLugar.get(position).getFotol());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickL.itemClick(iteml);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaLugar.size();
    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            listaLugar.clear();
            listaLugar.addAll(listaOriginalL);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                listaLugar.clear();
                List<Datos_Lugar> collect = listaOriginalL.stream()
                        .filter(i -> i.getNombrel().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                listaLugar.addAll(collect);
            } else {
                listaLugar.clear();
                for (Datos_Lugar i : listaOriginalL) {
                    if (i.getNombrel().toLowerCase().contains(strSearch)) {
                        listaLugar.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombrel;
        private ImageView fotol;

        public ViewHolder(View itemView) {
            super(itemView);
            nombrel = itemView.findViewById(R.id.id_nombre_lugar);
            fotol = itemView.findViewById(R.id.id_imagen_lugar);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(Datos_Lugar item);
    }
}
