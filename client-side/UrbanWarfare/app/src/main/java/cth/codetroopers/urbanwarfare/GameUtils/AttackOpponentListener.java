package cth.codetroopers.urbanwarfare.GameUtils;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import cth.codetroopers.urbanwarfare.Model.PlayerSkeleton;
import cth.codetroopers.urbanwarfare.Views.IMainView;

/**
 * @author latiif
 *
 * This class listens to the click events on the markers of the google map, and reacts accordingly
 *
 */

/*
USEFUL Reads:
*Google Maps Api's documentation on .OnMarkerClickListener
https://developers.google.com/android/reference/com/google/android/gms/maps/GoogleMap.OnMarkerClickListener

A click listener is not assigned for every marker on the map, but to the entire map.
 */

public class AttackOpponentListener implements GoogleMap.OnMarkerClickListener {

    private IMainView.MapListener mapListener;

    public AttackOpponentListener(IMainView.MapListener mapListener){
        this.mapListener=mapListener;
    }

    /**
     *
     * @param marker the marker being clicked
     * @return <code>true</code>  because we do NOT wish Google Maps to handle the click event after we do.
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.i("attack","attack dettected");

        if (marker.getTag()==null){
            //A marker with no tag, is that of the player
        }else {
            //Another player is being attacked

            //We grab the tag assigned to the marker

            PlayerSkeleton opponent=(PlayerSkeleton) marker.getTag();

                //Then we go ahead and extrac the opponet's id from their JSON tag.
                String opponentID= opponent.getID();

                //We proceed to inform the server that we are attacking the player with the id we just got
            mapListener.onAttackPlayer(opponentID);

        }


        //As said, we don't wish Google Maps to handle the click event after us
        return true;
    }
}
