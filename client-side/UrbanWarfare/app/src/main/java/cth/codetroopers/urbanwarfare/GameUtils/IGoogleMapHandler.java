package cth.codetroopers.urbanwarfare.GameUtils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by latiif on 5/6/17.
 */

interface IGoogleMapHandler extends OnMapReadyCallback {
    @Override
    void onMapReady(GoogleMap googleMap);

    void pinOpponents();

    void pinPlayer();
}
