package cth.codetroopers.urbanwarfare;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import cth.codetroopers.urbanwarfare.ClientSide.ConnectivityLayer;
import cth.codetroopers.urbanwarfare.Model.ClientModel;

/**
 * Created by latiif on 5/14/17.
 */

public class UrbanWarfare extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ClientModel.getInstance().setConnectionLayer(new ConnectivityLayer());

    }

}
