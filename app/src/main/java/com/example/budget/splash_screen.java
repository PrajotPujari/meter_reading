package com.example.budget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splash_screen extends AppCompatActivity {

    private static final int SPLASH_IME = 4000;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);



        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user != null){
                    //user is signed in
                    Intent intent=new Intent(splash_screen.this , MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    //user is not signed in
                    Intent intent=new Intent(splash_screen.this , sign_in.class);
                    startActivity(intent);
                    finish();
                }
            }

        },SPLASH_IME);





    }
}