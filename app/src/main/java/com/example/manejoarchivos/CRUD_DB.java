package com.example.manejoarchivos;
import android.database.Cursor;
import android.view.View;

import java.util.ArrayList;

public class CRUD_DB {
    private ArrayList<String> codigo;
    private ArrayList<String> descripcion;
    private ArrayList<String> precio;

    public String getCodigo(int position) {
        return codigo.get(position);
    }

    public void setCodigo(ArrayList<String> codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion(int position) {
        return descripcion.get(position);
    }

    public void setDescripcion(ArrayList<String> descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio(int position) {
        return precio.get(position);
    }

    public void setPrecio(ArrayList<String> precio) {
        this.precio = precio;
    }

    public void  listar(){
        this.descripcion=new ArrayList<String>();
        this.codigo=new ArrayList<String>();
        this.precio=new ArrayList<String>();
       Cursor datos=MainActivity.DB.rawQuery("select codigo,descripcion, pecio from articulos",null);
       while(datos.moveToNext()){
        codigo.add(datos.getString(0));
         descripcion.add(datos.getString(1));
       precio.add(datos.getString(2));
       }
    }
    public ArrayList<String> getdescripcion(){
        return this.descripcion;
    }

    public void eliminar(int position){
      this.precio.remove(position);
      this.codigo.remove(position);
      this.descripcion.remove(position);
    }
    public void actualizar(int position, String cod,String des, String precio) {
        this.codigo.set(position,cod);
        this.descripcion.set(position,des);
        this.precio.set(position,precio);
    }
}
