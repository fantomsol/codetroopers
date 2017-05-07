package cth.codetroopers.urbanwarfare.Controllers;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cth.codetroopers.urbanwarfare.ClientSide.ClientController;
import cth.codetroopers.urbanwarfare.GameUtils.LocationHandler;
import cth.codetroopers.urbanwarfare.Model.ClientModel;
import cth.codetroopers.urbanwarfare.Views.IMainView;
import cth.codetroopers.urbanwarfare.Views.MainViewImp;

/**
 * Created by latiif on 5/6/17.
 */

public class MainController extends AppCompatActivity implements IMainController{

    private IMainView mainView;
    private LocationHandler locationHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);

        mainView = new MainViewImp((LayoutInflater) getSystemService( LAYOUT_INFLATER_SERVICE ),view);
        mainView.setContext(this);
        mainView.setMapListener(this);
        mainView.setListener(this);


        ClientModel.subscribeMainView(mainView);

        setContentView(mainView.getRootView());

        locationHandler= new LocationHandler(this);

    }

    @Override
    public void onRequestRadarStatusChange() {
        ClientController.requestChangeRadarStatus();
    }

    @Override
    public void onChangeWeapon() {

    }

    @Override
    public void onAttackPlayer(String oID) {
        ClientController.attack(oID);
    }



    @Override
    public void onLocationChanged(Location location) {
        ClientModel.onMovementDetected(location);
    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }


}
