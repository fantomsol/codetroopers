package cth.codetroopers.urbanwarfare.Model;

import android.app.Application;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import cth.codetroopers.urbanwarfare.ClientSide.ClientController;
import cth.codetroopers.urbanwarfare.GameUtils.LocationHandler;
import cth.codetroopers.urbanwarfare.Views.IMainView;

/**
 * Created by latiif on 5/6/17.
 */

public class ClientModel {
   public static PlayerSkeleton mPlayer;
    private static IMainView mainView;



    public static void onMovementDetected(Location coordinates){
        ClientController.changePosition(coordinates);
    }

    public static void subscribeMainView(IMainView view){
        mainView=view;
    }


    public static void onNearbyPlayersReceived(List<PlayerSkeleton> opponents){
        mainView.updatePlayersNearby(opponents);
    }

   public static void onPlayerDataRecieved(PlayerSkeleton player){
       mPlayer=player;

       if (mainView!=null) {
           mainView.updateGUI(player);
       }
   }
}
