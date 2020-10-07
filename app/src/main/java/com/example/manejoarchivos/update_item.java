package com.example.manejoarchivos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class update_item extends AppCompatActivity {
    private int id, position;
    private EditText co,des,pri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        //obteniendo elementos por su id
        co=(EditText)findViewById(R.id.id_new);
        des=(EditText)findViewById(R.id.des_new);
        pri=(EditText)findViewById(R.id.price_new);

        //obteniedo parametros de la actividad
        id=Integer.parseInt(getIntent().getExtras().get("Codigo").toString());
        position=Integer.parseInt(getIntent().getExtras().get("Pos").toString());

        //creando la consulta
        Cursor datos= MainActivity.DB.rawQuery("select codigo, descripcion, pecio from articulos where codigo="+id,null);
        datos.moveToFirst();
        co.setText(String.valueOf(datos.getInt(0)));
        des.setText(datos.getString(1));
        pri.setText(String.valueOf(datos.getDouble(2)));
    }

    public void actualizar(View view){
        String cod=co.getText().toString();
        String des=this.des.getText().toString();
        String pre=pri.getText().toString();

        ContentValues content=new ContentValues();

        content.put("codigo",Integer.parseInt(cod));
        content.put("descripcion",des);
        content.put("pecio",Double.parseDouble(pre));

       MainActivity.DB.update("articulos",content,"codigo="+id,null);
       MainActivity.crud.actualizar(position,cod,des,pre );

       startActivity(new Intent(this,update.class));
       finish();
    }

}
