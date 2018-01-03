package cth.codetroopers.pixelwarfare.Model.Skeletons;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lumo on 29/12/17.
 */

public abstract class BeingSkeleton {

        public BeingSkeleton(JSONObject object){
            fetchFromJson(object);
        }

        private void fetchFromJson(JSONObject object){
            try {
                id=object.getString("id");
                avatar=object.getString("avatar");
                geoPos= new LatLng(object.getJSONObject("geoPos").getDouble("latitude"),
                        object.getJSONObject("geoPos").getDouble("longitude"));
                vision= object.getInt("vision");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private String id;
        private String avatar;
        private LatLng geoPos;
        private Integer vision;

        public String getID(){
            return id;
        }

        public String getAvatar() {
            return avatar;
        }

        public LatLng getGeoPos(){
            return geoPos;
        }

        public Integer getVision(){
            return vision;
        }

}
