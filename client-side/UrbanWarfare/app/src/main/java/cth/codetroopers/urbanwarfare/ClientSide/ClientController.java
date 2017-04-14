package cth.codetroopers.urbanwarfare.ClientSide;

import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import cth.codetroopers.urbanwarfare.Activities.MainActivity;
import cth.codetroopers.urbanwarfare.GameUtils.GoogleMapHandler;
import cth.codetroopers.urbanwarfare.LoadingActivityInterface;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by latiif on 4/1/17.
 */

public class ClientController {

    private static LoadingActivityInterface loadingActivity;
    public static void subscribeLoadingActivity(LoadingActivityInterface activity){
        loadingActivity=activity;
    }

    public static boolean getIsOnline(){
        try {
            return playerInfo.getBoolean("online");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static void requestChangeRadarStatus(){
        JSONObject object= new JSONObject();




        try {
            object.put("playerId",playerID);
            object.put("wantToGoOnline",!getIsOnline());

        } catch (JSONException e) {
            e.printStackTrace();
        }



        socket.emit("change-radar-status",object);
    }

    public static String playerID;
    public static JSONObject playerInfo;

    public static List<JSONObject> opponents=new ArrayList<>();

    private static Socket socket;


    private static void addListeners(){
        socket.on("player-info", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject msg= (JSONObject) args[0];

                try {
                    if (msg.get("id").equals(playerID)){

                        if (playerInfo==null){
                            loadingActivity.onDataFetched();
                        }
                        playerInfo= msg;
                        MainActivity.updateGUI();
                        if (MainActivity.googleMapHandler!=null) {
                            MainActivity.googleMapHandler.pinPlayer();
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



        socket.on("nearby-players-update", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("nearby", String.valueOf( args.length));

                ClientController.opponents.clear();
                for (int i=0;i<args.length;i++){

                    ClientController.opponents.add((JSONObject) args[i]);
                }

                MainActivity.googleMapHandler.pinOpponents();
            }
        });
    }

    public static void Init() throws URISyntaxException {
        socket = IO.socket("http://10.0.2.2:3000");


        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("information","CONNECTED");
                loadingActivity.onConnected();
            }
        });

        addListeners();
        socket.connect();

    }


    public static void changePosition(Location position){

        JSONObject object= new JSONObject();

        LatLng pos= new LatLng(position.getLatitude(),position.getLongitude());

        try {
            object.put("id",playerID);
            object.put("lat",pos.latitude);
            object.put("lang",pos.longitude);


            socket.emit("position-changed",object);
            requestPlayerInformation(playerID);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void signIn(final String id){
        JSONObject object= new JSONObject();

        try {
            object.put("id",id);

            socket.emit("signin",object);
            requestPlayerInformation(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        loadingActivity.onSignedin();

    }

    public static void attack(final String otherPlayerId){

        JSONObject object= new JSONObject();

        try {
            object.put("id",playerID);
            object.put("oId",otherPlayerId);
            socket.emit("attack",object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void requestPlayerInformation(final String id){
        JSONObject object= new JSONObject();


        try {
            object.put("id",id);
            socket.emit("get-player-info",object);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
