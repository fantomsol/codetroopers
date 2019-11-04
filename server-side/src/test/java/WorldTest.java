import Mediator.ServerModelMediator;
import Mocks.ServerMock;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.FactoryException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GeographicalException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Item.Armours.ArmoursDirectory;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.*;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.Lootbox.ILootbox;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.Lootbox.Lootbox;
import com.cth.codetroopers.pixelwars.serverside.Player.PlayerUtils.Avatar;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Player.Player;
import com.cth.codetroopers.pixelwars.serverside.Player.PlayerConstants;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.World;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by latiif on 4/1/17.
 *
 * The only methods not involved in tests are a few of the getter methods in the world class.
 */
public class WorldTest {

	private World world = new World();
	private IPlayer p1, p2;
	private GeoPos pos;
	private ServerModelMediator mediator = new ServerModelMediator(new ServerMock(), world);

	@Before
	public void initTestWorld() throws FactoryException, GeographicalException {
		pos = new GeoPos(0.0, 0.0);
		p1 = new Player("p1", pos);
		p2 = new Player("p2", pos);
		p1.goOnline();
		p2.goOnline();
		world.registerPlayer(p1);
		world.registerPlayer(p2);
	}

	@Test
	public void createNewPlayerTest() throws GameException {
		world.createNewPlayer("Luna");
		Assert.assertTrue(world.getPlayers().containsKey("Luna"));
		Assert.assertTrue(world.getPlayers().size() == 3);
	}

	@Test
	public void setMediatorTest() {
		ServerMock server = new ServerMock();
		ServerModelMediator newMediator = new ServerModelMediator(server, world);
		world.setMediator(newMediator);
		Assert.assertTrue(world.getMediator().equals(newMediator));
	}

	@Test
	public void changeWeaponTest() throws GameException {
		Assert.assertTrue(p1.getWeaponEquipped().equals(p1.getWeapons().get(0)));
		world.changeWeapon("p1", WeaponsDirectory.WHITEFLAG);
		Assert.assertTrue(p1.getWeaponEquipped().equals(p1.getWeapons().get(1)));
	}

	@Test
	public void addLootboxTest() {
		ILootbox lootbox = new Lootbox(pos, 2, 2, 2);
		world.addLootbox(lootbox);
		Assert.assertTrue(world.getLootboxes().contains(lootbox));
	}

	@Test
	public void performAttackTest() throws GameException, InterruptedException {
		world.performAttack("p1", "p2");

		int weaponDamage = p1.getWeaponEquipped().fireWeapon();
		Double targetDamage = PlayerConstants.damageCaluculation(weaponDamage, p2.getArmour());
		Assert.assertTrue(p2.getHp() == (PlayerConstants.MAX_HEALTH) - targetDamage);

		p2.switchWeapon(WeaponsDirectory.WHITEFLAG);
		Integer attackerExp = p1.getExp();
		TimeUnit.SECONDS.sleep(p1.getWeaponEquipped().getCooldown());
		world.performAttack("p1", "p2");
		Assert.assertTrue(p1.getExp() < attackerExp); //Should lose exp when attacking white flag

		// Check so that a dead player can't be attacked
		p2.setIsAlive(false);
		try {
			TimeUnit.SECONDS.sleep(p1.getWeaponEquipped().getCooldown());
			world.performAttack("p1", "p2");
		} catch (GameException e) {
			Assert.assertTrue(true); //Exception should be thrown here
		}
		// Make sure revive works
		try {
			TimeUnit.SECONDS.sleep(PlayerConstants.RESPAWN_COOLDWON);
			world.performAttack("p1", "p2");
			Assert.assertTrue(p2.getIsAlive());
		} catch (GameException e) {

		}
	}

	@Test
	public void registerTest() {
		Assert.assertTrue(world.getPlayers().containsKey("p1"));
		Assert.assertTrue(world.getPlayers().containsKey("p2"));
		Assert.assertTrue(world.getPlayers().size() == 2);
	}

	@Test
	public void getPlayerByIdTest() throws GameException {
		Assert.assertTrue(world.getPlayerById("p1") == p1);
	}

	// Also tests goOfflinePlayer
	@Test
	public void playerChangePosTest() throws GameException, InterruptedException {
		// Move p1 within vincity of p2 (Nothing should happen)
		GeoPos nearPos = new GeoPos(0.0, 0.0001);
		world.playerChangePos("p1", nearPos);
		Assert.assertTrue(p1.getPlayersNearby().contains(p2));
		Assert.assertTrue(p2.getPlayersNearby().contains(p1));

		// Move p1 out of vincity of p2
		GeoPos farPos = new GeoPos(10.0, 10.0);
		world.playerChangePos("p1", farPos);
		Assert.assertFalse(p1.getPlayersNearby().contains(p2));
		Assert.assertFalse(p2.getPlayersNearby().contains(p1));

		// Move p1 back into vincity of p2
		world.playerChangePos("p1", pos);
		Assert.assertTrue(p1.getPlayersNearby().contains(p2));
		Assert.assertTrue(p2.getPlayersNearby().contains(p1));

		// Let p1 go offline and make sure p1 and p2 can't see one another
		// The lines below also test the private method goOfflinePlayer in World.
		TimeUnit.SECONDS.sleep(p1.getOfflineCooldown());
		p1.goOffline();

		world.playerChangePos("p1", nearPos);
		Assert.assertTrue(p1.getPlayersNearby().isEmpty());
		Assert.assertFalse(p2.getPlayersNearby().contains(p1));
	}

	@Test
	public void updateLootboxesTest() {
		Lootbox lootbox = new Lootbox(pos, 100, WeaponsDirectory.SNIPER, ArmoursDirectory.KEVLAR);
		world.addLootbox(lootbox);
		world.updateLootboxes(p1);
		Assert.assertTrue(p1.getVisibleLootboxes().contains(lootbox));
	}

	@Test
	public void setPlayerAvatarTest() {
		world.setPlayerAvatar(p1, "LOTUS");
		Assert.assertTrue(p1.getAvatar().equals(Avatar.LOTUS));
	}

	@Test
	public void consumeLootboxByGeoPosTest() throws GameException {
		ILootbox lootbox = new Lootbox(pos, 100, WeaponsDirectory.ASSAULT_RIFLE, ArmoursDirectory.HELMET);
		world.addLootbox(lootbox);
		world.consumeLootboxByGeoPos("p1", pos);

		Assert.assertTrue(p1.getGold().equals(PlayerConstants.START_GOLD + 100));
		Assert.assertTrue(p1.getWeapons().get(2).getId().equals(WeaponsDirectory.ASSAULT_RIFLE));
		Assert.assertTrue(p1.getArmours().get(1).getId().equals(ArmoursDirectory.HELMET));
		Assert.assertFalse(world.getLootboxes().contains(lootbox));

	}

}
