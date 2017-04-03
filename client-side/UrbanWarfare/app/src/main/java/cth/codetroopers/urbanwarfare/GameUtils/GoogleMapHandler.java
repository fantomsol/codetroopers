package cth.codetroopers.urbanwarfare.GameUtils;


import android.content.res.Resources;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cth.codetroopers.urbanwarfare.ClientSide.ClientController;
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

        map.setMinZoomPreference(22);
        map.setMaxZoomPreference(22);

        Double lat=0.0,lng=0.0;
        try {

            lat=(Double) ClientController.playerInfo.getJSONObject("geoPos").get("latitude");
            lng= (Double) ClientController.playerInfo.getJSONObject("geoPos").get("longitude");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        LatLng pos=new LatLng(lat,lng);

        playerMarker= map.addMarker(new MarkerOptions().title("Player").position(pos).icon(BitmapDescriptorFactory.fromResource(R.drawable.player)));

      //  map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        

        applyDarkStyle();

    }


    public void goToLocation(Location location){
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(),location.getLongitude())));
        pinPlayer();
        Log.i("camera","change location to "+location.getLatitude()+":"+location.getLongitude());
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

    private List<Marker> opponentsMarkers= new ArrayList<>();
    public void pinOpponents(){

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                for (Marker marker:opponentsMarkers){
                    marker.remove();
                }
                opponentsMarkers.clear();

                for (JSONObject opponent:ClientController.opponents){
                    Double lat=0.0,lng=0.0;
                    String op_id="Secret";
                    try {

                        lat=(Double) opponent.getJSONObject("geoPos").get("latitude");
                        lng= (Double) opponent.getJSONObject("geoPos").get("longitude");
                        op_id=(String) opponent.get("id");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    LatLng pos=new LatLng(lat,lng);

                    opponentsMarkers.add(map.addMarker(new MarkerOptions().position(pos).title(op_id)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.opponent))));
                }

            }
        });


    }

    private Marker playerMarker;
    private void pinPlayer(){

        Double lat=0.0,lng=0.0;
        try {

            lat=(Double) ClientController.playerInfo.getJSONObject("geoPos").get("latitude");
            lng= (Double) ClientController.playerInfo.getJSONObject("geoPos").get("longitude");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        LatLng pos=new LatLng(lat,lng);


        playerMarker.setPosition(pos);
        Log.i("camera","change location to "+lat+":"+lng);

    }



}
