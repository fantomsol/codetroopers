package cth.codetroopers.urbanwarfare.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by latiif on 5/2/17.
 */

public class WeaponSkeleton {

    private String name;
    private Integer damage;
    private Integer id;
    private Integer range;

    private Integer cost;

    public String getName() {
        return name;
    }

    public Integer getCost() {
        return cost;
    }


    public Integer getDamage() {
        return damage;
    }

    public Integer getRange() {
        return range;
    }

    public Integer getId(){
        return id;
    }

    public WeaponSkeleton(JSONObject weapon){
        try {

            name=weapon.getString("name");
            damage=weapon.getInt("damage");
            range=weapon.getInt("range");
            id=weapon.getInt("id");
            cost=weapon.getInt("cost");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null){
            return false;
        }

        if (obj==this){
            return true;
        }

        if (! (obj instanceof WeaponSkeleton)){
            return false;
        }

        WeaponSkeleton oWeapon = (WeaponSkeleton) obj;

        return (this.getId()==oWeapon.getId());
    }
}
