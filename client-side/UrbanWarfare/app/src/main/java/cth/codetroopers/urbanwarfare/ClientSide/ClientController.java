package cth.codetroopers.urbanwarfare.ClientSide;

import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by latiif on 4/1/17.
 */

public class ClientController {

    private static Socket socket;


    private static void addListeners(){
        socket.on("player-info", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

            }
        });

        socket.on("nearby-players-update", new Emitter.Listener() {
            @Override
            public void call(Object... args) {




                Log.i("nearbyplayers",args.toString());


            }
        });
    }

    public static void Init() throws URISyntaxException {
        socket = IO.socket("http://84.217.63.86:8080");
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("information","CONNECTED");
            }
        });

        addListeners();
        socket.connect();
    }

    public static void changePosition(final String id, Location position){

        JSONObject object= new JSONObject();

        LatLng pos= new LatLng(position.getLatitude(),position.getLongitude());

        try {
            object.put("id","test");
            object.put("lat",pos.latitude);
            object.put("lang",pos.longitude);


            socket.emit("position-changed",object);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void signIn(final String id){
        JSONObject object= new JSONObject();

        try {
            object.put("id",id);

            socket.emit("signin",object);
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
