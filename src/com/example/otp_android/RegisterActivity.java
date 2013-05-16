package com.example.otp_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.database.sqlite.SQLiteDatabase;
public class RegisterActivity extends Fragment{

    private ImageView exit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_registry, container, false);
        exit=((ImageView) view.findViewById(R.id.exit));
        exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                exitFragment();
            }
        });
        return view;
    }

    protected void exitFragment() {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.remove(this);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void dbHandler(View v){
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
       MySQLiteHelper usdbh =
                new MySQLiteHelper(v.getContext(), "usersdb", null, 1);

        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if(db != null)
        {
                String name = ((EditText)v.findViewById(R.id.name)).getText().toString();
                String pass = ((EditText)v.findViewById(R.id.pass)).getText().toString();
                String counter = ((EditText)v.findViewById(R.id.counter)).getText().toString();
                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Usuarios (nombre,passphrase,counter) " +
                        "VALUES ('" + name + "', '" + pass +"', '" +counter+"')");
            //Cerramos la base de datos
            db.close();
        }
    }
}
