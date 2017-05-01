package GameModel.Shop;

import GameModel.Item.Armours.ArmoursDirectory;
import GameModel.Item.Armours.ArmoursFactory;
import GameModel.Item.Item;
import GameModel.Item.Weapons.WeaponsDirectory;
import GameModel.Item.Weapons.WeaponsFactory;
import GameModel.Player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 5/1/17.
 */
public class Shop implements IShop {
    public List<Item> getItems() {
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

    public void buyItem(Player player, Item item) {
        player.buyItem(item);
    }

    public void sellItem(Player player, Item item) {

    }
}
