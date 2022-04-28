package com.example.seccion_09_maps.Activities;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.seccion_09_maps.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.seccion_09_maps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(15);
        // Add a marker in Sydney and move the camera
        LatLng bqto = new LatLng(10.078760924499175, -69.37431259504389);
        mMap.addMarker(new MarkerOptions()
                .position(bqto)
                .title("Marker in BQTO")
                .draggable(true));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(bqto)
                .zoom(18)       // Max 21
                .bearing(90)    // orientacion de la camara hacia el este (360°)
                .tilt(30)       // Edificios 3D... 0-90 max
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(bqto));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                Toast.makeText(MapsActivity.this, "Clicked on:" +
                        "\n Lat: " + latLng.latitude +
                        "\n Lon: " + latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                Toast.makeText(MapsActivity.this, "Long Clicked on:" +
                        "\n Lat: " + latLng.latitude +
                        "\n Lon: " + latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDrag(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {
                Toast.makeText(MapsActivity.this, "Marker dragged to:" +
                        "\n Lat: " + marker.getPosition().latitude +
                        "\n Lon: " + marker.getPosition().longitude, Toast.LENGTH_SHORT).show();
            }
        });
    }

}