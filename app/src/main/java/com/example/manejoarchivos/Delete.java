package com.example.manejoarchivos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Delete extends AppCompatActivity{
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        lv=(ListView)findViewById(R.id.consulta);
        //VERIFICAR SI HAY DATOS EN LA BD
        if(MainActivity.crud.getdescripcion().size()!=0){
                List_items adapter=new List_items(this,R.layout.activity_list_items,MainActivity.crud.getdescripcion());
                lv.setAdapter(adapter);
        }else{
           Toast.makeText(this,"No hay registros",Toast.LENGTH_LONG).show();
        }
        //colocando evento a la lista
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Delete.this, delete_item.class);
               intent.putExtra("id",Integer.parseInt(MainActivity.crud.getCodigo(i)));
               intent.putExtra("pos",i);
               startActivity(intent);
            }
        });
    }
    public void cerrar(View view){
        finish();
    }
}
