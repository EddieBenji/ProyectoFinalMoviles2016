package computomovil.fmat.lalo.integratingproject;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

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
    private LatLng current;
    //private Location currentLocation = new Location("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        pond = (Pond) getIntent().getSerializableExtra("pond");
        current = new LatLng(21.059342, -89.6493876);
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
        LatLng pondLocation = new LatLng(pond.getLatitude(), pond.getLongitude());


        /*
        /* ACÁ DEBE DE IR LA LATITUD Y LONGITUD ACTUAL*/
        //LatLng currentPosition = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());

        // Acquire a reference to the system Location Manager

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location currentLocationRead) {
                Log.i("MI LUGAR 2", currentLocationRead.getLatitude() + ", " + currentLocationRead.getLongitude());
                current = new LatLng(currentLocationRead.getLatitude(), currentLocationRead.getLongitude());
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);


        mMap.addMarker(new MarkerOptions()
                .position(pondLocation)
                .snippet(pond.getDescription())
                .title(pond.getName()));

        mMap.addMarker(new MarkerOptions()
                .position(current)
                .title("Tu posición actual"));

        mMap.addPolyline(new PolylineOptions().geodesic(true)
                .color(Color.GRAY)
                .add(pondLocation)
                .add(current)
        );


        mMap.moveCamera(CameraUpdateFactory.newLatLng(pondLocation));

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}
