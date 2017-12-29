package cth.codetroopers.pixelwarfare.Model;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import cth.codetroopers.pixelwarfare.Model.EventChannels.ILoadUpdateListener;
import cth.codetroopers.pixelwarfare.Model.EventChannels.ILootboxUpdateListener;
import cth.codetroopers.pixelwarfare.Model.EventChannels.INPCUpdateListener;
import cth.codetroopers.pixelwarfare.Model.EventChannels.IOpponentsUpdateListener;
import cth.codetroopers.pixelwarfare.Model.EventChannels.IPlayerUpdateListener;
import cth.codetroopers.pixelwarfare.Model.EventChannels.IShopUpdateListener;
import cth.codetroopers.pixelwarfare.Model.EventChannels.LoadingStates;
import cth.codetroopers.pixelwarfare.Model.Skeletons.CharacterSkeleton;
import cth.codetroopers.pixelwarfare.Model.Skeletons.GameException;
import cth.codetroopers.pixelwarfare.Model.Skeletons.PlayerSkeleton;
import cth.codetroopers.pixelwarfare.Model.Skeletons.ShopSkeleton;
import cth.codetroopers.pixelwarfare.PixelWarfare;

/**
 * Created by latiif on 5/6/17.
 */

public class ClientModel implements IConnectivityLayer.ConnectivityListener {


    private static ClientModel clientModel = new ClientModel();
    public PlayerSkeleton mPlayer;

    public boolean signIn = true;

    private List <IPlayerUpdateListener> playerListeners;
    private List <INPCUpdateListener> npcListeners;
    private List <ILoadUpdateListener> loadListeners;
    private List <ILootboxUpdateListener> lootboxListeners;
    private List <IOpponentsUpdateListener> opponentListeners;
    private List <IShopUpdateListener> shopListeners;

    public void setPlayerId(String id){
        layer.setPlayerID(id);
        this.playerID=id;
    }
    private ShopSkeleton shop;

    private IConnectivityLayer layer;

    private String playerID;

    private ClientModel(){
        playerListeners = new ArrayList<>();
        npcListeners = new ArrayList<>();
        loadListeners = new ArrayList<>();
        lootboxListeners = new ArrayList<>();
        opponentListeners = new ArrayList<>();
        shopListeners = new ArrayList<>();

    }

    public static ClientModel getInstance() {
        return clientModel;
    }

    public void setConnectionLayer(IConnectivityLayer layer){
        this.layer=layer;
        layer.setListener(this);
    }

    @Override
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

    @Override
    public void onSignedup() {
        onSignedin();
    }

    @Override
    public void onSignedin() {
        //loadingView.onFetchingData();
        updateLoadlisteners(LoadingStates.FETCHING);
        layer.requestPlayerInformation(playerID);

    }

    public void updateShop(ShopSkeleton newShop) {
        shop = newShop;
    }

    @Override
    public void onExceptionReceived(GameException gameException) {
        Log.i("EXCEPTION",gameException.getTitle()+" : "+gameException.getMsg());

        PixelWarfare.getInstance().showToast(gameException.getTitle()+" :"+gameException.getMsg());
    }

    public void consumeLootbox(LatLng coord) {
        layer.consumeLootboxRequest(coord);
    }

    @Override
    public void onLootboxesUpdate(List<LatLng> boxes) {
        updateLootboxlisteners(boxes);
    }

    public void changeWeapon(int weaponID) {
        layer.changeWeapon(weaponID);
    }

    @Override
    public void onDataFetched() {
        updateLoadlisteners(LoadingStates.COMPLETE);

    }

    public void commenceLogin() throws URISyntaxException {

        updateLoadlisteners(LoadingStates.CONNECTING);
        layer.Init();

    }

    public void attack(String oId) {
        layer.attack(oId);
    }

    public void requestRadarStatusChange() {
        layer.requestChangeRadarStatus(mPlayer.isOnline());
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

    public void subscribeNPCUpdate(INPCUpdateListener listener){
        npcListeners.add(listener);
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
    private void updateNPClisteners(List<CharacterSkeleton> n) {
        for (INPCUpdateListener listener:npcListeners) {
            listener.updateNPCs(n);
        }
    }

    private void updateLoadlisteners(LoadingStates l) {
        for (ILoadUpdateListener listener:loadListeners
                ) {
            listener.updateLoadingStatus(l);
        }
    }

    private void updateLootboxlisteners(List <LatLng> l) {
        for (ILootboxUpdateListener listener:lootboxListeners) {
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

    public void changeAvatar(String newAvatar){
        layer.requestChangeAvatar(newAvatar);
    }

    @Override
    public void onNearbyPlayersReceived(List<PlayerSkeleton> opponents) {
        updateOpponentlisteners(opponents);
        /*if (mainView != null) {
            mainView.updatePlayersNearby(opponents);
        }*/
    }

    @Override
    public void onNearbyNPCsReceived(List<CharacterSkeleton> npcs) {
        updateNPClisteners(npcs);
    }


    @Override
    public void onPlayerDataRecieved(PlayerSkeleton player) {
        mPlayer = player;

        /*
        if (mainView != null) {
            mainView.updateGUI(player);
        }
        if (shopView != null) {
            shopView.updateGUI(mPlayer);
            shopView.updateItemsList(shop.getAllItems());
        }*/

        updatePlayerlisteners(player);

        if (shop!=null) {
            updateShoplisteners(shop.getAllItems());
        }
    }

    public void buyItem(Integer itemId, String itemType) {
        layer.requestItemBuy(itemId, itemType);
    }

    public void sellItem(Integer itemId, String itemType) {
        layer.requestItemSell(itemId, itemType);
    }
}
