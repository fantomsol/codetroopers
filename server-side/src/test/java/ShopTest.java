import GameModel.GameUtils.Exceptions.DuplicateItemException;
import GameModel.GameUtils.Exceptions.FactoryException;
import GameModel.GameUtils.Exceptions.GameException;
import GameModel.GameUtils.Exceptions.InsufficientException;
import GameModel.Item.Armours.ArmoursDirectory;
import GameModel.Item.Item;
import GameModel.Item.Weapons.IWeapon;
import GameModel.Item.Weapons.Sniper;
import GameModel.Item.Weapons.WeaponsDirectory;
import GameModel.Player.IPlayer;
import GameModel.Shop.IShop;
import GameModel.Shop.Shop;
import GameModel.Shop.ShopConstants;
import Mocks.WeaponMock;
import Mocks.PlayerMock;
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
        } catch (FactoryException e) {
            Assert.assertTrue(1==2);
        }

        Assert.assertEquals(WeaponsDirectory.NUMBER_OF_WEAPONS+ ArmoursDirectory.NUMBER_OF_ARMOURS,items.size());

    }

    @Test
    public void itemBuyTest() throws FactoryException {

        IPlayer p1 =  new PlayerMock();
        IWeapon weapon = new WeaponMock();
        IShop shop = new Shop();


        p1.grantGold(weapon.getCost()-100);
        int goldF=p1.getGold();

        try {
            shop.buyItem(p1, weapon);
        } catch (DuplicateItemException e) {
            Assert.assertTrue(1==2);
        } catch (InsufficientException e) {
           Assert.assertTrue(true);
        }

        Assert.assertTrue(p1.getGold()== goldF);

        p1.grantGold(100);

        try {
            shop.buyItem(p1,weapon);
        } catch (DuplicateItemException e) {
            Assert.assertTrue(1==2);
        } catch (InsufficientException e) {
            Assert.assertTrue(1==2);
        }


    }

    @Test
    public void itemSellTest() throws InsufficientException, DuplicateItemException {

        IPlayer p1 =  new PlayerMock();
        IShop shop = new Shop();

        IWeapon weapon= new WeaponMock();

        p1.grantGold(weapon.getCost());

        shop.buyItem(p1, weapon);


        try {
            shop.sellItem(p1,weapon);
        } catch (GameException e) {
            Assert.assertTrue(1==2);
        }


        Assert.assertTrue(p1.getGold()==weapon.getCost()* ShopConstants.REFUND_PERCENTAGE);



    }
}
