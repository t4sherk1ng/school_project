package com.example.mybooks.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.example.mybooks.R;

public class LoginFragment extends Fragment {

    private static final String TAG = "myLogs";

    public LoginFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            return inflater.inflate(R.layout.login_fragment, container, false);
        } catch (Exception e) {
            Log.e(TAG, "onCreateView", e);
            throw e;
        }
    }
}