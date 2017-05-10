package GameModel.Shop;

import GameModel.Item.Item;
import GameModel.Player.IPlayer;

import java.util.List;

/**
 * Created by Hugo on 5/1/17.
 */
public interface IShop {
    List<Item> getItems();
    void buyItem(IPlayer IPlayer, Item item);
    void sellItem(IPlayer IPlayer, Item item);
    Item getItem(Integer itemID, String itemType);
}
