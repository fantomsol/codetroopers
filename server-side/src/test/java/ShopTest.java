import GameModel.Item.Armours.ArmoursDirectory;
import GameModel.Item.Item;
import GameModel.Item.Weapons.WeaponsDirectory;
import GameModel.Shop.IShop;
import GameModel.Shop.Shop;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Hugo on 5/1/17.
 */
public class ShopTest {
    @Test
    public void getItemsListTest(){
        IShop shop= new Shop();

        List<Item> items= shop.getItems();

        Assert.assertEquals(WeaponsDirectory.NUMBER_OF_WEAPONS+ ArmoursDirectory.NUMBER_OF_ARMOURS,items.size());

    }
}
