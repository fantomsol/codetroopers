package cth.codetroopers.urbanwarfare.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 5/10/17.
 */

public class ShopSkeleton {
    private List<ArmourSkeleton> armours= new ArrayList<>();
    private List<WeaponSkeleton> weapons= new ArrayList<>();

    public ShopSkeleton(JSONArray array){
        for (int i=0;i<array.length();i++){
            try {
                JSONObject item=array.getJSONObject(i);

                if (item.getString("itemType").equals("armour")){
                    armours.add(new ArmourSkeleton(item));
                }
                else {
                    weapons.add(new WeaponSkeleton(item));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }



    public List<ArmourSkeleton> getArmours() {
        return armours;
    }

    public List<WeaponSkeleton> getWeapons() {
        return weapons;
    }
}
