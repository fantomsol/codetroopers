import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.*;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Item.Armours.ArmoursDirectory;
import com.cth.codetroopers.pixelwars.serverside.Item.Armours.IArmour;
import com.cth.codetroopers.pixelwars.serverside.Item.Item;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.IWeapon;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.Weapon;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.WeaponsDirectory;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Player.Player;
import com.cth.codetroopers.pixelwars.serverside.Player.PlayerConstants;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.Shop.Shop;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.Shop.ShopConstants;
import Mocks.WeaponMock;
import Mocks.PlayerMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Hugo on 5/1/17.
 */
public class ShopTest {

	private Shop shop = new Shop();
	GeoPos pos;
	IPlayer player;

	@Before
	public void initShopTest() throws GeographicalException, FactoryException {
		this.pos = new GeoPos(0.0, 0.0);
		this.player = new Player("p", pos);
	}

	@Test
	public void getItemsTest() {
		List<Item> items = null;
		try {
			items = shop.getItems();
		} catch (FactoryException e) {
			Assert.fail();
		}
		for (Object item : items) {
			Assert.assertTrue(item instanceof Weapon || item instanceof IArmour);
		}

		Assert.assertEquals(WeaponsDirectory.NUMBER_OF_WEAPONS + ArmoursDirectory.NUMBER_OF_ARMOURS, items.size());
	}

	@Test
	public void itemBuyTest() {
		// Make sure exception is thrown and no money drawn when attempting to buy with insufficient money
		IWeapon weapon = new WeaponMock(PlayerConstants.START_GOLD + 1);
		try {
			shop.buyItem(player, weapon);
		} catch (DuplicateItemException e) {
			Assert.fail();
		} catch (InsufficientException e) {
			Assert.assertTrue(player.getGold() == PlayerConstants.START_GOLD);
		}

		// Grant money to buy the weapon, make sure nothing fails
		player.grantGold(1);
		try {
			shop.buyItem(player, weapon);
		} catch (DuplicateItemException e) {
			Assert.fail();
		} catch (InsufficientException e) {
			Assert.fail();
		}
		Assert.assertTrue(player.getWeapons().contains(weapon));

		// Grant money and try again. Duplicate exception should be thrown.
		int pGold = PlayerConstants.START_GOLD + 1;
		player.grantGold(pGold);
		try {
			shop.buyItem(player, weapon);
		} catch (DuplicateItemException e) {
			Assert.assertTrue(player.getGold() == pGold);
		} catch (InsufficientException e) {
			Assert.fail();
		}
	}

	@Test
	public void itemSellTest() throws InsufficientException, DuplicateItemException {
		IWeapon weapon = new WeaponMock();
		shop.buyItem(player, weapon);

		try {
			shop.sellItem(player, weapon);
		} catch (GameException e) {
			Assert.fail();
		}
		double moneyAfterSelling = weapon.getCost() * ShopConstants.REFUND_PERCENTAGE;
		Assert.assertTrue(player.getGold() == moneyAfterSelling);
		Assert.assertFalse(player.getWeapons().contains(weapon));

		// Try to sell again, should not work, should gain no money
		try {
			shop.sellItem(player, weapon);
		} catch (GameException e) {
			Assert.assertTrue(player.getGold() == moneyAfterSelling);
		}
	}

	@Test
	public void getItemTest() throws FactoryException {
		Item kevlar = shop.getItem(ArmoursDirectory.KEVLAR, "armour");
		Assert.assertTrue(kevlar instanceof IArmour && kevlar.getName().equals("Kevlar"));

		Item shotgun = shop.getItem(WeaponsDirectory.SHOTGUN, "Weapon");
		Assert.assertTrue(shotgun instanceof Weapon && shotgun.getName().equals("Shotgun"));

		// Exception should be thrown when no such item exists
		try {
			shop.getItem(13, "Armour");
		} catch (FactoryException e) {
			Assert.assertTrue(true);
		}
	}

}
