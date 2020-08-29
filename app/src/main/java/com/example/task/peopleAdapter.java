package com.example.task;


import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class peopleAdapter extends  RecyclerView.Adapter<peopleholder>{

    List<people> modelview ;
    String name,address,email,ph,lat,lng;



    public peopleAdapter(List<people> model) {
        this.modelview = model;

    }
    @NonNull
    @Override
    public peopleholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardui,parent,false);
        return new peopleholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull peopleholder holder, int position) {

        name = modelview.get(position).getName();
        address= modelview.get(position).getAddress();
        email = modelview.get(position).getEmail();
        ph=modelview.get(position).getPhno();
        lat = modelview.get(position).getLat();
        lng = modelview.get(position).getLongi();
            holder.setdata(name,address,email,ph,lat,lng);

    }



    @Override
    public int getItemCount() {
        return modelview.size();
    }
}

class peopleholder extends RecyclerView.ViewHolder {

    TextView name,email,phno,address,location;



    public peopleholder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.Name);
        email= itemView.findViewById(R.id.time);
        address= itemView.findViewById(R.id.address);
        phno= itemView.findViewById(R.id.textView);
        location=itemView.findViewById(R.id.Location);
    }

    public void setdata(final String nameid, String addressid, String emailid,String phid,String lat,String lng)
    {
        name.setText(nameid);
        email.setText(emailid);
        address.setText(addressid);
        phno.setText(phid);
        location.setText(new String("Latitude:"+lat+"\nLongitude: "+ lng));
        phno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phno.getText().toString()));
                itemView.getContext().startActivity(intent);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType(" message/rfc822");
                Intent shareIntent = Intent.createChooser(sendIntent, "Invite through");
                itemView.getContext().startActivity(shareIntent);

            }
        });
    }


}
