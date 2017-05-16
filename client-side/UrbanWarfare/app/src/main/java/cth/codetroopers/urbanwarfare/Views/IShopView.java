package cth.codetroopers.urbanwarfare.Views;

import android.content.Context;

import cth.codetroopers.urbanwarfare.Model.EventChannels.IPlayerUpdateListener;
import cth.codetroopers.urbanwarfare.Model.EventChannels.IShopUpdateListener;

/**
 * Created by latiif on 5/10/17.
 */

public interface IShopView extends IView, IShopUpdateListener, IPlayerUpdateListener{
    void setContext(Context context);
    void setListener(ShopViewListener listener);

    interface ShopViewListener{
        void onArmourBuyListener(Integer armourID);
        void onArmourSellListener(Integer armourID);

        void onWeaponBuyListener(Integer weaponID);
        void onWeaponSellListener(Integer weaponID);
    }
}
