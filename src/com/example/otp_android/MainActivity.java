package com.example.otp_android;

import net.cortexx.otp.HmacBasedOneTimePassword;
import net.cortexx.otp.HmacBasedOneTimePassword.Algorithm;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;

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
        transaction.add(android.R.id.content, registry_fragment);
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

}
