package cth.codetroopers.urbanwarfare;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

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
        instance=this;
    }



    private static UrbanWarfare instance;


    public static UrbanWarfare getInstance(){
      return instance;
    }


    public void showToast(final String  data) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getApplicationContext(), data,
                        Toast.LENGTH_LONG).show();
            }});
    }

}
