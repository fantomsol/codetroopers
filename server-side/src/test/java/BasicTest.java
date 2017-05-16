import GameModel.GameUtils.GeoPos;
import GameModel.Item.Weapons.WeaponsDirectory;
import GameModel.Item.Weapons.WeaponsFactory;
import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by latiif on 3/28/17.
 */

public class BasicTest {
/*
	@Test
	public void hitDamageTest(){

		IPlayer p1= new Player("p1");
		Player p2 = new Player("p2");


		p1.attackOtherPlayer(p2);

		Assert.assertEquals(new Double(p1.getHp()-4),p2.getHp());
	}

	@Test
	public void untilKilledTest(){
		IPlayer p1= new Player("p1");
		Player p2= new Player("p2");

		while (p2.getIsAlive()){
			p1.attackOtherPlayer(p2);
		}


		Assert.assertTrue(p2.getHp()<=0);
	}

	@Test
	public void attackFirstTest() throws InterruptedException {
		Player p1= new Player("p1");
		Player p2= new Player("p2");



		while (p2.getIsAlive() && p1.getIsAlive()){
			p2.attackOtherPlayer(p1);
			p1.attackOtherPlayer(p2);

			Thread.sleep(1000);
		}


		Assert.assertTrue(!(p1.getIsAlive()));

	}

	@Test
	public void isOnCooldownTest() throws InterruptedException {
		Player player= new Player("player");

		player.attackOtherPlayer(new Player("dummy"));

		Thread.sleep(player.weaponEquipped.getCooldown()*1000-100);

		Assert.assertTrue(player.weaponEquipped.getIsOnCooldown());

	}

	@Test
	public void getsKilledTest(){
		IPlayer p1,p2,p3;

		p1=new Player("p1", new GeoPos(0.0,0.0));
		p2=new Player("p2", new GeoPos(0.0,0.0));

		p1.goOnline();
		p2.goOnline();

		p1.setHp(1.0);
		p2.grantWeapon(WeaponsFactory.createWeapon(WeaponsDirectory.SHOTGUN));

		p2.attackOtherPlayer(p1);

		Assert.assertTrue(p1.getIsAlive()==false);

		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Assert.assertTrue(p1.getIsAlive());


	}
	*/
}
