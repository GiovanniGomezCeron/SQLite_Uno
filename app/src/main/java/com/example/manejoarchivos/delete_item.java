package com.example.manejoarchivos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class delete_item extends AppCompatActivity {
    private int position,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);
        final int bundle = getIntent().getExtras().getInt("id");
        position=getIntent().getExtras().getInt("position");
    }

    public void delete_si(View view) {
        id=Integer.parseInt(getIntent().getExtras().get("id").toString());
        position=Integer.parseInt(getIntent().getExtras().get("pos").toString());

        MainActivity.DB.delete("articulos","codigo="+id,null);
        MainActivity.crud.eliminar(position);

        Toast.makeText(getApplicationContext(),"Operación exitosa",Toast.LENGTH_SHORT).show();

        startActivity(new Intent(delete_item.this,MainActivity.class));
        finish();
    }

    public void close_no(View view) {
       finish();
    }
}
