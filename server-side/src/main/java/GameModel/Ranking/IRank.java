package GameModel.Ranking;

import GameModel.Player.Player;

/**
 * Created by lumo on 02/05/17.
 */
public interface IRank {

    Ranks getRank(int exp);

    void onKill(Player killer, Player killed);

    void onBuy(Player p, int price);
}
