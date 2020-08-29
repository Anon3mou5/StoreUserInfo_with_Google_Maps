package com.example.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.jaeger.library.StatusBarUtil;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signupacti extends AppCompatActivity {


    EditText name = null;
    EditText ph = null;
    EditText email = null;
    static people z;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        //    final EditText name = findViewById(R.id.nameedit);
        StatusBarUtil.setTransparent(this);
        ph = findViewById(R.id.phedit);
        email = findViewById(R.id.emailedit);
        name = findViewById(R.id.nameedit);



        // TextView emailindication = findViewById(R.id.emailindication);

        final EditText pass = findViewById(R.id.passedit);

        final EditText address = findViewById(R.id.addressedit);


        final EditText confirmpass = findViewById(R.id.confirmpassedit);

        final Button signin = findViewById(R.id.signin);

        signin.setVisibility(View.GONE);

        ConstraintLayout l = findViewById(R.id.constraint3);

        confirmpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bttonvisibleornot(address,pass,confirmpass,signin);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bttonvisibleornot(address,pass,confirmpass,signin);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bttonvisibleornot(address,pass,confirmpass,signin);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ph.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bttonvisibleornot(address,pass,confirmpass,signin);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bttonvisibleornot(address,pass,confirmpass,signin);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bttonvisibleornot(address,pass,confirmpass,signin);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().length() == 0) {
                    Snackbar snack = Snackbar.make(findViewById(R.id.constraint2), "Email cannot be empty", Snackbar.LENGTH_SHORT);
                    snack.show();
                } else {
                    if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().toLowerCase()).matches()) {
                        Snackbar.make(findViewById(R.id.constraint2), "Inavalid email format", Snackbar.LENGTH_SHORT).show();
                    } else {
                        if (!isPasswordValidMethod(pass.getText().toString())){
                            Snackbar.make(findViewById(R.id.constraint2), "Password must contain a upper,lower letter and a number", Snackbar.LENGTH_SHORT).show();
                        } else {
                            if (pass.getText().toString().equals(confirmpass.getText().toString())) {
                            Intent t = new Intent(signupacti.this,MapsMarkerActivity.class);
                            t.putExtra("name",name.getText().toString() );
                            t.putExtra("ph", ph.getText().toString());
                            t.putExtra("address",address.getText().toString());
                            t.putExtra("email",email.getText().toString());
                            t.putExtra("password",pass.getText().toString());
                                startActivity(t);
                            finish();
                            }
                               else {
                                Snackbar.make(findViewById(R.id.constraint2), "password not same", Snackbar.LENGTH_SHORT).show();
                            }
                        }

                    }
                }
            }
        });
    }

    public boolean isPhValidMethod(String ph) {
        Pattern pattern;
        Matcher matcher;
        final String PHONE_PATTERN = "^[0-9][0-9](?=.*[0-9]).{10}$";
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(ph);
        return matcher.matches();
    }

    public boolean isPasswordValidMethod(String pas) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,17}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(pas);
        return matcher.matches();
    }

    void bttonvisibleornot(EditText address,EditText pass,EditText confirmpass,Button signin)
    {
        if (name.getText().length() >= 4) {
            if (email.getText().length() != 0) {
                if (isPhValidMethod(ph.getText().toString())) {
                    if (Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().toLowerCase()).matches()) {
                        if (address.getText().length() >= 10) {
                            if (isPasswordValidMethod(pass.getText().toString())) {
                                if (pass.getText().toString().equals(confirmpass.getText().toString())) {
                                    signin.setAlpha(0f);
                                    signin.setVisibility(View.VISIBLE);
                                    signin.setAlpha(1f);
                                }  else{
                                    signin.setVisibility(View.GONE);
                                }
                            } else{
                                signin.setVisibility(View.GONE);
                            }
                        }
                        else{
                            signin.setVisibility(View.GONE);
                        }
                    }
                    else{
                        signin.setVisibility(View.GONE);
                    }
                }
                else{
                    signin.setVisibility(View.GONE);
                }
            }
            else{
                signin.setVisibility(View.GONE);
            }
        }
        else{
            signin.setVisibility(View.GONE);
        }
    }


}