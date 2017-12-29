package cth.codetroopers.pixelwarfare.GameUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cth.codetroopers.pixelwarfare.Model.Skeletons.GameException;
import cth.codetroopers.pixelwarfare.Model.Skeletons.PlayerSkeleton;
import cth.codetroopers.pixelwarfare.Model.Skeletons.ShopSkeleton;

/**
 * Created by latiif on 5/14/17.
 */

public class  SkeletonFactory {
    public static PlayerSkeleton getPlayer(JSONObject object){
        return new PlayerSkeleton(object);
    }

    public static List<PlayerSkeleton> getPlayers(Object... args){
        List<PlayerSkeleton> players= new ArrayList<PlayerSkeleton>();


        for (Object arg : args) {

            //ConnectivityLayer.opponents.add();
            players.add(new PlayerSkeleton((JSONObject) arg));
        }

        return players;
    }

    public static ShopSkeleton getShop(Object... args){
        JSONArray array= new JSONArray();

        for (Object arg: args){
            array.put((JSONObject)arg);
        }


        return new ShopSkeleton(array);
    }

    public static GameException getGameException(JSONObject object){
        return new GameException(object);
    }

}
