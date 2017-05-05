package GameModel.Ranking;

import GameModel.Player.Player;

import static GameModel.Ranking.Ranks.*;

/**
 * Created by lumo on 02/05/17.
 */
public abstract class Rank {


    public static Ranks getRank(int exp) {
        if (exp < 1000) {
            return PRIVATE;
        } else if (exp < 5000) {
            return SERJEANT;
        } else if (exp < 10000) {
            return MAJOR;
        } else if (exp < 20000) {
            return GENERAL;
        } else {
            return JOACHIMVONHACHT;
        }
    }

    public static Ranks getRank(Player p) {
        return getRank(p.getExp());
    }

    public void onKill(Player killer, Player killed) {

    }

    public void onBuy(Player p, int price) {

    }
}
