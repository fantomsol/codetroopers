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
 * This class serves as a two-way gate that takes care of communication between the android
 * implementation of the game and it's classes, and the remote server
 *
 * This class membder methods and variables are all static, because there is no point in instantiating a single-purpose connection layer.
 *
 * All communication with the server is performed via and by this class
 *
 * @author latiif
 *
 */

/*
Useful reads:

**Android Studio Documentation on JSONObject class:**
https://developer.android.com/reference/org/json/JSONObject.html

JSON is a very useful and quick technique to send and recieve data on serializable objects via any connection medium.

JSON is readable by humans, and supported by our Networking framework (SocketIO)

** SocketIO java implementation**
* https://github.com/socketio/socket.io-client-java
*
* Read the usage part, and keep in mind that SocketIO was designed for Javascript, but this implementation makes it possible to be used in Java.
*
* SocketIO has a built-in support for sending JSON objects, which is something that is excessively used when communicating with the remote server in this module

 */

public class ClientController {

    private static LoadingActivityInterface loadingActivity;
    /**
     * Registers the LoadingActivity of the application, so that it can be triggered on connection-related events that are generated within this class.
     *
     * This static function should be called upon at onCreate event of the app's sole activity that implements LoadingActivityInterface
     *
     * @param activity the loading activity that will be informed on various loading-related events
     */
    public static void subscribeLoadingActivity(LoadingActivityInterface activity){
        loadingActivity=activity;
    }


    /**
     * This static function checks whether the oldplayer is online or offline. Keep in mind that online and offline here correspond the the oldplayer's ability to see and be seen and NOT to whether they're signed in to the server or not.
     *
     * @return <code>true</code> if player is online,and <code>false</code> otherwise
     */
    public static boolean getIsOnline(){
        try {
            return playerInfo.getBoolean("online");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * This staic method is called whenever the user request a change in their online/offline radar status. Whether the request is granted or not is handled on the server and NOT here.
     */

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

    /**
     * The Id of player who's signed in to the server within the game
     * This variable is set when the sign-in process is successfully accomplished.
     */
    public static String playerID;

    /**
     * A JSON object that encapsulates all the data needed about the player, this object's content is dynamically updated by the server and sent to the client.
     *
     * Whenever this class get's a oldplayer-info event from the server and the sent data is that of the current oldplayer, this object is updated.
     * @see ClientController#addListeners()
     */
    public static JSONObject playerInfo;

    /**
     * An array of JSON objects that stores a list of the nearby opponents that can be seen by the player.
     *
     * This list is updated when the class gets an nearby-players-update event
     * @see ClientController#addListeners()
     */
    public static List<JSONObject> opponents=new ArrayList<>();


    /**
     * The client socket that is created for the sole purpose of sending and recieving events from/to the remote server.
     * This object is assigned on Init() method.
     *
     * @see ClientController#Init()
     */
    private static Socket socket;


    /**
     * One-time run method that registers all the events that the client can listen to, every event is added manually in this method, and is called once inside the Init method
     *
     * @see  ClientController#Init()
     */
    private static void addListeners(){
        /**
         * Reacts to a player-info event.
         * If the recieved data is that of the player, they are stored in the local variable playerInfo of the type JSONObject.
         *
         * @see ClientController#playerInfo
         */
        socket.on("player-info", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject msg= (JSONObject) args[0];

                try {
                    if (msg.get("id").equals(playerID)){

                        if (playerInfo==null){
                            /**
                             * Prompts the loadingActivity to react to the newly fetched data of the player.
                             * @see ClientController#loadingActivity
                             */
                            loadingActivity.onDataFetched();
                        }
                        playerInfo= msg;
                        /**
                         * Asks the MainActivity of the game to update its content to reflect the new data of the player
                         */
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


        /**
         * When the list of the neaby players gets updated on the server this event is sent and it contains a list of the new players that can be seen by this player.
         *
         * The sent array is then converted into a list and stored in the opponents list
         *
         * @see ClientController#opponents
         */
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

    /**
     * Creates a client socket and connects to the remote server.
     *
     * It does also add listeners for the events in the addListeners method.
     *
     * @see ClientController#addListeners()
     * @throws URISyntaxException
     *
     */
    public static void Init() throws URISyntaxException {
        /*
        This address the one the emulator uses to connect to localhost on the hosting machine
        If connecting to a remote server online, this URI address needs to be changed
         */
        socket = IO.socket("http://10.0.2.2:3000");


        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("information","CONNECTED");
                /*
                Prompts the loadingActivity that connection is established
                 */
                loadingActivity.onConnected();
            }
        });

        addListeners();
        /*
        If all goes right, goes ahead and connects
         */
        socket.connect();

    }


    /**
     * The method sends a "position-changed" event to the server to update the position of the player
     *
     * @param position The position object that contains the new geographical coordinates of the player. This object is passed directly from the LocationHandler class.
     *
     * @see cth.codetroopers.urbanwarfare.GameUtils.LocationHandler#locationListener
     */
    public static void changePosition(Location position){

        JSONObject object= new JSONObject();

        /*
        Converting the Location object to LatLng object
         */
        LatLng pos= new LatLng(position.getLatitude(),position.getLongitude());

        /*
        Filling in the data required by the event.
        Look at the specifications for this event in the code of the server
         */
        try {
            object.put("id",playerID);
            object.put("lat",pos.latitude);
            object.put("lang",pos.longitude);


            /*
            Sends the actual request as a JSON object
             */
            socket.emit("position-changed",object);

            Log.i("position-changed", String.valueOf(pos.latitude)+" "+pos.longitude);

            /*
            Fetches the updated data of the player from the server
             */
           // requestPlayerInformation(playerID);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method send a sign-in request to the server from the player
     * @param id
     */
    public static void signIn(final String id){
        JSONObject object= new JSONObject();

        try {
            object.put("id",id);

            socket.emit("signin",object);
            requestPlayerInformation(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*
        Prompts loadingActivity that signing in is ongoing
         */
        loadingActivity.onSignedin();

    }

    public static void changeWeapon(WeaponSkeleton weaponSkeleton){
        JSONObject object = new JSONObject();

        try {
            object.put("playerId",playerID);
            object.put("weaponId",weaponSkeleton.getId());

            socket.emit("change-weapon",object);
            Log.i("request","request to change weapon sent");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    /**
     * Send an "attack" event to the server to handle it.
     * NOTE: No logic is processed here, all is done on the server.
     * This event can be sent even if the weapon is on cooldown, it's up to the server to perform the suitable action.
     *
     * @param otherPlayerId The id of the oldplayer that the current oldplayer is going to attack
     */
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

    /**
     * Asks the server to fetch back a JSON object containing all the information about a specific player.
     *
     * Usage: Can be used to either fetch information on the current oldplayer, or when viewing other oldplayer's profile
     *
     * @param id
     */

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
