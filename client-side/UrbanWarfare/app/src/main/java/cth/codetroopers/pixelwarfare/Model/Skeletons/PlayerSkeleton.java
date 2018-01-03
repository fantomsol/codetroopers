package cth.codetroopers.pixelwarfare.Model.Skeletons;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by latiif on 5/6/17.
 */

public class PlayerSkeleton extends BeingSkeleton {
    public PlayerSkeleton(JSONObject object){
        super(object);
    }

    private void fetchFromJson(JSONObject object){
        try {
            hp=object.getDouble("hp");
            rank=object.getString("rank");
            isAlive=object.getBoolean("isAlive");
            isOnline=object.getBoolean("online");
            offlineCooldown=object.getInt("offlineCooldown");
            canGoOffline=object.getBoolean("canGoOffline");
            xp=object.getInt("exp");
            gold= object.getInt("gold");

            weaponEquipped=new WeaponSkeleton(object.getJSONObject("weaponEquipped"));

            JSONArray array;
            array = object.getJSONArray("weapons");

            for (int i=0;i<array.length();i++){
                weapons.add(new WeaponSkeleton(array.getJSONObject(i)));
            }


            array = object.getJSONArray("armours");

            for (int i=0;i<array.length();i++){
                armours.add(new ArmourSkeleton(array.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public boolean hasWeapon(WeaponSkeleton weapon){
        return weapons.contains(weapon);
    }

    public boolean hasArmour(ArmourSkeleton armour){
        return armours.contains(armour);
    }

    private Double hp;
    private String rank;
    private Boolean isAlive;
    private Boolean isOnline;
    private Integer offlineCooldown;
    private Boolean canGoOffline;
    private Integer xp;
    private Integer gold;
    private WeaponSkeleton weaponEquipped;
    private List<WeaponSkeleton> weapons = new ArrayList<>();
    private List<ArmourSkeleton> armours = new ArrayList<>();



    public Double getHp(){
        return this.hp;
    }

    public String getRank(){
        return this.rank;
    }

    public Boolean getIsAlive(){
        return this.isAlive;
    }

    public Boolean isOnline(){
        return isOnline;
    }

    public Integer getOfflineCooldown(){
        return offlineCooldown;
    }

    public Boolean getCanGoOffline(){
        return canGoOffline;
    }

    public Integer getExp(){
        return xp;
    }

    public Integer getGold(){
        return gold;
    }

    public WeaponSkeleton getWeaponEquipped(){
        return weaponEquipped;
    }

    public List<WeaponSkeleton> getWeapons(){
        return weapons;
    }


}
