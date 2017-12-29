package cth.codetroopers.pixelwarfare.Controllers;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;

import java.security.Permission;
import java.security.Permissions;

import cth.codetroopers.pixelwarfare.Activities.ChooseWeapon;
import cth.codetroopers.pixelwarfare.Model.ClientModel;
import cth.codetroopers.pixelwarfare.Model.EventChannels.INPCUpdateListener;
import cth.codetroopers.pixelwarfare.Views.IMainView;
import cth.codetroopers.pixelwarfare.Views.MainViewImp;

/**
 * Created by latiif on 5/6/17.
 */

public class MainController extends AppCompatActivity implements IMainController{

    private IMainView mainView;
    private LocationHandler locationHandler;

    public static int CODE_CHANGEWEAPON=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);

        mainView = new MainViewImp((LayoutInflater) getSystemService( LAYOUT_INFLATER_SERVICE ),view);
        mainView.setContext(this);
        mainView.setMapListener(this);
        mainView.setListener(this);

        ClientModel.getInstance().subscribePlayerUpdate(mainView);
        ClientModel.getInstance().subscribeLootboxUpdate(mainView);
        ClientModel.getInstance().subscribeOpponentUpdate(mainView);
        ClientModel.getInstance().subscribeNPCUpdate(mainView);

        setContentView(mainView.getRootView());


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getContext(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }else {
            attachLocationHandler();
        }


    }

    private void attachLocationHandler(){
        locationHandler= new LocationHandler(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        ClientModel.getInstance().requestUpdate();
    }

    @Override
    public void onRequestRadarStatusChange() {
        ClientModel.getInstance().requestRadarStatusChange();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==CODE_CHANGEWEAPON){
            if (resultCode==RESULT_OK){
                ClientModel.getInstance().changeWeapon(data.getIntExtra("weapon-id",1));
            }
        }
    }

    @Override
    public void onChangeWeapon() {
        Intent i = new Intent(this, ChooseWeapon.class);
        startActivityForResult(i,CODE_CHANGEWEAPON);
    }

    @Override
    public void onStartShop() {
        Intent i = new Intent(this, ShopViewController.class);
        startActivity(i);
    }

    @Override
    public void onAttackPlayer(String oID) {
        ClientModel.getInstance().attack(oID);
    }

    @Override
    public void onConsumeLootbox(LatLng coord) {
        ClientModel.getInstance().consumeLootbox(coord);
    }


    @Override
    public void onLocationChanged(Location location) {
        ClientModel.getInstance().onMovementDetected(location);
    }

    @Override
    public AppCompatActivity getContext() {
        return this;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==1){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                attachLocationHandler();

            }
        }
    }
}
