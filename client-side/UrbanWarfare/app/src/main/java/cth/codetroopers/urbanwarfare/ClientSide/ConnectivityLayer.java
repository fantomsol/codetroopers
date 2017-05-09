package cth.codetroopers.urbanwarfare.ClientSide;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import cth.codetroopers.urbanwarfare.Model.ClientModel;
import cth.codetroopers.urbanwarfare.Model.PlayerSkeleton;
import cth.codetroopers.urbanwarfare.Model.WeaponSkeleton;
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

public class ConnectivityLayer {

    /**
     * This static function checks whether the oldplayer is online or offline. Keep in mind that online and offline here correspond the the oldplayer's ability to see and be seen and NOT to whether they're signed in to the server or not.
     *
     * @return <code>true</code> if player is online,and <code>false</code> otherwise
     */
    public  boolean getIsOnline(){
            return ClientModel.mPlayer.isOnline();
    }

    /**
     * This staic method is called whenever the user request a change in their online/offline radar status. Whether the request is granted or not is handled on the server and NOT here.
     */

    public  void requestChangeRadarStatus(){
        JSONObject object= new JSONObject();

        try {
            object.put("playerId",ClientModel.playerID);
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
    public  String playerID;

    /**
     * A JSON object that encapsulates all the data needed about the player, this object's content is dynamically updated by the server and sent to the client.
     *
     * Whenever this class get's a oldplayer-info event from the server and the sent data is that of the current oldplayer, this object is updated.
     * @see ConnectivityLayer#addListeners()
     */
    public  JSONObject playerInfo;

    /**
     * An array of JSON objects that stores a list of the nearby opponents that can be seen by the player.
     *
     * This list is updated when the class gets an nearby-players-update event
     * @see ConnectivityLayer#addListeners()

    /**
     * The client socket that is created for the sole purpose of sending and recieving events from/to the remote server.
     * This object is assigned on Init() method.
     *
     * @see ConnectivityLayer#Init()
     */
    private  Socket socket;


    /**
     * One-time run method that registers all the events that the client can listen to, every event is added manually in this method, and is called once inside the Init method
     *
     * @see  ConnectivityLayer#Init()
     */
    private  void addListeners(){
        /**
         * Reacts to a player-info event.
         * If the recieved data is that of the player, they are stored in the local variable playerInfo of the type JSONObject.
         *
         * @see ConnectivityLayer#playerInfo
         */
        socket.on("player-info", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject msg= (JSONObject) args[0];

                if (msg==null){
                    return;
                }
                try {
                    if (msg.get("id").equals(ClientModel.playerID)){
                        if (ClientModel.mPlayer==null){
                            ClientModel.onDataFetched();
                        }
                        ClientModel.onPlayerDataRecieved(new PlayerSkeleton(msg));
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
         * @see ConnectivityLayer#opponents
         */
        socket.on("nearby-players-update", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("nearby", String.valueOf( args.length));

                List<PlayerSkeleton> playersNearby= new ArrayList<PlayerSkeleton>();


                for (Object arg : args) {

                    //ConnectivityLayer.opponents.add();
                    playersNearby.add(new PlayerSkeleton((JSONObject) arg));
                }


                ClientModel.onNearbyPlayersReceived(playersNearby);
            }
        });


        socket.on("lootbox-update", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                List<LatLng> lootboxes= new ArrayList<LatLng>();

                for (Object arg: args){
                    JSONObject object = (JSONObject) arg;
                    try {
                        lootboxes.add(new LatLng(object.getJSONObject("geoPos").getDouble("latitude"),object.getJSONObject("geoPos").getDouble("longitude")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                ClientModel.onLootboxesUpdate(lootboxes);
            }
        });
    }

    /**
     * Creates a client socket and connects to the remote server.
     *
     * It does also add listeners for the events in the addListeners method.
     *
     * @see ConnectivityLayer#addListeners()
     * @throws URISyntaxException
     *
     */
    public void Init() throws URISyntaxException {
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
               // loadingActivity.onConnected();
                ClientModel.onConnected();
            }
        });

        addListeners();
        /*
        If all goes right, goes ahead and connects
         */
        socket.connect();

    }

    public  void changePosition(Location position){
        LatLng pos= new LatLng(position.getLatitude(),position.getLongitude());

        changePosition(pos);

    }

    /**
     * The method sends a "position-changed" event to the server to update the position of the player
     *
     * @param position The position object that contains the new geographical coordinates of the player. This object is passed directly from the LocationHandler class.
     *
     * @see cth.codetroopers.urbanwarfare.GameUtils.LocationHandler#locationListener
     */
    public  void changePosition(LatLng position){

        JSONObject object= new JSONObject();

        /*
        Converting the Location object to LatLng object
         */

        /*
        Filling in the data required by the event.
        Look at the specifications for this event in the code of the server
         */
        try {
            object.put("id",ClientModel.playerID);
            object.put("lat",position.latitude);
            object.put("lang",position.longitude);


            /*
            Sends the actual request as a JSON object
             */
            socket.emit("position-changed",object);

            Log.i("position-changed", String.valueOf(position.latitude)+" "+position.longitude);

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
    public  void signIn(final String id){
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
       // loadingActivity.onSignedin();
        ClientModel.onSignedin();

    }


    public  void signUp(final String id){
        JSONObject object= new JSONObject();

        try {
            object.put("id",id);

            socket.emit("signup",object);
            requestPlayerInformation(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*
        Prompts loadingActivity that signing in is ongoing
         */
        // loadingActivity.onSignedin();
        ClientModel.onSignedup();

    }

    public  void changeWeapon(int weaponID){
        JSONObject object = new JSONObject();

        try {
            object.put("playerId",ClientModel.playerID);
            object.put("weaponId",weaponID);

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
    public  void attack(final String otherPlayerId){

        JSONObject object= new JSONObject();

        try {
            object.put("id",ClientModel.playerID);
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

    public  void requestPlayerInformation(final String id){
        JSONObject object= new JSONObject();
        try {
            object.put("id",id);
            socket.emit("get-player-info",object);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void consumeLootboxRequest(LatLng coord){
        JSONObject object = new JSONObject();

        try {
            object.put("id",ClientModel.playerID);
            object.put("geoPos",new JSONObject().put("latitude",coord.latitude).put("longitude",coord.longitude));
            socket.emit("consume-lootbox",object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
