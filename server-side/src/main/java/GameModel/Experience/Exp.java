package GameModel.Experience;

import GameModel.Player.IPlayer;

/**
 * Created by lumo on 05/05/17.
 */
public abstract class Exp {
    public static void setExpOnKill(IPlayer killer, IPlayer killed){
        int dif = killed.getExp()-killer.getExp();
        if (dif <= 100) {
            killer.setExp(killer.getExp() + 50);
        } else {
            killer.setExp(killer.getExp() + (int)(dif*0.5));
        }

        killed.setExp((int)(killed.getExp()*0.9));
    }
}
