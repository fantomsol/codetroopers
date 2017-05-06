package cth.codetroopers.urbanwarfare.Model;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cth.codetroopers.urbanwarfare.ClientSide.ClientController;

/**
 * Created by latiif on 5/6/17.
 */

public class PlayerSkeleton {
    public PlayerSkeleton(JSONObject object){
        fetchFromJson(object);
    }

    private void fetchFromJson(JSONObject object){
        try {
            id=object.getString("id");
            hp=object.getDouble("hp");
            rank=object.getString("rank");
            isAlive=object.getBoolean("isAlive");
            isOnline=object.getBoolean("online");
            offlineCooldown=object.getInt("offlineCooldown");
            canGoOffline=object.getBoolean("canGoOffline");
            xp=object.getInt("exp");
            geoPos= new LatLng(object.getJSONObject("geoPos").getDouble("latitude"),
                    object.getJSONObject("geoPos").getDouble("longitude"));
            vision= object.getInt("vision");
            gold= object.getInt("gold");


            JSONArray array;
            array = ClientController.playerInfo.getJSONArray("weapons");

            for (int i=0;i<array.length();i++){
                playersNearby.add(new PlayerSkeleton(array.getJSONObject(i)));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String id;
    private Double hp;
    private String rank;
    private Boolean isAlive;
    private Boolean isOnline;
    private Integer offlineCooldown;
    private Boolean canGoOffline;
    private Integer xp;
    private LatLng geoPos;
    private Integer vision;
    private Integer gold;
    private List<PlayerSkeleton> playersNearby= new ArrayList<>();








    Double getHp(){
        return this.hp;
    }

    String getRank(){
        return this.rank;
    }

    Boolean getIsAlive(){
        return this.isAlive;
    }






    Boolean isOnline(){
        return isOnline;
    }

    Integer getOfflineCooldown(){
        return offlineCooldown;
    }


    Boolean getCanGoOffline(){
        return canGoOffline;
    }

    String getID(){
        return id;
    }

    Integer getExp(){
        return xp;
    }

    LatLng getGeoPos(){
        return geoPos;
    }

    Integer getVision(){
        return vision;
    }

    Integer getGold(){
        return gold;
    }


    List<PlayerSkeleton> getPlayersNearby(){
        return playersNearby;
    }


}
