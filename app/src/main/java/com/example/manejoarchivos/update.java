package com.example.manejoarchivos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class update extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        lv=(ListView)findViewById(R.id.l_update);
        if(MainActivity.crud.getdescripcion().size()!=0){
            List_items adapter=new List_items(this,R.layout.activity_update_u,MainActivity.crud.getdescripcion());
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   Intent intent=new Intent(getApplicationContext(),update_item.class);
                   intent.putExtra("Codigo",MainActivity.crud.getCodigo(i));
                   intent.putExtra("Pos",i);
                   startActivity(intent);
                }
            });
        }
        else{
            Toast.makeText(this,"No hay registros",Toast.LENGTH_LONG).show();
        }
    }
    public void cerrar(View view){
        finish();
    }
}
