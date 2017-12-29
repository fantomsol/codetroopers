package cth.codetroopers.pixelwarfare.Views;

import android.view.View;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import cth.codetroopers.pixelwarfare.Model.Skeletons.CharacterSkeleton;
import cth.codetroopers.pixelwarfare.Model.Skeletons.PlayerSkeleton;

/**
 * Created by latiif on 5/6/17.
 */
//TODO
//Consider swithcing to abstract super class

public interface IMapHandler extends OnMapReadyCallback {

    void setMapFragment(View view);

    void pinOpponents(List<PlayerSkeleton> nearbyPlayers);

    void pinNPCs(List<CharacterSkeleton> nearbyNPCs);

    void pinPlayer(PlayerSkeleton player);

    void pinLootboxes(List<LatLng> lootboxes);

    void setMapListener(IMainView.MapListener mapListener);
}
