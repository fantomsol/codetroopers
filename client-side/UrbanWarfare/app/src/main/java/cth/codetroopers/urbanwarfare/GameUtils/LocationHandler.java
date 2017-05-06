package cth.codetroopers.urbanwarfare.GameUtils;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cth.codetroopers.urbanwarfare.ClientSide.ClientController;
import cth.codetroopers.urbanwarfare.Activities.MainActivity;


/**
 * @author latiif
 * This class is instantiated in MainActivity, and it takes care of grabbing changing in position from the device's gps.
 *
 * This class does also send a change position request to the server when a change is detected.
 *
 * This class does NOT request the local Google Map to be updated. The icon of the oldplayer is changed as a result of the server updating the oldplayer's data including position.
 */

/*
Useful reads:

**Android Studio Documentation on LocationManager class:**
https://developer.android.com/reference/android/location/LocationManager.html

LocationManager is a must-have object that takes care of requesting the device's location-detecting method (most likely gps)

** Android Studio Documentation on LocationListener Interface:**
* https://developer.android.com/reference/android/location/LocationListener.html
*
* LocationListener can listen to LocationManager object and triggers on certain events.
*
* NOTE: You cannot use a LocationListener without first initializing a LocationManager.
* One or more LocationListener instances can listen to the same LocationManager.
 */

public class LocationHandler {
    private LocationManager locationManager;
    private LocationListener locationListener;

    private final AppCompatActivity context;


    /**
     * The constructor takes a Context object as its parameter and initializes the LocationListener and LocationManager objects.
     *
     * @param context the activity that requests LOCATION_SERVICE service.
     */
    public LocationHandler(final AppCompatActivity context) {
        this.context = context;

        /*
        Here we request the device's System Service named LOCATION_SERVICE
        To be granted access to this service we must add certains permissions to our app's manifest file.
         */
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);


        /*
        Here we instantiate a LocationListener interface.
        NOTE: LocationListener is no concrete class, but rather an interface and it must be implemented.
         */
        locationListener = new LocationListener() {
            /**
             * Simple onLocationChanged event, it requests the ConnectionLayer on ClientController to inform the server of the player's new coordinates.
             *
             * @see ClientController#changePosition(Location)
             * @param location The Location parameter is passed by the LocationManager instance we have.
             */
            @Override
            public void onLocationChanged(Location location) {

                ClientController.changePosition(location);
                Log.i("gps","change in location detected");

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(context, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                   1 );

        }else {

        /*
        After initializing our LocationListener interface implementation, we ask the system to send location updates to our locationManager, via the service gps, and every 500 ms, and when the change in location is <= .00001 meters
         */
            Log.i("location-manager", "asking for requests");
            locationManager.requestLocationUpdates("gps", 500, 0.0001f, locationListener);
        }


    }


}
