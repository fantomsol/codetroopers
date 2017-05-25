package cth.codetroopers.pixelwarfare.GameUtils;

import cth.codetroopers.pixelwarfare.Model.Skeletons.PlayerSkeleton;
import cth.codetroopers.pixelwarfare.Model.Skeletons.WeaponSkeleton;
import cth.codetroopers.pixelwarfare.R;

/**
 * Created by latiif on 5/8/17.
 */

public class ResourceDirectory {


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

    public static int getAvatarImage(String name) {
        switch (name){
            case "JIM":
                return R.drawable.avatar_jim;
            case "WILLOW":
                return R.drawable.avatar_willow;
            case "KARMA":
                return R.drawable.avatar_karma;
            case "SIBOAN":
                return R.drawable.avatar_siboan;
            case "KYLE":
                return R.drawable.avatar_kyle;
            case "TERRY":
                return R.drawable.avatar_terry;
            case "LOTUS":
                return R.drawable.avatar_lotus;
            default:
                return R.drawable.ghost;

        }
    }
}
