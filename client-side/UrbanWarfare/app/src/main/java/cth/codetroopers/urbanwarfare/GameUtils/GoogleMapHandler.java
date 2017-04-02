package cth.codetroopers.urbanwarfare.GameUtils;


import android.content.res.Resources;
import android.location.Location;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import cth.codetroopers.urbanwarfare.R;

/**
 * Created by latiif on 3/30/17.
 */

public class GoogleMapHandler implements OnMapReadyCallback {

    private GoogleMap map;
    private FragmentActivity context;

    public GoogleMapHandler(FragmentActivity context){

        this.context=context;

        SupportMapFragment mapFragment = (SupportMapFragment) context.getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.map = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        map.setMinZoomPreference(20);


        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        

        applyDarkStyle();

    }


    public void goToLocation(Location location){
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(),location.getLongitude())));
    }


    private void applyDarkStyle(){
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            context, R.raw.map_style_dark));

        } catch (Resources.NotFoundException e) {

        }
    }
}
