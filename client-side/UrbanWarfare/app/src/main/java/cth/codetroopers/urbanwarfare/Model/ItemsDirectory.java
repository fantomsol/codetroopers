package cth.codetroopers.urbanwarfare.Model;

import cth.codetroopers.urbanwarfare.R;

/**
 * Created by latiif on 5/8/17.
 */

public class ItemsDirectory {

    public static int getWeaponImage(WeaponSkeleton weapon){
        switch (weapon.getId()){
            case 1:
                return R.drawable.pistol;
            case 2:
                return R.drawable.sniper;
            case 3:
                return R.drawable.rifle;
            case 4:
                return R.drawable.shotgun;
            default:
                return 0;

        }
    }
}
