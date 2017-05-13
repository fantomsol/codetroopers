package cth.codetroopers.urbanwarfare.Model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.net.URISyntaxException;

/**
 * Created by latiif on 5/13/17.
 */

public interface IConnectivityLayer {

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
