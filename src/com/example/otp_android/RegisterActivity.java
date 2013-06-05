package com.example.otp_android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class RegisterActivity extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.activity_registry, container, false);
        String[] campos = new String[] {"nick", "passphrase","counter"};
        MySQLiteHelper usdbh =
                new MySQLiteHelper(view.getContext(), "usersdb", null, 1);
        SQLiteDatabase db = usdbh.getReadableDatabase();
        Cursor c = db.query("users", campos,null ,null, null, null, null);
        String nick=null;
        if (c.moveToFirst()) {
             do {
                  nick = c.getString(0);
             } while(c.moveToNext());
        }
        if(nick!=null){
        ((TextView)view.findViewById(R.id.name)).setText(nick);
        ((TextView)view.findViewById(R.id.name)).setFocusable(false);
        }
        return view;
    }

    protected void exitFragment() {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.remove(this);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
