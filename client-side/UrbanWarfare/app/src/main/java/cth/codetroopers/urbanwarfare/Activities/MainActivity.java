package cth.codetroopers.urbanwarfare.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;

import java.net.URISyntaxException;

import cth.codetroopers.urbanwarfare.ClientSide.ClientController;
import cth.codetroopers.urbanwarfare.GameUtils.GoogleMapHandler;
import cth.codetroopers.urbanwarfare.GameUtils.LocationHandler;
import cth.codetroopers.urbanwarfare.R;

public class MainActivity extends AppCompatActivity {


    public static GoogleMapHandler googleMapHandler;
    public static LocationHandler locationHandler;


    static TextView txtName;
    static ProgressBar progressHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_actvitiy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        googleMapHandler = new GoogleMapHandler(this);
        locationHandler = new LocationHandler(this.getApplicationContext());



    }

    private void initGUI(){
        txtName = (TextView) findViewById(R.id.txtName);
        progressHp = (ProgressBar) findViewById(R.id.progressHp);
    }


    public static void updateGUI() {
        if (txtName != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String name="NAME";
                    Integer hp= 0;


                    try {
                        name=ClientController.playerInfo.getString("id");
                        hp=ClientController.playerInfo.getInt("hp");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    txtName.setText(name);
                    progressHp.setProgress(hp);
                }
            });
        }

    }
}
