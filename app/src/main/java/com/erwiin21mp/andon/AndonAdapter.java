package com.erwiin21mp.andon;

import static com.erwiin21mp.andon.Index.AMARILLO;
import static com.erwiin21mp.andon.Index.AZUL;
import static com.erwiin21mp.andon.Index.ROJO;
import static com.erwiin21mp.andon.Index.VERDE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AndonAdapter extends RecyclerView.Adapter<AndonAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<AndonModel> list;

    public AndonAdapter(Context context, ArrayList<AndonModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AndonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new AndonAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AndonAdapter.MyViewHolder holder, int position) {
        holder.Label_FechaYResponsable.setText(String.valueOf(list.get(position).getDia()).concat("/").concat(String.valueOf(list.get(position).getMes()).concat("/").concat(String.valueOf(list.get(position).getYear()).concat(" ").concat(String.valueOf(list.get(position).getHora()).concat(":").concat(String.valueOf(list.get(position).getMinuto()).concat(":").concat(String.valueOf(list.get(position).getSegundo()).concat(" · ").concat(list.get(position).getResponsable())))))));
        holder.Label_Descripcion.setText(list.get(position).getDescripcion());
        switch (list.get(position).getEstado()) {
            case ROJO:
                holder.Image_Estado.setBackgroundResource(R.drawable.rojo);
                break;
            case AMARILLO:
                holder.Image_Estado.setBackgroundResource(R.drawable.amarillo);
                break;
            case AZUL:
                holder.Image_Estado.setBackgroundResource(R.drawable.azul);
                break;
            case VERDE:
                holder.Image_Estado.setBackgroundResource(R.drawable.verde);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView Image_Estado;
        TextView Label_FechaYResponsable, Label_Descripcion;

        public MyViewHolder(View view) {
            super(view);
            Label_FechaYResponsable = view.findViewById(R.id.TextView_FechaYResponsable);
            Label_Descripcion = view.findViewById(R.id.TextView_Descripcion);
            Image_Estado = view.findViewById(R.id.ImageView_Estado);
        }

    }
}
