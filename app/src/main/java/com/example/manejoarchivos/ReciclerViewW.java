package com.example.manejoarchivos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ReciclerViewW extends AppCompatActivity {
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recicler_view_w);
        rv=(RecyclerView)findViewById(R.id.datos);

        LinearLayoutManager lnm =new LinearLayoutManager(this);
        lnm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(lnm);
        if (MainActivity.crud.getdescripcion().size() != 0) {
            ListAdapter ls=new ListAdapter(MainActivity.crud.getdescripcion(),R.layout.activity_layout__l,this);
            rv.setAdapter(ls);
        }else{
            Toast.makeText(this,"No hay registros",Toast.LENGTH_LONG).show();
        }
    }
    public void cerrar(View view){
        finish();
    }
}
