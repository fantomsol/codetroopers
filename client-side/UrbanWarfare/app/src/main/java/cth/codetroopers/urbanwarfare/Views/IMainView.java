package cth.codetroopers.urbanwarfare.Views;

import cth.codetroopers.urbanwarfare.Model.PlayerSkeleton;

/**
 * Created by latiif on 5/6/17.
 */

public interface IMainView extends IView {
    void updateGUI(PlayerSkeleton player);

    interface PanelControlInteraction {
        /**
         * This callback will be invoked when "mark as read" button is being clicked
         */
        void onRequestRadarStatusChange();
        void onChangeWeapon();
    }

    interface MapInteraction{
        void onAttackPlayer();
    }



}
