package cth.codetroopers.urbanwarfare.GameUtils;


import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
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
 * <p>
 * This class manages all the marker-placement requests onto the map.
 * It implements OnMapReadyCallback interface.
 * <p>
 * It's passed a GoogleMap object in its onMapReady(...) method
 */


/*
USEFUL READS:
THE GOOGLE MAPS API
https://developers.google.com/maps/documentation/android-api/map

DO READ THE WHOLE THING
 */
public class GoogleMapHandler implements OnMapReadyCallback {

    private GoogleMap map;
    private FragmentActivity context;


    public GoogleMapHandler(FragmentActivity context) {

        this.context = context;

        SupportMapFragment mapFragment = (SupportMapFragment) context.getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    }

    /**
     * This method is called, when the map is up and running, so that we can modify it.
     * @param googleMap the instance of GoogleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


        this.map = googleMap;


        //Set a fixed zoom value (Needs to be revised when we set the constants for the game)
        map.setMinZoomPreference(18);
        map.setMaxZoomPreference(18);

        //We create the first instance of the playerMarker, a marker that will always exist throughout the lifetime of the app.
        playerMarker =
                map.addMarker(
                        new MarkerOptions()
                                .title("Player")
                                .position(new LatLng(0, 0))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.player)));

        //needs to be revised to decide the most visually appealing map type
       // map.setMapType(GoogleMap.MAP_TYPE_HYBRID);


        //Are we gonna use dark theme?
        applyDarkStyle();


        /**
         * Here we set the context for the generator
         * @see OpponentIconGenerator#setContext(Context)
         */
        OpponentIconGenerator.setContext(this.context);


        //Ask our class AttackOpponentListener to listen to all click events on markers
        map.setOnMarkerClickListener(new AttackOpponentListener());


    }

    private void applyDarkStyle() {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            context, R.raw.map_style_dark));

        } catch (Resources.NotFoundException e) {

        }
    }

    /**
     * A list that contains the markers of all the other people/things that are not this player
     */
    private List<Marker> opponentsMarkers = new ArrayList<>();

    /**
     * Here we take all the opponents in the ClientController class and place them as markers on the map
     */
    public void pinOpponents() {


        /*
        The following two lines are used to do all visual operations on the thread of the application UI thread and not on some other thread i.e. that of ClientController.
         */
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                /*
                Iterating through the already existing markers, and taking them off the map
                 */
                for (Marker marker : opponentsMarkers) {
                    marker.remove();
                }
                //We clear the array from the removed markers
                opponentsMarkers.clear();

                /*
                For every JSON object we extract the data we want and pass it to the OpponentIconGenerator and place them on the map, and in the list opponentsMarkers
                 */
                for (JSONObject opponent : ClientController.opponents) {
                    Double lat = 0.0, lng = 0.0;
                    String op_id = "Secret";
                    Integer hp = 100;
                    BitmapDescriptor descriptor = BitmapDescriptorFactory.defaultMarker();
                    try {

                        lat = (Double) opponent.getJSONObject("geoPos").get("latitude");
                        lng = (Double) opponent.getJSONObject("geoPos").get("longitude");

                        descriptor = OpponentIconGenerator.getInstance().generateFromPlayer(opponent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    LatLng pos = new LatLng(lat, lng);


                    Marker marker = map.addMarker(new MarkerOptions().position(pos).title(op_id)
                            .icon(descriptor));

                    //Since this marker is that of an opponent, it's assigned a JSON-object tag, that would be useful in AttackOpponentListener.
                    marker.setTag(opponent);

                    //We go ahead and place the marker on the map
                    opponentsMarkers.add(marker);
                }

            }
        });


    }

    private Marker playerMarker;

    /**
     * Same thing happens here as in pinOpponents() method, apart from the fact that we place a simple marker and not a one generated from OpponentIconGenerator.
     */
    public void pinPlayer() {

        Double lat = 0.0, lng = 0.0;
        try {

            lat = (Double) ClientController.playerInfo.getJSONObject("geoPos").get("latitude");
            lng = (Double) ClientController.playerInfo.getJSONObject("geoPos").get("longitude");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final LatLng pos = new LatLng(lat, lng);


        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                playerMarker.setPosition(pos);
                map.moveCamera(CameraUpdateFactory.newLatLng(pos));

            }
        });

        Log.i("player", "change location to " + lat + ":" + lng);

    }


}
