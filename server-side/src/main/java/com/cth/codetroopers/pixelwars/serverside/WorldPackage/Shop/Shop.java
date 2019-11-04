package com.cth.codetroopers.pixelwars.serverside.WorldPackage.Shop;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.DuplicateItemException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.FactoryException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.InsufficientException;
import com.cth.codetroopers.pixelwars.serverside.Item.Armours.ArmoursDirectory;
import com.cth.codetroopers.pixelwars.serverside.Item.Armours.ArmoursFactory;
import com.cth.codetroopers.pixelwars.serverside.Item.Item;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.WeaponsDirectory;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.WeaponsFactory;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugo on 5/1/17.
 */
public class Shop {
    public List<Item> getItems() throws FactoryException {
        List<Item> items =  new ArrayList<Item>();

        for (int i = 1; i<= ArmoursDirectory.NUMBER_OF_ARMOURS; i++){
            items.add(ArmoursFactory.createArmour(i));
        }

        for (int i = 1; i<= WeaponsDirectory.NUMBER_OF_WEAPONS; i++){
            items.add(WeaponsFactory.createWeapon(i));
        }
        return items;
    }


    public void buyItem(IPlayer IPlayer, Item item) throws InsufficientException,DuplicateItemException {
        IPlayer.buyItem(item);
    }

    public void sellItem(IPlayer IPlayer, Item item) throws GameException {
        IPlayer.sellItem(item,Double.valueOf(item.getCost()*ShopConstants.REFUND_PERCENTAGE).intValue());
    }

    public Item getItem(Integer itemID, String itemType) throws FactoryException {
        if (itemType.equals("armour") || itemType.equals("Armour")){
            return ArmoursFactory.createArmour(itemID);
        }
        else if (itemType.equals("weapon") || itemType.equals("Weapon")) {
            return WeaponsFactory.createWeapon(itemID);
        }
        else throw new FactoryException("No such item found");
    }
}
