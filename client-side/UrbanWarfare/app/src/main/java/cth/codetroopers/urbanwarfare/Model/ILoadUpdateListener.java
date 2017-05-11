package cth.codetroopers.urbanwarfare.Model;

import cth.codetroopers.urbanwarfare.GameUtils.LoadingStates;

/**
 * Created by lumo on 11/05/17.
 */

public interface ILoadUpdateListener {

    void updateLoadingStatus(LoadingStates state);
}
