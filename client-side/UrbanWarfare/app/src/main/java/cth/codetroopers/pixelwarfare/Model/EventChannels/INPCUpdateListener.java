package cth.codetroopers.pixelwarfare.Model.EventChannels;

import java.util.List;

import cth.codetroopers.pixelwarfare.Model.Skeletons.BeingSkeleton;

/**
 * Created by lumo on 29/12/17.
 */

// use for both monsters and talking npcs?

public interface INPCUpdateListener {
    void updateNPCs(List<BeingSkeleton> npcs);
}
