package com.example.task;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class homeacti extends Fragment{


    boolean isloaded=false;
    private static final String ARG_SECTION_NUMBER = "section_number";
    people obj;
    private PageViewModel pageViewModel;


    public homeacti(people obj) {
        this.obj = obj;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.cardui, container, false);
        final TextView phno = v.findViewById(R.id.textView);
        TextView email = v.findViewById(R.id.time);
        phno.setText(obj.getPhno());
        email.setText(obj.getEmail());
        phno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phno.getText().toString()));
                startActivity(intent);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                //  sendIntent.putExtra(Intent.EXTRA_TEXT, "Lets,chat on Conv0,a one stop solution for small,secure,fast and cloud messaging.\nMoreover its simple and 6mb only.\nDownload it now at : https://www.dropbox.com/sh/xfnqdwm6seplzdp/AABzJAu9s-KvaIfb642N3IPRa?dl=0 ");
                sendIntent.setType(" message/rfc822");
                Intent shareIntent = Intent.createChooser(sendIntent, "Invite through");
                startActivity(shareIntent);

            }
        });
        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }
}
