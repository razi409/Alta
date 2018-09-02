package com.altashcools.altaschools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by raziehakbari on 17/03/2018.
 */

public class FragmentSearchList extends Fragment implements OnMapReadyCallback {

    public View v;
    public String type;
    public ArrayList<Post> dataToShow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(type.equals("map")){
            v = inflater.inflate(R.layout.search_map, container, false);
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }else if(type.equals("list")){
          v = inflater.inflate(R.layout.search_list, container, false);
          }
        return v;

    }

    public void setDataToShow(ArrayList<Post> dataToShow){
        this.dataToShow = dataToShow;
    }


    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //this should be set as users cap
        LatLng Rome = new LatLng(41.903820,12.484485);


        for(Post data : dataToShow){
            LatLng place = new LatLng(data.lat,data.longitude);

            googleMap.addMarker(new MarkerOptions().position(place)
                    .title(data.teacherName).snippet("Long Click My Marker..."));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(Rome));

            googleMap.setMinZoomPreference(11);

            googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    Intent intent = new Intent(v.getContext(), ActivityLectureInfo.class);
                    Bundle options = ActivityOptionsCompat.makeScaleUpAnimation(
                            v, 0, 0, v.getWidth(), v.getHeight()).toBundle();


                    ActivityCompat.startActivity(v.getContext(), intent, options);
                }
            });
        }
    }
}
