import Mediator.ServerModelMediator;
import Mocks.ServerMock;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.FactoryException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GeographicalException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.AssaultRifle;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.WeaponsDirectory;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.Lootbox.Lootbox;
import com.cth.codetroopers.pixelwars.serverside.Player.PlayerUtils.Avatar;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Player.PlayerConstants;
import com.cth.codetroopers.pixelwars.serverside.ServerController.IServer;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.World;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServerModelMediatorTest {

	private ServerModelMediator mediator;
	private IServer server;
	private World world;

	@Before
	public void initServerModelMediatorTest(){
		this.world = new World();
		this.server = new ServerMock();
		this.mediator = new ServerModelMediator(server, world);
	}

	@Test
	public void registerPlayer() throws GameException {
		mediator.registerPlayer("Luna");
		try {
			world.getPlayerById("Luna");
		} catch (GameException e){
			Assert.fail(); // No exception should be thrown here
		}
	}

	@Test
	public void getShopItems() {
		//TODO
	}

	@Test
	public void changeRadarStatus() throws GameException, InterruptedException {
		registerPlayer();
		IPlayer luna = world.getPlayerById("Luna");
		mediator.changeRadarStatus("Luna", false);
		Assert.assertFalse(luna.getOnlineStatus());
		mediator.changeRadarStatus("Luna", true);
		Assert.assertTrue(luna.getOnlineStatus());
	}

	@Test
	public void updateNearbyPlayers() {
		//TODO
	}

	@Test
	public void updatePlayer() {
		//TODO
	}

	@Test
	public void performAttack() {
		mediator.registerPlayer("p1");
		mediator.registerPlayer("p2");
		mediator.performAttack("p1", "p2");
		// Just make sure the player took damage
		Assert.assertTrue(mediator.getPlayerById("p2").getHp() < PlayerConstants.MAX_HEALTH);
	}

	@Test
	public void playerChangePos() throws GeographicalException {
		mediator.registerPlayer("p1");
		mediator.playerChangePos("p1", 3.0,4.0);
		Assert.assertTrue(mediator.getPlayerById("p1").getGeoPos().getLatitude() == 3.0);
		Assert.assertTrue(mediator.getPlayerById("p1").getGeoPos().getLongitude() == 4.0);
		mediator.playerChangePos("p1", new GeoPos(8.0,9.0));
		Assert.assertTrue(mediator.getPlayerById("p1").getGeoPos().getLatitude() == 8.0);
		Assert.assertTrue(mediator.getPlayerById("p1").getGeoPos().getLongitude() == 9.0);

	}

	@Test
	public void getPlayerById() {
		// Tested in lots of places
	}

	@Test
	public void setWorld() {
		World newWorld = new World();
		mediator.setWorld(newWorld);
		Assert.assertTrue(mediator.getWorld().equals(newWorld));
	}

	@Test
	public void setServer() {
		IServer newServer = new ServerMock();
		mediator.setServer(newServer);
		Assert.assertTrue(mediator.getServer().equals(newServer));
	}

	@Test
	public void consumeLootbox() throws GeographicalException {
		mediator.registerPlayer("Luna");
		GeoPos pos = new GeoPos(0.0,0.0);
		Lootbox lootbox = new Lootbox(pos,2,2,2);
		world.addLootbox(lootbox);
		mediator.consumeLootbox("Luna", pos);
		Assert.assertTrue(world.getLootboxes().isEmpty());
	}

	@Test
	public void updateLootbox() {
		//TODO
	}

	@Test
	public void sendPlayerInfo() {
		//TODO
	}

	@Test
	public void changeWeapon() {
		mediator.registerPlayer("Luna");
		IPlayer luna = mediator.getPlayerById("Luna");
		AssaultRifle rifle = new AssaultRifle();
		luna.grantWeapon(rifle);
		mediator.changeWeapon("Luna", WeaponsDirectory.ASSAULT_RIFLE);
		Assert.assertTrue(luna.getWeaponEquipped().equals(rifle));
	}

	@Test
	public void testGetShopItems() throws FactoryException {
		// Just tests that the size of the returned list is correct, details tested in lower test class levels
		Assert.assertTrue(mediator.getShopItems().size() == 10);
	}

	@Test
	public void updatePlayerShopItems() {
		//TODO
	}

	@Test
	public void buyItem() {
		//TODO
	}

	@Test
	public void sellItem() {
		//TODO
	}

	@Test
	public void playerSignin() {
		//TODO
	}

	@Test
	public void changeAvatar() {
		mediator.registerPlayer("Sid");
		mediator.changeAvatar("Sid", "TERRY");
		Assert.assertTrue(mediator.getPlayerById("Sid").getAvatar().equals(Avatar.TERRY));
	}

	@Test
	public void sendPlayerSpecificException() {
		//TODO
	}

	@Test
	public void sendException() {
		//TODO
	}
}
