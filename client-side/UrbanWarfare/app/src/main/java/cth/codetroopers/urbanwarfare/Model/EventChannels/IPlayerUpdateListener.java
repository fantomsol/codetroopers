package cth.codetroopers.urbanwarfare.Model.EventChannels;

import cth.codetroopers.urbanwarfare.Model.Skeletons.PlayerSkeleton;

/**
 * Created by lumo on 11/05/17.
 */

public interface IPlayerUpdateListener {

    void updateGUI(PlayerSkeleton p);

}
