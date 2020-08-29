package com.example.task;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class MapsMarkerActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerDragListener {
    GoogleMap map;
    Geocoder geocoder;
    Address address;
    LatLng lng;
    Intent val;
    Marker marker;
    FusedLocationProviderClient fusedlocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        // when the map is ready to be used.

        val=getIntent();
        Button button = findViewById(R.id.proceed);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;

        mapFragment.getMapAsync(this);
        geocoder = new Geocoder(this);
        fusedlocation= LocationServices.getFusedLocationProviderClient(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lng!=null)
                {
                    verifyinfo(val.getStringExtra("email"),val.getStringExtra("name"),
                            val.getStringExtra("password"),
                            val.getStringExtra("ph"),
                            MapsMarkerActivity.this,val.getStringExtra("address"),lng);
                }
                else
                {
                    Toast.makeText(MapsMarkerActivity.this,"Location not set",Toast.LENGTH_SHORT).show();;
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setOnMapLongClickListener(this);
        map.setOnMarkerDragListener(this);
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            getloc();
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 001);
        }


    }

    @Override
    public void onMapLongClick(LatLng latLng) {

        lng=latLng;

        try {
            List<Address> adr = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (adr.size() > 0) {
                address = adr.get(0);
                marker.remove();
                marker = map.addMarker(new MarkerOptions()
                         .position(latLng).title(String.valueOf(address.getAddressLine(0))).draggable(true));
            } else {
                marker.remove();
                marker = map.addMarker(new MarkerOptions()
                        .position(latLng).draggable(true));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker markr) {
        try {
            List<Address> adr = geocoder.getFromLocation(markr.getPosition().latitude, marker.getPosition().longitude, 1);
            if (adr.size() > 0) {
                address = adr.get(0);
                marker =map.addMarker(new MarkerOptions()
                        .position(markr.getPosition()).title(String.valueOf(address.getAddressLine(0))).draggable(true));
            } else {
                 marker = map.addMarker(new MarkerOptions()
            .position(markr.getPosition()).draggable(true));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 001) {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getloc();
            } else {
                Toast.makeText(this, "Enabling Permission can get Result", Toast.LENGTH_LONG).show();
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                },001);
            }
        }

    }

    void getloc() {
        map.setMyLocationEnabled(true);
        Task<Location> loc = fusedlocation.getLastLocation();
        loc.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location locati) {
                lng=new LatLng(locati.getLatitude(),locati.getLongitude());
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locati.getLatitude(),locati.getLongitude()),10));
                try {
                    List<Address> adr = geocoder.getFromLocation(locati.getLatitude(), locati.getLongitude(), 1);
                    if (adr.size() > 0) {
                        address = adr.get(0);
                     if(marker!=null)
                     {
                         marker.remove();
                     }
                        marker=map.addMarker(new MarkerOptions()
                                .position(new LatLng(locati.getLatitude(), locati.getLongitude())).title(String.valueOf(address.getAddressLine(0))).draggable(true));
                    } else {
                        if(marker!=null)
                        {
                            marker.remove();
                        }
                        marker=map.addMarker(new MarkerOptions()
                                .position(new LatLng(locati.getLatitude(), locati.getLongitude())).draggable(true));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

   void verifyinfo(final String email, final String name, final String pass, final String ph, final Context c, final String address, final LatLng ln)
    {
        Thread nw  = new Thread(new Runnable() {
            @Override
            public void run() {
                people j = peopleremoteclass.getinstance(c).doe().presentornot(email, pass);
                if(j==null) {
                    people p = new people(name, email, ph, pass,address,Double.valueOf(lng.latitude).toString(),Double.valueOf(lng.longitude).toString());
                    adddata(getApplicationContext(), p);
                }
                else
                {
                    Toast.makeText(MapsMarkerActivity.this,"User Already Present",Toast.LENGTH_SHORT).show();
                    Intent t = new Intent(MapsMarkerActivity.this,signupacti.class);
                    startActivity(t);
                    finish();
                }
            }
        }
        );
        nw.start();

    }

    void adddata(final Context c, final people p) {
        Thread j = new Thread(new Runnable() {
            @Override
            public void run() {
                peopleremoteclass.getinstance(c.getApplicationContext()).doe().insertdata(p);
                List<people> obj = peopleremoteclass.getinstance(c).doe().getelements();
                Intent h = new Intent(c, homescreen.class);
                h.putExtra("obj", (Serializable) obj);
                h.putExtra("objk", (Serializable) p);
                startActivity(h);
                finish();
            }
        });
        j.start();
    }

}