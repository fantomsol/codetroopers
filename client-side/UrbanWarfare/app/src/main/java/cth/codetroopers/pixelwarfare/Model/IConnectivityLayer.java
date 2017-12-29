package cth.codetroopers.pixelwarfare.Model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.net.URISyntaxException;
import java.util.List;

import cth.codetroopers.pixelwarfare.Model.Skeletons.CharacterSkeleton;
import cth.codetroopers.pixelwarfare.Model.Skeletons.GameException;
import cth.codetroopers.pixelwarfare.Model.Skeletons.PlayerSkeleton;
import cth.codetroopers.pixelwarfare.Model.Skeletons.ShopSkeleton;

/**
 * Created by latiif on 5/13/17.
 */

public interface IConnectivityLayer {

    void requestChangeAvatar(String newAvatar);

    interface ConnectivityListener {
        void onConnected();

        void onSignedup();

        void onSignedin();

        void onLootboxesUpdate(List<LatLng> boxes);

        void onDataFetched();

        void onNearbyPlayersReceived(List<PlayerSkeleton> opponents);

        void onNearbyNPCsReceived(List<CharacterSkeleton> npcs);

        void onPlayerDataRecieved(PlayerSkeleton player);

        void updateShop(ShopSkeleton skeleton);

        void onExceptionReceived(GameException gameException);
    }


    void setListener(ConnectivityListener listener);

    void requestChangeRadarStatus(boolean currentStatus);

    void Init() throws URISyntaxException;

    void requestShopItems();

    void changePosition(Location position);

    void changePosition(LatLng position);

    void setPlayerID(String id);

    void signIn(String id);

    void signUp(String id);

    void changeWeapon(int weaponID);

    void attack(String otherPlayerId);

    void requestPlayerInformation(String id);

    void consumeLootboxRequest(LatLng coord);

    void requestItemBuy(Integer itemId, String itemType);

    void requestItemSell(Integer itemId, String itemType);
}
