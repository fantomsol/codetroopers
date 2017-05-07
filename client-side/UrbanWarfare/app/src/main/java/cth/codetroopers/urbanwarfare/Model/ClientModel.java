package cth.codetroopers.urbanwarfare.Model;

import android.location.Location;

import java.net.URISyntaxException;
import java.util.List;

import cth.codetroopers.urbanwarfare.ClientSide.ConnectivityLayer;
import cth.codetroopers.urbanwarfare.Views.ILoadingView;
import cth.codetroopers.urbanwarfare.Views.IMainView;

/**
 * Created by latiif on 5/6/17.
 */

public class ClientModel {
   public static PlayerSkeleton mPlayer;

    private static IMainView mainView;
    private static ILoadingView loadingView;

    private static ConnectivityLayer layer= new ConnectivityLayer();


    public static String playerID;

    public static void commenceLogin(){

        loadingView.onConnecting();
        try {
            layer.Init();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        loadingView.onSigningIn();
        layer.signIn(playerID);

        loadingView.onFetchingData();
        layer.requestPlayerInformation(playerID);

        loadingView.onLoadingCompleted();

    }

    public static void attack(String oId){
        layer.attack(oId);
    }
    public static void requestRadarStatusChange(){
        layer.requestChangeRadarStatus();
        layer.changePosition(mPlayer.getGeoPos());
    }

    public static void requestUpdate(){
        layer.requestPlayerInformation(playerID);
        layer.changePosition(mPlayer.getGeoPos());
    }
    public static void onMovementDetected(Location coordinates){
        if (layer!=null) {
            layer.changePosition(coordinates);
        }
    }

    public static void subscribeMainView(IMainView view){
        mainView=view;
    }
    public static void subscribeLoadingView(ILoadingView view){
        loadingView=view;
    }



    public static void onNearbyPlayersReceived(List<PlayerSkeleton> opponents){
        if (mainView!=null) {
            mainView.updatePlayersNearby(opponents);
        }
    }

   public static void onPlayerDataRecieved(PlayerSkeleton player){
       mPlayer=player;

       if (mainView!=null) {
           mainView.updateGUI(player);
       }
   }
}
