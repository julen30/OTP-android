package com.example.otp_android;

import com.example.otp_android.HOTP.AlgorithmType;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class OTPActivity extends Fragment{
    ImageView exit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
    super.onCreate(savedInstanceState);
    View view = inflater.inflate(R.layout.activity_otp, container, false);
    exit=((ImageView) view.findViewById(R.id.exit));
    exit.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            exitFragment();
        }
    });
    String[] campos = new String[] {"nick", "passphrase","counter"};
    String[] args = new String[] {"magufo"};
	MySQLiteHelper usdbh =
            new MySQLiteHelper(view.getContext(), "usersdb", null, 1);
	SQLiteDatabase db = usdbh.getReadableDatabase();
    Cursor c = db.query("users", campos,null ,null, null, null, null);
    //Nos aseguramos de que existe al menos un registro
    String nick=null;
    String passphrase=null;
    int counter=0;
    if (c.moveToFirst()) {
         //Recorremos el cursor hasta que no haya más registros
         do {
              nick = c.getString(0);
              passphrase = c.getString(1);
              counter=c.getInt(2);
         } while(c.moveToNext());
    }
    HOTP hotp=new HOTP(AlgorithmType.SHA1,6,passphrase.getBytes());
    int otp=hotp.generateHTOPPassword(counter);
    ((TextView)view.findViewById(R.id.user_name)).setText(nick);
    ((TextView)view.findViewById(R.id.Secret)).setText(otp+"");
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
