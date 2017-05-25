package cth.codetroopers.pixelwarfare.Controllers;

import android.content.Context;
import android.location.Location;

import cth.codetroopers.pixelwarfare.Views.IMainView;

/**
 * Created by latiif on 5/7/17.
 */

public interface IMainController extends IMainView.MapListener,IMainView.PanelControlInteractionListener{

    void onLocationChanged(Location location);

    Context getContext();

}
