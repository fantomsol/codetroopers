package cth.codetroopers.urbanwarfare.Agents;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by latiif on 4/1/17.
 */

public class Player {

    public String getId() {
        return id;
    }

    public Boolean getOnlineStatus() {
        return onlineStatus;
    }

    public Boolean getCanGoOffline() {
        return canGoOffline;
    }


    public Double getHp() {
        return hp;
    }

    public Integer getArmour() {
        return armour;
    }

    public Integer getXp() {
        return xp;
    }

    public Integer getGold() {
        return gold;
    }

    public Integer getVision() {
        return vision;
    }

    private String id;

    private Boolean onlineStatus;
    private Boolean canGoOffline;

    private Double hp;
    private Integer armour;
    private Integer xp;
    private Integer gold;
    private Integer vision;


    public Player(JSONObject object){
        try {
            this.id=(String) object.get("id");
            this.onlineStatus=(Boolean) object.get("onlineStatus");
            this.canGoOffline=(Boolean) object.get("canGoOffline");

            this.hp= (Double) object.get("hp");
            this.armour=(Integer) object.get("armour");
            this.xp=(Integer) object.get("xp");
            this.gold=(Integer) object.get("gold");

            this.vision=(Integer) object.get("vision");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
