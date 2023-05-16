package com.example.planmate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.ViewHolder> {

    //private ArrayList<Tarea> listadoInformacion;
    private OnItemClickListener onItemClickListener;
    private Context context;
    private List<Tarea> listadoInformacion;
    public AdaptadorPersonalizado(Context context,List<Tarea> listadoInformacion) {
        this.listadoInformacion = listadoInformacion;
        this.onItemClickListener = null;
        this.context = context;
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
        Tarea tarea = listadoInformacion.get(position);
        holder.enlazar(tarea);

        final Context activityContext = context; // Almacena el contexto de la actividad MainActivity en una variable final

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(activityContext, DetallesTareaActivity.class);
                intent.putExtra("tarea", tarea);
                activityContext.startActivity(intent);
            }
        });
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

        public void enlazar(Tarea tarea){
            btnTituloTarea.setText(tarea.getTitulo());

            if (onItemClickListener != null){
                btnTituloTarea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemBtnTituloTarea(tarea,getAdapterPosition());
                    }
                });
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Tarea tarea, int posicion);
        void onItemBtnTituloTarea(Tarea tarea, int posicion);
    }
}
