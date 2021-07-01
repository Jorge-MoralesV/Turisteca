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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Datos_Ciudades> listaCiudad;
    private List<Datos_Ciudades> listaOriginal;
    private RecyclerItemClick itemClick;

    public RecyclerViewAdapter(List<Datos_Ciudades> listaCiudad, RecyclerItemClick itemClick) {
        this.listaCiudad = listaCiudad;
        this.listaOriginal = new ArrayList<>();
        this.itemClick = itemClick;
        listaOriginal.addAll(listaCiudad);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ciudad, parent, false);
        //ViewHolder viewHolder = new ViewHolder(view);
        return new ViewHolder(view);//viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Datos_Ciudades item = listaCiudad.get(position);
        holder.nombre.setText(listaCiudad.get(position).getNombre());
        holder.foto.setImageResource(listaCiudad.get(position).getFoto());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaCiudad.size();
    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            listaCiudad.clear();
            listaCiudad.addAll(listaOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                listaCiudad.clear();
                List<Datos_Ciudades> collect = listaOriginal.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                listaCiudad.addAll(collect);
            } else {
                listaCiudad.clear();
                for (Datos_Ciudades i : listaOriginal) {
                    if (i.getNombre().toLowerCase().contains(strSearch)) {
                        listaCiudad.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;
        private ImageView foto;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.id_nombre_ciudad);
            foto = itemView.findViewById(R.id.id_imagen_ciudad);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(Datos_Ciudades item);
    }
}
