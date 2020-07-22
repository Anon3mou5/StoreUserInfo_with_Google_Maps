package com.example.task;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class adaptermodel extends  RecyclerView.Adapter<viewholder>{

    List<data> modelview ;
    String heading,url,title,date,time;
    String web;


    public adaptermodel(List<data> model) {
        this.modelview = model;

    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.modellay,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        title = modelview.get(position).getTitle();
        url = modelview.get(position).getThumbnail();
        web = modelview.get(position).getId();
        try {
            holder.setdata(url,title,web);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public int getItemCount() {
        return modelview.size();
    }
}

class viewholder extends RecyclerView.ViewHolder {

    TextView heading;
    TextView title;
    TextView id;

    ImageView thumbnail;
    ConstraintLayout l ;

    Button edit, delete;


    public viewholder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.titleedit);
         id = itemView.findViewById(R.id.idedit);
       thumbnail = itemView.findViewById(R.id.thumbnail);
        l = itemView.findViewById(R.id.consti);
    }

    public void setdata(final String url, String titles, String web) throws IOException {

        title.setText(titles);
        id.setText(web);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.mipmap.pgnotfound);

    //    Glide.with(itemView.getContext()).load(url).apply(options).into(thumbnail);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URL urli;
                try {
                    urli = new URL("https://cdn.dnaindia.com/sites/default/files/styles/full/public/2019/08/11/858095-govinda-081219.jpg");
                  final  Bitmap bmp = BitmapFactory.decodeStream(urli.openConnection().getInputStream());
                    ((Activity)itemView.getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            thumbnail.setImageBitmap(bmp);
                        }
                    });

                } catch (MalformedURLException e) {
                    ((Activity)itemView.getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap icon = BitmapFactory.decodeResource(itemView.getContext().getResources(),
                                    R.mipmap.pgnotfound);
                            thumbnail.setImageBitmap(icon);

                        }
                    });
                    e.printStackTrace();

                    Log.d("EXCeption",e.toString());
                } catch (IOException e) {
                    Bitmap icon = BitmapFactory.decodeResource(itemView.getContext().getResources(),
                            R.mipmap.pgnotfound);
                    thumbnail.setImageBitmap(icon);

                    e.printStackTrace();
                    Log.d("EXCeption",e.toString());
                }
            }
        });
        t.start();


            }


    }
