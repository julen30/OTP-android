package com.example.otp_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StartActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_start, container, false);
        return view;

    }
}
