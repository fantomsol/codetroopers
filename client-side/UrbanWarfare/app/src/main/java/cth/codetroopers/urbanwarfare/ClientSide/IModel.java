package cth.codetroopers.urbanwarfare.ClientSide;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import cth.codetroopers.urbanwarfare.Model.PlayerSkeleton;
import cth.codetroopers.urbanwarfare.Model.ShopSkeleton;

/**
 * Created by latiif on 5/13/17.
 */

public interface IModel {
    void onConnected();

    void onSignedup();

    void onSignedin();

    void onLootboxesUpdate(List<LatLng> boxes);

    void onDataFetched();

    void onNearbyPlayersReceived(List<PlayerSkeleton> opponents);

    void onPlayerDataRecieved(PlayerSkeleton player);

    void updateShop(ShopSkeleton skeleton);
}
