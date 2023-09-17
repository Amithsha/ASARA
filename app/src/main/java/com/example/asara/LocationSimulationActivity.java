package com.example.asara;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;

public class LocationSimulationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker sourceMarker;
    private Marker destinationMarker;
    private Polyline routePolyline;
    private List<LatLng> routePoints;
    private Handler handler;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_simulation);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        handler = new Handler();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Apply a custom map style if needed
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style));

        // Add a vehicle marker for the source location (example)
        LatLng sourceLatLng = new LatLng(13.008714932046635, 77.71410952182663);
        BitmapDescriptor vehicleIcon = BitmapDescriptorFactory.fromResource(R.drawable.ambulance_pic);
        BitmapDescriptor patientIcon = BitmapDescriptorFactory.fromResource(R.drawable.patient);
        sourceMarker = mMap.addMarker(new MarkerOptions()
                .position(sourceLatLng)
                .title("Source")
                .icon(vehicleIcon));

        // Add a marker for the destination location (example)
        LatLng destinationLatLng = new LatLng(12.898563, 77.668153);
        destinationMarker = mMap.addMarker(new MarkerOptions()
                .position(destinationLatLng)
                .title("Destination")
                .icon(patientIcon));

        // Move the camera to the center of the route (example)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sourceLatLng, 12));

        // Create a list of LatLng points that define your route (example)
        routePoints = new ArrayList<>();
        routePoints.add(sourceLatLng); // Add source
        routePoints.add(new LatLng(13.009601, 77.714401));
        routePoints.add(new LatLng(13.008149, 77.711075));
        routePoints.add(new LatLng(13.009704, 77.709801));
        routePoints.add(new LatLng(13.019428, 77.708447));
        routePoints.add(new LatLng(13.015455, 77.701032));
        routePoints.add(new LatLng(13.001243, 77.676965));
        routePoints.add(new LatLng(13.000674, 77.679200));
        routePoints.add(new LatLng(12.943793, 77.697878));
        routePoints.add(new LatLng(12.928363, 77.682071));
        routePoints.add(new LatLng(12.920978, 77.666105));
        routePoints.add(new LatLng(12.919656, 77.667242));
        routePoints.add(new LatLng(12.918847, 77.668729));
        routePoints.add(new LatLng(12.910009, 77.662950));
        routePoints.add(new LatLng(12.905820, 77.662980));
        routePoints.add(new LatLng(12.901932, 77.660507));
        routePoints.add(new LatLng(12.900680, 77.663305));



                                                                                // Add intermediate points as needed
        routePoints.add(destinationLatLng); // Add destination

        // Draw the polyline to represent the route
        PolylineOptions polylineOptions = new PolylineOptions()
                .addAll(routePoints)
                .width(8) // Width of the polyline
                .color(Color.BLUE); // Color of the polyline

        routePolyline = mMap.addPolyline(polylineOptions);

        // Start moving the source marker
        moveSourceMarker();
    }

    private void moveSourceMarker() {
        if (currentIndex < routePoints.size() - 1) {
            LatLng startPosition = routePoints.get(currentIndex);
            LatLng endPosition = routePoints.get(currentIndex + 1);
            long duration = 3000; // Adjust the duration as needed

            final long startTime = SystemClock.uptimeMillis();
            final LatLng finalEndPosition = endPosition;
            final Handler localHandler = handler;

            localHandler.post(new Runnable() {
                @Override
                public void run() {
                    long elapsed = SystemClock.uptimeMillis() - startTime;
                    float t = elapsed / (float) duration;
                    if (t < 1) {
                        double lat = t * finalEndPosition.latitude + (1 - t) * startPosition.latitude;
                        double lng = t * finalEndPosition.longitude + (1 - t) * startPosition.longitude;
                        sourceMarker.setPosition(new LatLng(lat, lng));
                        localHandler.postDelayed(this, 16);
                    } else {
                        currentIndex++;
                        moveSourceMarker();
                    }
                }
            });
        }
    }
}
