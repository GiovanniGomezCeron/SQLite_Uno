package com.example.manejoarchivos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Listar_arrays extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_arrays);
        lv=(ListView)findViewById(R.id.li);
        CRUD_DB d=new CRUD_DB();
        d.listar();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,d.getdescripcion());
        lv.setAdapter(adapter);
    }
}
