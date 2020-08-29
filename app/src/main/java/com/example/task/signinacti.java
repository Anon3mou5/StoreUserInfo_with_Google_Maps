package com.example.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Contacts;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.florent37.shapeofview.ShapeOfView;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.List;

public class signinacti extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authenticate);
        final EditText email = findViewById(R.id.emailedit);
        final EditText pass = findViewById(R.id.passedit);
        Button signin = findViewById(R.id.signup);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyinfo(email.getText().toString(),pass.getText().toString(),getApplicationContext());
            }
        });
    }
    void  verifyinfo(final String email, final String pass, final Context c)
    {

        Thread nw  = new Thread(new Runnable() {
            @Override
            public void run() {
                people  p = peopleremoteclass.getinstance(c).doe().presentornot(email,pass);
                if(p == null)
                {
                    Snackbar.make(findViewById(R.id.constraint2),"User-not-found",Snackbar.LENGTH_SHORT).show();
                }
                else
                {    List<people> obj = peopleremoteclass.getinstance(c).doe().getelements();
                    Intent nwe = new Intent(signinacti.this,homescreen.class);
                    nwe.putExtra("objk", (Serializable) p);
                    nwe.putExtra("obj", (Serializable) obj);
                    startActivity(nwe);
                    finish();
                }
            }
        });nw.start();
    }
}
