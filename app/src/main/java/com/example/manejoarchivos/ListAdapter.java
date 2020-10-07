package com.example.manejoarchivos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListAdapterViewHolder>{
    private ArrayList<String> descripcion;
    private int resource;
    private Activity activity;

    public ListAdapter(ArrayList<String> des, int resource, Activity activity) {
        this.descripcion = des;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new ListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapterViewHolder holder, int position) {
       holder.codigo.setText(descripcion.get(position));
       holder.descripcion.setText(MainActivity.crud.getCodigo(position));
       holder.precio.setText(MainActivity.crud.getPrecio(position));
    }
    @Override
    public int getItemCount() {
        return descripcion.size();
    }

    public class ListAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView codigo, precio, descripcion;
        public ListAdapterViewHolder(View itemView) {
            super(itemView);
            codigo=(TextView)itemView.findViewById(R.id.codigo_l);
            precio=(TextView)itemView.findViewById(R.id.precio_l);
            descripcion=(TextView)itemView.findViewById(R.id.descripcion_l);
        }
    }
}