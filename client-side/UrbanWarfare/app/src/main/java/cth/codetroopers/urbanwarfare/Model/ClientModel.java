package cth.codetroopers.urbanwarfare.Model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import cth.codetroopers.urbanwarfare.ClientSide.ConnectivityLayer;
import cth.codetroopers.urbanwarfare.GameUtils.LoadingStates;
import cth.codetroopers.urbanwarfare.Views.ILoadingView;
import cth.codetroopers.urbanwarfare.Views.IMainView;
import cth.codetroopers.urbanwarfare.Views.IShopView;

/**
 * Created by latiif on 5/6/17.
 */

public class ClientModel {


    private static ClientModel clientModel = new ClientModel();
    public PlayerSkeleton mPlayer;

    public boolean signIn = true;

    /*
    private IMainView mainView;
    private ILoadingView loadingView;
    private IShopView shopView;
    */

    private List <IPlayerUpdateListener> playerListeners;
    private List <ILoadUpdateListener> loadListeners;
    private List <ILootboxUpdateListener> lootboxListeners;
    private List <IOpponentsUpdateListener> opponentListeners;
    private List <IShopUpdateListener> shopListeners;

    private ShopSkeleton shop;

    private ConnectivityLayer layer = new ConnectivityLayer();

    public String playerID;

    private ClientModel(){
        playerListeners = new ArrayList<>();
        loadListeners = new ArrayList<>();
        lootboxListeners = new ArrayList<>();
        opponentListeners = new ArrayList<>();
        shopListeners = new ArrayList<>();
    }

    public static ClientModel getInstance() {
        return clientModel;
    }

    public void onConnected() {
        if (signIn) {
            //loadingView.onSigningIn();
            updateLoadlisteners(LoadingStates.SIGNINGIN);
            layer.signIn(playerID);
        } else {
            layer.signUp(playerID);
        }

        layer.requestShopItems();
    }

    public void onSignedup() {
        onSignedin();
    }

    public void onSignedin() {
        //loadingView.onFetchingData();
        updateLoadlisteners(LoadingStates.FETCHING);
        layer.requestPlayerInformation(playerID);

    }

    public void updateShop(ShopSkeleton newShop) {
        shop = newShop;
    }

    public void consumeLootbox(LatLng coord) {
        layer.consumeLootboxRequest(coord);
    }

    public void onLootboxesUpdate(List<LatLng> boxes) {
        updateLootboxlisteners(boxes);
    }

    public void changeWeapon(int weaponID) {
        layer.changeWeapon(weaponID);
    }

    public void onDataFetched() {
        //loadingView.onLoadingCompleted();
        updateLoadlisteners(LoadingStates.COMPLETE);

    }

    public void commenceLogin() {

        //loadingView.onConnecting();
        updateLoadlisteners(LoadingStates.CONNECTING);
        try {
            layer.Init();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void attack(String oId) {
        layer.attack(oId);
    }

    public void requestRadarStatusChange() {
        layer.requestChangeRadarStatus();
        layer.changePosition(mPlayer.getGeoPos());
    }

    public void requestUpdate() {
        layer.requestPlayerInformation(playerID);
        layer.changePosition(mPlayer.getGeoPos());
    }

    public void onMovementDetected(Location coordinates) {
        if (layer != null) {
            layer.changePosition(coordinates);
        }
    }

    /*public void subscribeMainView(IMainView view) {
        mainView = view;
    }

    public void subscribeLoadingView(ILoadingView view) {
        loadingView = view;
    }

    public void subscribeShopView(IShopView view) {
        shopView = view;
    }*/

    public void subscribePlayerUpdate(IPlayerUpdateListener listener){
        playerListeners.add(listener);
    }

    public void subscribeLoadUpdate(ILoadUpdateListener listener) {
        loadListeners.add(listener);
    }

    public void subscribeLootboxUpdate(ILootboxUpdateListener listener) {
        lootboxListeners.add(listener);
    }

    public void subscribeOpponentUpdate(IOpponentsUpdateListener listener) {
        opponentListeners.add(listener);
    }

    public void subscribeShopUpdate(IShopUpdateListener listener) {
        shopListeners.add(listener);
    }

    private void updatePlayerlisteners(PlayerSkeleton p) {
        for (IPlayerUpdateListener listener:playerListeners
             ) {
            listener.updateGUI(p);
        }
    }

    private void updateLoadlisteners(LoadingStates l) {
        for (ILoadUpdateListener listener:loadListeners
                ) {
            listener.updateLoadingStatus(l);
        }
    }

    private void updateLootboxlisteners(List <LatLng> l) {
        for (ILootboxUpdateListener listener:lootboxListeners
                ) {
            listener.updateLootboxes(l);
        }
    }

    private void updateOpponentlisteners(List<PlayerSkeleton> p) {
        for (IOpponentsUpdateListener listener:opponentListeners
                ) {
            listener.updateOpponents(p);
        }
    }

    private void updateShoplisteners(List<Object> items) {
        for (IShopUpdateListener listener:shopListeners
                ) {
            listener.updateItemsList(items);
        }
    }

    public void onNearbyPlayersReceived(List<PlayerSkeleton> opponents) {
        updateOpponentlisteners(opponents);
        /*if (mainView != null) {
            mainView.updatePlayersNearby(opponents);
        }*/
    }

    public void onPlayerDataRecieved(PlayerSkeleton player) {
        /*mPlayer = player;

        if (mainView != null) {
            mainView.updateGUI(player);
        }
        if (shopView != null) {
            shopView.updateGUI(mPlayer);
            shopView.updateItemsList(shop.getAllItems());
        }*/

        updatePlayerlisteners(player);
        updateShoplisteners(shop.getAllItems());
    }

    public void buyItem(Integer itemId, String itemType) {
        layer.requestItemBuy(itemId, itemType);
    }

    public void sellItem(Integer itemId, String itemType) {
        layer.requestItemSell(itemId, itemType);
    }
}
