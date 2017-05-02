package cth.codetroopers.urbanwarfare.Activities;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cth.codetroopers.urbanwarfare.ClientSide.WeaponSkeleton;
import cth.codetroopers.urbanwarfare.R;

/**
 * Created by latiif on 5/2/17.
 */

public class WeaponViewAdapter extends ArrayAdapter<WeaponSkeleton> {

    public WeaponViewAdapter(@NonNull Context context, List<WeaponSkeleton> weaponSkeletons) {
        super(context, R.layout.weapon_view, weaponSkeletons);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater= LayoutInflater.from(getContext());
        View customView= inflater.inflate(R.layout.weapon_view,parent,false);

        WeaponSkeleton weapon = getItem(position);

        TextView textView = (TextView) customView.findViewById(R.id.weaponName);
        ImageView imageView = (ImageView) customView.findViewById(R.id.imageView);

        textView.setText(weapon.getName());

        return customView;

    }
}
