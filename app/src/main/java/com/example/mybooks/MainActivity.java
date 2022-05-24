package com.example.mybooks;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.mybooks.fragment.LoginFragment;
import com.example.mybooks.fragment.MainPageFragment;
import com.example.mybooks.fragment.MessagesFragment;
import com.example.mybooks.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    ProfileFragment profileFragment = new ProfileFragment();
    MainPageFragment mainPageFragment= new MainPageFragment();
    MessagesFragment messagesFragment = new MessagesFragment();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_a, mainPageFragment).commit();
                return true;
            case R.id.messages:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_a, messagesFragment).commit();
                return true;

            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_a, profileFragment).commit();
                return true;
            }
            return false;
        });
        bottomNavigation.setSelectedItemId(R.id.home);
    }
}