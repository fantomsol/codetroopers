package cth.codetroopers.pixelwarfare.Views;

import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.model.LatLng;

import cth.codetroopers.pixelwarfare.Model.EventChannels.ILootboxUpdateListener;
import cth.codetroopers.pixelwarfare.Model.EventChannels.INPCUpdateListener;
import cth.codetroopers.pixelwarfare.Model.EventChannels.IOpponentsUpdateListener;
import cth.codetroopers.pixelwarfare.Model.EventChannels.IPlayerUpdateListener;

/**
 * Created by latiif on 5/6/17.
 */

public interface IMainView extends IView, IPlayerUpdateListener, IOpponentsUpdateListener, ILootboxUpdateListener, INPCUpdateListener {

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
        void onAttackMonster();
        void onConsumeLootbox(LatLng coord);
    }


    void setContext(FragmentActivity context);


}
