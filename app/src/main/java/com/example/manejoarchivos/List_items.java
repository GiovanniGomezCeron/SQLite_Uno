package com.example.manejoarchivos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class List_items extends BaseAdapter {
    private Context context;
    private int layout;
    private List<String> names;

    public List_items(Context context, int layout, List<String> names){
     this.context=context;
     this.layout=layout;
     this.names=names;
    }
    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int postion) {
        return names.get(postion);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View ConvertView, ViewGroup viewGroup) {
        View v=ConvertView;
        LayoutInflater inflater=LayoutInflater.from(this.context);
        v=inflater.inflate(this.layout,null);
        (((TextView)v.findViewById(R.id.articulo))).setText(names.get(position));
        (((TextView)v.findViewById(R.id.Contador))).setText(MainActivity.crud.getPrecio(position));
        (((TextView)v.findViewById(R.id.codigo_u))).setText(MainActivity.crud.getCodigo(position));
        return v;
    }
}
