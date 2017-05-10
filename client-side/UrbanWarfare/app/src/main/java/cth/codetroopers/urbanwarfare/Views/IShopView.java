package cth.codetroopers.urbanwarfare.Views;

import android.content.Context;

import java.util.List;

import cth.codetroopers.urbanwarfare.Model.PlayerSkeleton;

/**
 * Created by latiif on 5/10/17.
 */

public interface IShopView extends IView {
    void updateItemsList(List<Object> shopItems);
    void updateGUI(PlayerSkeleton player);
    void setContext(Context context);

    void setListener(ShopViewListener listener);

    interface ShopViewListener{
        void onArmourBuyListener(Integer armourID);
        void onArmourSellListener(Integer armourID);

        void onWeaponBuyListener(Integer weaponID);
        void onWeaponSellListener(Integer weaponID);
    }
}
