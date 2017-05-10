package cth.codetroopers.urbanwarfare.Activities;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cth.codetroopers.urbanwarfare.Model.ArmourSkeleton;
import cth.codetroopers.urbanwarfare.Model.ClientModel;
import cth.codetroopers.urbanwarfare.Model.ItemsDirectory;
import cth.codetroopers.urbanwarfare.Model.WeaponSkeleton;
import cth.codetroopers.urbanwarfare.R;
import cth.codetroopers.urbanwarfare.Views.IShopView;

/**
 * Created by latiif on 5/10/17.
 */

public class ShopItemsAdapter extends ArrayAdapter<Object> {

    private IShopView.ShopViewListener mListener;

    public ShopItemsAdapter(@NonNull Context context, List<Object> shopItems,IShopView.ShopViewListener listener) {
        super(context,R.layout.armor_view, shopItems);
        mListener=listener;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Object obj = getItem(position);

        if (obj instanceof WeaponSkeleton) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View customView = inflater.inflate(R.layout.weapon_view, parent, false);

            final WeaponSkeleton weapon=(WeaponSkeleton) obj;


            Button btnSell =(Button) customView.findViewById(R.id.button_sell);
            Button btnBuy =(Button) customView.findViewById(R.id.button_buy);

            btnSell.setText(R.string.button_sell);
            btnBuy.setText(R.string.button_buy);

            btnSell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onWeaponSellListener(weapon.getId());
                }
            });

            btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onWeaponBuyListener(weapon.getId());
                }
            });

            if (ClientModel.mPlayer.hasWeapon(weapon)) {
                btnSell.setVisibility(View.VISIBLE);
                btnBuy.setVisibility(View.GONE);
            }
            else {
                btnSell.setVisibility(View.GONE);
                btnBuy.setVisibility(View.VISIBLE);
            }

            TextView textView = (TextView) customView.findViewById(R.id.armorName);
            ImageView imageView = (ImageView) customView.findViewById(R.id.imageArmor);

            TextView txtDamage = (TextView) customView.findViewById(R.id.txtDamage);
            TextView txtRange = (TextView) customView.findViewById(R.id.txtRange);


            textView.setText(weapon.getName());
            txtDamage.setText(String.valueOf(weapon.getDamage()));
            txtRange.setText(String.valueOf(weapon.getRange()));


            imageView.setImageResource(ItemsDirectory.getWeaponImage(weapon));

            return customView;
        }
        else {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View customView = inflater.inflate(R.layout.armor_view, parent, false);

            final ArmourSkeleton armour=(ArmourSkeleton) obj;


            Button btnSell =(Button) customView.findViewById(R.id.button_sell);
            Button btnBuy =(Button) customView.findViewById(R.id.button_buy);

            btnSell.setText(R.string.button_sell);
            btnBuy.setText(R.string.button_buy);

            btnSell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onArmourSellListener(armour.getId());
                }
            });

            btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onArmourBuyListener(armour.getId());
                }
            });

            if (ClientModel.mPlayer.hasArmour(armour)) {
                btnSell.setVisibility(View.VISIBLE);
                btnBuy.setVisibility(View.GONE);
            }
            else {
                btnSell.setVisibility(View.GONE);
                btnBuy.setVisibility(View.VISIBLE);
            }

            TextView textView = (TextView) customView.findViewById(R.id.armorName);
            ImageView imageView = (ImageView) customView.findViewById(R.id.imageArmor);

            TextView txtDefense = (TextView) customView.findViewById(R.id.txtDefense);

            textView.setText(armour.getName());
            txtDefense.setText(String.valueOf(armour.getValue()));


            //TODO add pics for armours
            //imageView.setImageResource(ItemsDirectory.getWeaponImage(weapon));

            return customView;
        }

    }

}
