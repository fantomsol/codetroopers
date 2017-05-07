package cth.codetroopers.urbanwarfare.Views;

import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.List;

import cth.codetroopers.urbanwarfare.Model.PlayerSkeleton;

/**
 * Created by latiif on 5/6/17.
 */
//TODO
//Consider swithcing to abstract super class

public interface IMapHandler extends OnMapReadyCallback {

    void setMapFragment(View view);

    void pinOpponents(List<PlayerSkeleton> nearbyPlayers);

    void pinPlayer(PlayerSkeleton player);

    void setMapListener(IMainView.MapListener mapListener);
}
