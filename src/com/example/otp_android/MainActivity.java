package com.example.otp_android;

import net.cortexx.otp.HmacBasedOneTimePassword;
import net.cortexx.otp.HmacBasedOneTimePassword.Algorithm;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

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
    	System.out.println("HOLA");
    	MySQLiteHelper usdbh =
             new MySQLiteHelper(v.getContext(), "usersdb", null, 1);
    	SQLiteDatabase db = usdbh.getWritableDatabase();
      //Si hemos abierto correctamente la base de datos
      if(db != null)
      {
    	  System.out.println("asdasdas");
    	  System.out.println(center_layout);
    	  System.out.println(((EditText)center_layout.findViewById(R.id.name)));
              String name = ((EditText)center_layout.findViewById(R.id.name)).getText().toString();
              System.out.println(name);
              String pass = ((EditText)center_layout.findViewById(R.id.pass)).getText().toString();
              System.out.println(pass);
              String counter = ((EditText)center_layout.findViewById(R.id.counter)).getText().toString();
              System.out.println(counter);
              //Insertamos los datos en la tabla Usuarios
              db.execSQL("INSERT INTO users (nick,passphrase,counter) " +
                      "VALUES ('" + name + "', '" + pass +"', '" +counter+"')");
          //Cerramos la base de datos
          db.close();
      }
    }

}
