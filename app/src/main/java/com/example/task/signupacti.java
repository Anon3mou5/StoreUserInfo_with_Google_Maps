package com.example.task;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jaeger.library.StatusBarUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signupacti extends AppCompatActivity {


    EditText name = null;
    EditText ph = null;
    EditText email = null;
    static  people z;


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

        final TextView indication = findViewById(R.id.passindication);

        final EditText confirmpass = findViewById(R.id.confirmpassedit);


        Button signin = findViewById(R.id.signin);

        ConstraintLayout l = findViewById(R.id.constraint3);

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (count == 0) {
                    indication.setVisibility(View.INVISIBLE);
                } else {
                    indication.setVisibility(View.VISIBLE);
                }
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
                        if (isnmValidMethod(name.getText().toString()) == false) {
                            Snackbar.make(findViewById(R.id.constraint2), "Name can have only alpahbets", Snackbar.LENGTH_SHORT).show();

                        } else {
                            if (pass.getText().toString().equals(confirmpass.getText().toString())) {
                            verifyinfo(email.getText().toString().toLowerCase(),pass.getText().toString(),getApplicationContext());
                            }
                               else {
                                Snackbar.make(findViewById(R.id.constraint2), "password not same", Snackbar.LENGTH_SHORT).show();
                                ;
                            }
                        }

                    }
                }
            }
        });
    }

    public boolean isPhValidMethod(String ph) {
        boolean isValid = false;

        // ^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$
        // ^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^[6-9](?=.*[0-9]).{9}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(ph);

        return matcher.matches();
    }

    public boolean isnmValidMethod(String ph) {
        boolean isValid = false;

        // ^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$
        // ^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$

        Pattern pattern;
        Matcher matcher;
        final String name_PATTERN = "^[a-zA-Z]*$";
        pattern = Pattern.compile(name_PATTERN);
        matcher = pattern.matcher(ph);

        return matcher.matches();
    }

    void adddata(final Context c, final people p) {
//        AsyncTask<people,Void,Void> pr = new AsyncTask<people, Void, Void>() {
////            @Override
////            protected Void doInBackground(people... pe) {
////
////                peopleremoteclass.getinstance(c.getApplicationContext()).doe().insertdata(p);
////            }
////        }
////        pr.execute()
////    }
////}

        Thread j = new Thread(new Runnable() {
            @Override
            public void run() {
                peopleremoteclass.getinstance(c.getApplicationContext()).doe().insertdata(p);
                Intent h = new Intent(c,homescreen.class);
      h.putExtra("obj", (Serializable) p);
                startActivity(h);
                finish();
////            }
            }
        });
        j.start();
    }

     people  verifyinfo(final String email, final String pass, final Context c)
    {

        Thread nw  = new Thread(new Runnable() {
            @Override
            public void run() {
                people j = peopleremoteclass.getinstance(c).doe().presentornot(email, pass);
                if(j==null) {
                    people p = new people(name.getText().toString(), email, ph.getText().toString(), pass);
                    adddata(getApplicationContext(), p);
                }
                else
                {
                    Snackbar.make(findViewById(R.id.constraint2), "User Already Present", Snackbar.LENGTH_SHORT).show();
                }
            }
        }
        );
        nw.start();
        return z;
    }
}