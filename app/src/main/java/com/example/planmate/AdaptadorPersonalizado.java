package com.example.planmate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.ViewHolder> {

    private ArrayList<Tarea> listadoInformacion;
    private OnItemClickListener onItemClickListener;

    public AdaptadorPersonalizado(ArrayList<Tarea> listadoInformacion) {
        this.listadoInformacion = listadoInformacion;
        this.onItemClickListener = null;
    }
    public void setListadoInformacion(ArrayList<Tarea> listadoInformacion) {
        this.listadoInformacion = listadoInformacion;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AdaptadorPersonalizado.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View miView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lista_tareas,parent,false);
        return new ViewHolder(miView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizado.ViewHolder holder, int position) {
        Tarea miTarea = listadoInformacion.get(position);
        holder.enlazar(miTarea);
    }

    @Override
    public int getItemCount() {
        return listadoInformacion.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button btnTituloTarea;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnTituloTarea = itemView.findViewById(R.id.bt_tarea);
        }

        public void enlazar(Tarea miTarea){
            btnTituloTarea.setText(miTarea.getTitulo());

            if (onItemClickListener != null){
                btnTituloTarea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemBtnTituloTarea(miTarea,getAdapterPosition());
                    }
                });
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Tarea miTarea, int posicion);
        void onItemBtnTituloTarea(Tarea miTarea, int posicion);
    }
}