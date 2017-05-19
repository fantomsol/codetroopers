package GameModel.Shop;

import GameModel.GameUtils.Exceptions.DuplicateItemException;
import GameModel.GameUtils.Exceptions.FactoryException;
import GameModel.GameUtils.Exceptions.GameException;
import GameModel.GameUtils.Exceptions.InsufficientException;
import GameModel.Item.Item;
import GameModel.Player.IPlayer;

import java.util.List;

/**
 * Created by Hugo on 5/1/17.
 */
public interface IShop {
    List<Item> getItems() throws FactoryException;
    void buyItem(IPlayer IPlayer, Item item) throws DuplicateItemException,InsufficientException;
    void sellItem(IPlayer IPlayer, Item item) throws GameException;
    Item getItem(Integer itemID, String itemType) throws FactoryException;
}
