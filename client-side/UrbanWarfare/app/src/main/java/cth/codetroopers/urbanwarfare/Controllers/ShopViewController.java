package cth.codetroopers.urbanwarfare.Controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import cth.codetroopers.urbanwarfare.Model.ClientModel;
import cth.codetroopers.urbanwarfare.Views.IShopView;
import cth.codetroopers.urbanwarfare.Views.LoginViewImp;
import cth.codetroopers.urbanwarfare.Views.ShopViewImp;

/**
 * Created by latiif on 5/10/17.
 */

public class ShopViewController extends AppCompatActivity implements IShopView.ShopViewListener {

    private IShopView shopView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);
        shopView= new ShopViewImp((LayoutInflater) getSystemService( LAYOUT_INFLATER_SERVICE ),view);

        shopView.setListener(this);
        shopView.setContext(this);

        ClientModel.subscribeShopView(shopView);


        setContentView(shopView.getRootView());
        ClientModel.requestUpdate();
    }


    @Override
    public void onArmourBuyListener(Integer armourID) {
        ClientModel.buyItem(armourID,"armour");
    }

    @Override
    public void onArmourSellListener(Integer armourID) {
        ClientModel.sellItem(armourID,"armour");

    }

    @Override
    public void onWeaponBuyListener(Integer weaponID) {
        ClientModel.buyItem(weaponID,"weapon");

    }

    @Override
    public void onWeaponSellListener(Integer weaponID) {
        ClientModel.sellItem(weaponID,"weapon");

    }
}
