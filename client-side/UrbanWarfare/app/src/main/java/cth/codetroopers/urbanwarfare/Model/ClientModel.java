package cth.codetroopers.urbanwarfare.Model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

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

    public static boolean signIn=true;

    private static IMainView mainView;
    private static ILoadingView loadingView;

    private static ShopSkeleton shop;

    private static ConnectivityLayer layer= new ConnectivityLayer();


    public static String playerID;



    public static void onConnected(){
        if (signIn){
        loadingView.onSigningIn();
        layer.signIn(playerID);
        }
        else {
            layer.signUp(playerID);
        }

        layer.requestShopItems();
    }

    public static void onSignedup(){
        onSignedin();
    }

    public static void onSignedin(){
        loadingView.onFetchingData();
        layer.requestPlayerInformation(playerID);

    }

    public static void updateShop(ShopSkeleton newShop){
        shop=newShop;
    }
    public static void consumeLootbox(LatLng coord){
        layer.consumeLootboxRequest(coord);
    }

    public static void onLootboxesUpdate(List<LatLng> boxes){
        mainView.updateLootboxes(boxes);
    }

    public static void changeWeapon(int weaponID){
        layer.changeWeapon(weaponID);
    }

    public static void onDataFetched(){
        loadingView.onLoadingCompleted();

    }

    public static void commenceLogin(){

        loadingView.onConnecting();
        try {
            layer.Init();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
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
