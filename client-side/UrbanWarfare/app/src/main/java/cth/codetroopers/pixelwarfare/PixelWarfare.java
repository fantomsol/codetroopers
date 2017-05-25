package cth.codetroopers.pixelwarfare;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import cth.codetroopers.pixelwarfare.ClientSide.ConnectivityLayer;
import cth.codetroopers.pixelwarfare.Model.ClientModel;

/**
 * Created by latiif on 5/14/17.
 */

public class PixelWarfare extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ClientModel.getInstance().setConnectionLayer(new ConnectivityLayer());
        instance=this;
    }



    private static PixelWarfare instance;


    public static PixelWarfare getInstance(){
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
