package cth.codetroopers.pixelwarfare.Model.EventChannels;

import java.util.List;

import cth.codetroopers.pixelwarfare.Model.Skeletons.BeingSkeleton;

/**
 * Created by lumo on 03/01/18.
 */

//Replace Listener for Player and Npc?
public interface IBeingUpdateListener {
    void updateNPCs(List<BeingSkeleton> beings);
}
