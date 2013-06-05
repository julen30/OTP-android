package com.example.otp_android;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
    View center_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        center_layout=findViewById(R.id.center_layout);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.center_layout, new StartActivity());
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void register(View v) {
        ((TextView)v.findViewById(R.id.register)).setText("Synchronize");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        RegisterActivity registry_fragment = new RegisterActivity();
        transaction.replace(R.id.center_layout, registry_fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void makeSecret(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        OTPActivity otp_fragment = new OTPActivity();
        transaction.add(android.R.id.content, otp_fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void dbHandler(View v){
        MySQLiteHelper usdbh =
                new MySQLiteHelper(v.getContext(), "usersdb", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        boolean insert=((TextView)center_layout.findViewById(R.id.name)).isFocusable();
        //Si hemos abierto correctamente la base de datos
        if(db != null)
        {	if(insert){
	            String name = ((EditText)center_layout.findViewById(R.id.name)).getText().toString();
	            String pass = ((EditText)center_layout.findViewById(R.id.pass)).getText().toString();
	            String counter = ((EditText)center_layout.findViewById(R.id.counter)).getText().toString();
	            //Insertamos los datos en la tabla Usuarios
	            db.execSQL("INSERT INTO users (nick,passphrase,counter) " +
	                    "VALUES ('" + name + "', '" + pass +"', '" +counter+"')");
	            //Cerramos la base de datos
	            db.close();
            }else{
                String name = ((EditText)center_layout.findViewById(R.id.name)).getText().toString();
	            String pass = ((EditText)center_layout.findViewById(R.id.pass)).getText().toString();
	            String counter = ((EditText)center_layout.findViewById(R.id.counter)).getText().toString();
	            ContentValues cv = new ContentValues();
	            cv.put("passphrase", pass);
	            cv.put("counter", counter);
	            db.update("users", cv, null, null);
	            db.close();
            }
        
        }
        
    }

}
