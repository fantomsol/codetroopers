package GameModel.Shop;

import GameModel.GameUtils.Exceptions.DuplicateItemException;
import GameModel.GameUtils.Exceptions.FactoryException;
import GameModel.GameUtils.Exceptions.GameException;
import GameModel.GameUtils.Exceptions.InsufficientException;
import GameModel.Item.Armours.ArmoursDirectory;
import GameModel.Item.Armours.ArmoursFactory;
import GameModel.Item.Item;
import GameModel.Item.Weapons.WeaponsDirectory;
import GameModel.Item.Weapons.WeaponsFactory;
import GameModel.Player.IPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 5/1/17.
 */
public class Shop implements IShop {
    public List<Item> getItems() throws FactoryException {
        List<Item> result =  new ArrayList<Item>();


        //Här lägger vi till alla armours
        for (int i = 1; i<= ArmoursDirectory.NUMBER_OF_ARMOURS; i++){
            result.add(ArmoursFactory.createArmour(i));
        }

        //Här lägger vi till alla weapons
        for (int i = 1; i<= WeaponsDirectory.NUMBER_OF_WEAPONS; i++){
            result.add(WeaponsFactory.createWeapon(i));
        }
        return result;
    }


    public void buyItem(IPlayer IPlayer, Item item) throws InsufficientException,DuplicateItemException {
        IPlayer.buyItem(item);
    }

    public void sellItem(IPlayer IPlayer, Item item) throws GameException {
        IPlayer.sellItem(item,Double.valueOf(item.getCost()*ShopConstants.REFUND_PERCENTAGE).intValue());
    }

    public Item getItem(Integer itemID, String itemType) throws FactoryException {
        if (itemType.equals("armour")){
            return ArmoursFactory.createArmour(itemID);
        }
        else {
            return WeaponsFactory.createWeapon(itemID);
        }
    }
}
