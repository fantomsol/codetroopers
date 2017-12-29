package cth.codetroopers.pixelwarfare.Model.Skeletons;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lumo on 29/12/17.
 */

public class MonsterSkeleton extends CharacterSkeleton {

    public MonsterSkeleton(JSONObject object) {
        super(object);
    }

    private void fetchFromJson(JSONObject object){
            try {
                hp=object.getDouble("hp");
                isAlive=object.getBoolean("isAlive");;
                vision= object.getInt("vision");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private Double hp;
        private Boolean isAlive;
        private Integer vision;


        public Double getHp(){
            return this.hp;
        }

        public Boolean getIsAlive(){
            return this.isAlive;
        }

        public Integer getVision(){
            return vision;
        }

}
