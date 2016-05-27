package computomovil.fmat.lalo.integratingproject;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import computomovil.fmat.lalo.integratingproject.model.Pond;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Pond pond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        pond = (Pond) getIntent().getSerializableExtra("pond");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(pond.getLatitude(), pond.getLongitude());

        /* ACÁ DEBE DE IR LA LATITUD Y LONGITUD ACTUAL*/
        LatLng currentPosition = new LatLng(0, 0);

        mMap.addMarker(new MarkerOptions()
                .position(location)
                .snippet(pond.getDescription())
                .title(pond.getName()));
        mMap.addMarker(new MarkerOptions()
                .position(currentPosition)
                .title("Tu posición actual"));

        // Polylines are useful for marking paths and routes on the map.
        mMap.addPolyline(new PolylineOptions().geodesic(true)
                        .color(Color.GRAY)
                        .add(location)
                        .add(currentPosition)
        );


        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}
