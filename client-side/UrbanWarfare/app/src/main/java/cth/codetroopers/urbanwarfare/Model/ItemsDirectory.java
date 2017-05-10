package cth.codetroopers.urbanwarfare.Model;

import cth.codetroopers.urbanwarfare.R;

/**
 * Created by latiif on 5/8/17.
 */

public class ItemsDirectory {


    public static int getRank(PlayerSkeleton player){
        switch (player.getRank()){
            case "PRIVATE":
                return R.drawable.rank_private;
            case "SERGEANT":
                return R.drawable.rank_serjeant;
            case "MAJOR":
                return R.drawable.rank_major;
            case "GENERAL":
                return R.drawable.rank_general;
            case "JOACHIMVONHACHT":
                return R.drawable.rank_joachimvonhacht;
            default:
                return 0;
        }
    }

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
            case 5:
                return R.drawable.whiteflag;
            default:
                return 0;

        }
    }
}
