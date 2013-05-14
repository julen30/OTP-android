package com.example.otp_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

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
}
