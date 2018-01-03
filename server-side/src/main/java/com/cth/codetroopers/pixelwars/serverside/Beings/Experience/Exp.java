package com.cth.codetroopers.pixelwars.serverside.Beings.Experience;

/**
 * Created by lumo on 05/05/17.
 */
public abstract class Exp {
    public static Integer getExpOnKill(Integer killerXp, Integer killedXp){
        int dif = killedXp-killerXp;
        if (dif <= 100) {
            return (killerXp + 50);
        } else {
            return  (killerXp + (int)(dif*0.5));
        }


    }

    public static Integer getExpOnKilled(Integer killedXP){
        return ((int)(killedXP*0.9));
    }



    public static Integer getExpOnAttackingUnarmed(Integer attackerXp){
        if (attackerXp>10) {
            return (attackerXp - 10);
        }
        return 0;
    }
}
