import GameModel.Item.Armours.ArmoursDirectory;
import GameModel.Item.Item;
import GameModel.Item.Weapons.Sniper;
import GameModel.Item.Weapons.WeaponsDirectory;
import GameModel.Player.GeoPos;
import GameModel.Player.Player;
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

    @Test
    public void itemBuyTest(){
        Player p1 =  new Player("hugo",new GeoPos(0.0,0.0));
        IShop shop = new Shop();


        int goldF=p1.getGold();

        shop.buyItem(p1,new Sniper());

        Assert.assertTrue(p1.getGold()== goldF);

        p1.grantGold(100);

        shop.buyItem(p1,new Sniper());

      //  Assert.assertTrue(p1.getGold()==new Integer(0));
    }
}
