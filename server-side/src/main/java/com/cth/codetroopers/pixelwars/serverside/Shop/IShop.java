package com.cth.codetroopers.pixelwars.serverside.Shop;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.DuplicateItemException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.FactoryException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.InsufficientException;
import com.cth.codetroopers.pixelwars.serverside.Item.Item;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;

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
