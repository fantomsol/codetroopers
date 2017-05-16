package cth.codetroopers.urbanwarfare.Model.Skeletons;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by latiif on 5/9/17.
 */

/*
"armours":[{"id":1,"name":"Shield of Valor","value":25,"cost":100,"itemType":"armour"}]
 */
public class ArmourSkeleton {


    private String  name;
    private Integer value;
    private Integer id;
    private Integer cost;

    public ArmourSkeleton(JSONObject object){
        try {
            name=object.getString("name");
            value=object.getInt("value");
            id=object.getInt("id");
            cost=object.getInt("cost");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null){
            return false;
        }

        if (obj==this){
            return true;
        }

        if (! (obj instanceof ArmourSkeleton)){
            return false;
        }

        ArmourSkeleton oWeapon = (ArmourSkeleton) obj;

        return (this.getId()==oWeapon.getId());
    }
}
