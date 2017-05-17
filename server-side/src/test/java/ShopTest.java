import GameModel.GameUtils.Exceptions.GameException;
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

        List<Item> items= null;
        try {
            items = shop.getItems();
        } catch (GameException e) {
            System.out.println(e);
        }

        Assert.assertEquals(WeaponsDirectory.NUMBER_OF_WEAPONS+ ArmoursDirectory.NUMBER_OF_ARMOURS,items.size());

    }

    @Test
    public void itemBuyTest(){
        /*
        IPlayer p1 =  new Player("hugo",new GeoPos(0.0,0.0));
        IShop shop = new Shop();


        int goldF=p1.getGold();

        shop.buyItem(p1,new Sniper());

        Assert.assertTrue(p1.getGold()== goldF);

        p1.grantGold(100);

        shop.buyItem(p1,new Sniper());

       // Assert.assertTrue(p1.getGold()==new Integer(0));
       */
    }

    @Test
    public void itemSellTest(){
        /*
        Player p1 =  new Player("hugo",new GeoPos(0.0,0.0));
        IShop shop = new Shop();

        Sniper sniper= new Sniper();

        p1.grantGold(100);

        shop.buyItem(p1, sniper);


        p1.weaponEquipped=sniper;

        Assert.assertTrue(p1.weaponEquipped.getId()==WeaponsDirectory.SNIPER);

        shop.sellItem(p1,sniper);




        Assert.assertTrue(p1.getGold()==100);



        Assert.assertTrue(p1.weaponEquipped.getId()==WeaponsDirectory.PISTOL);




*/
    }
}
