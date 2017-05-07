package cth.codetroopers.urbanwarfare.Controllers;

import android.content.Context;
import android.location.Location;

import cth.codetroopers.urbanwarfare.Views.IMainView;

/**
 * Created by latiif on 5/7/17.
 */

public interface IMainController extends IMainView.MapListener,IMainView.PanelControlInteractionListener{

    void onLocationChanged(Location location);

    Context getContext();

}
