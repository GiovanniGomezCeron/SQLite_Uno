package com.example.manejoarchivos;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText codigo,descripcion,precio;
    String cod;
    String des;
    String pre;
    public static Database db;
    public static SQLiteDatabase DB;
    public static CRUD_DB crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db=new Database(this,"Admin",null,1);
        DB=db.getWritableDatabase();
        codigo=(TextInputEditText)findViewById(R.id.codigo);
        descripcion=(TextInputEditText)findViewById(R.id.descripcion);
        precio=(TextInputEditText)findViewById(R.id.precio);
        crud=new CRUD_DB();
        crud.listar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void Insert(View view){
        if(VerificarVacios(0,2)){
            ContentValues cv=new ContentValues();
            cv.put("codigo",Double.parseDouble(cod));
            cv.put("descripcion",des);
            cv.put("pecio",Double.parseDouble(pre));
            DB.insert("articulos",null,cv);
            Toast.makeText(this,"Operación exitosa",Toast.LENGTH_LONG).show();
            descripcion.setText("");
            precio.setText("");
            codigo.setText("");
            crud.listar();
        }
    }
    public void SelectCodigo(View view){
        try{
            if(VerificarVacios(0,0)){
                Cursor query=DB.rawQuery("select descripcion,pecio from articulos where codigo="+Integer.parseInt(cod),null);
                if(query.moveToFirst()){
                    descripcion.setText(query.getString(0));
                    precio.setText(query.getString(1));
                }else{
                    Toast.makeText(this,"No hay articulos con el código",Toast.LENGTH_LONG).show();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ConsultaDescripcio(View view){
        if(VerificarVacios(1,1)){
            Cursor query=DB.rawQuery("select codigo, pecio from articulos where descripcion="+"'"+des+"'",null);
            if(query.moveToFirst()){
                codigo.setText(query.getString(0));
                precio.setText(query.getString(1));
            }else{
                Toast.makeText(this,"No hay articulos con la descripción",Toast.LENGTH_LONG).show();
            }
        }

    }
    public void Delete(View view){

        startActivity(new Intent(this,Delete.class));
    }
    public void Update(View view){
        startActivity(new Intent(this,update.class));
    }

    public boolean VerificarVacios(int desde,int hasta){
        cod=codigo.getText().toString();
        des=descripcion.getText().toString();
        pre=precio.getText().toString();
        EditText[]ets={codigo,descripcion,precio};
        for(int contador=desde; contador<=hasta; contador++){
            if (ets[contador].getText().toString().isEmpty()) {
                ets[contador].requestFocus();
                ets[contador].setError("Rellene campo");
                return false;
            }
        }
        return true;
    }
    public void RV(View view){
        startActivity(new Intent(this,ReciclerViewW.class));
    }

    public void exportDatabse(View view) throws IOException {
        File sdcard=Environment.getExternalStorageDirectory();
        File file=Environment.getDataDirectory();

        File cur=new File(file,"/data/"+getPackageName()+"/databases/Admin");
        File bac=new File(sdcard,"");

        Toast.makeText(this, String.valueOf(cur), Toast.LENGTH_LONG).show();

        FileChannel src=new FileInputStream(cur).getChannel();
        FileChannel dst=new FileOutputStream(bac).getChannel();
        dst.transferFrom(src,0,src.size());
           src.close();
           dst.close();

        /*try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            Toast.makeText(this, sd.toString(), Toast.LENGTH_LONG).show();
            if (sd.canWrite()) {
                String currentDBPath = "/data/" + getPackageName() + "/databases/Admin.db";
                String backupDBPath = "backupname.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {

        }*/
    }
    public void copyFile(View view ) {
        String inputPath = String.valueOf(Environment.getDataDirectory())+"/data/"+getPackageName()+"/databases/";
        String outputPath=String.valueOf(getExternalFilesDir("DBBackup"));
        String inputFile ="Admin";
        InputStream in = null; OutputStream out = null;
        try {
             File dir = new File (outputPath);
             if (!dir.exists()) {
                 dir.mkdirs(); }
                 in = new FileInputStream(inputPath + inputFile);
                out = new FileOutputStream(outputPath + inputFile);
                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1)
                { out.write(buffer, 0, read); }
                in.close();
                in = null;
               out.flush();
               out.close();
               out = null; }
        catch (FileNotFoundException fnfe1) {
            Log.e("tag", fnfe1.getMessage());
        }
        catch (Exception e) { Log.e("tag", e.getMessage());
        }
        Toast.makeText(this,"Operación exitosa",Toast.LENGTH_LONG).show();
    }
  }

