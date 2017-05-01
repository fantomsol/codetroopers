package GameModel.Shop;

import GameModel.Item.Item;
import GameModel.Player.Player;

import java.util.List;

/**
 * Created by Hugo on 5/1/17.
 */
public interface IShop {
    List<Item> getItems();
    void buyItem(Player player, Item item);
    void sellItem(Player player,Item item);

}
