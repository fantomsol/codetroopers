package cth.codetroopers.urbanwarfare.GameUtils;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONException;
import org.json.JSONObject;

import cth.codetroopers.urbanwarfare.Activities.MainActivity;
import cth.codetroopers.urbanwarfare.ClientSide.ClientController;

/**
 * Created by latiif on 4/9/17.
 */

public class AttackOpponentListener implements GoogleMap.OnMarkerClickListener {
    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.i("attack","attack dettected");

        if (marker.getTag()==null){
            //Player is attacking himself(xerself, hahaha, not an SJW - TRUMP 2016)
        }else {
            JSONObject opponent = (JSONObject) marker.getTag();



            String opponentID= null;

            try {
                opponentID= opponent.getString("id");
                ClientController.attack(opponentID);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
