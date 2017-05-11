package cth.codetroopers.urbanwarfare.Views;

import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import cth.codetroopers.urbanwarfare.Model.ILootboxUpdateListener;
import cth.codetroopers.urbanwarfare.Model.IOpponentsUpdateListener;
import cth.codetroopers.urbanwarfare.Model.IPlayerUpdateListener;
import cth.codetroopers.urbanwarfare.Model.PlayerSkeleton;

/**
 * Created by latiif on 5/6/17.
 */

public interface IMainView extends IView, IPlayerUpdateListener, IOpponentsUpdateListener, ILootboxUpdateListener {

    void setListener(PanelControlInteractionListener listener);

    void setMapListener(MapListener listener);


    interface PanelControlInteractionListener {
        /**
         * This callback will be invoked when "mark as read" button is being clicked
         */
        void onRequestRadarStatusChange();
        void onChangeWeapon();
        void onStartShop();
    }

    interface MapListener{
        void onAttackPlayer(String oID);
        void onConsumeLootbox(LatLng coord);
    }


    void setContext(FragmentActivity context);


}
