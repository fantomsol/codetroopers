package cth.codetroopers.urbanwarfare.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by lumo on 11/05/17.
 */

public interface ILootboxUpdateListener {

    void updateLootboxes(List<LatLng> lootboxes);
}
