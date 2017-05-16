package cth.codetroopers.urbanwarfare.Model.EventChannels;

import java.util.List;

import cth.codetroopers.urbanwarfare.Model.Skeletons.PlayerSkeleton;

/**
 * Created by lumo on 11/05/17.
 */

public interface IOpponentsUpdateListener {

    void updateOpponents(List<PlayerSkeleton> opponents);
}
