import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.CombatException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.CooldownException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.FactoryException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GeographicalException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Player.Player;
import com.cth.codetroopers.pixelwars.serverside.Player.PlayerConstants;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by latiif on 3/28/17.
 */

public class PlayerTest {

	@Test
	public void hitDamageTest(){

		IPlayer p1 =null;
		IPlayer p2 = null;
		try {
			p2 = new Player("p2",new GeoPos(0.0,0.0));
			p1= new Player("p1", new GeoPos(0.0,0.0));

		} catch (FactoryException e) {
			//Should not throw a factory exception when creating
			Assert.assertTrue(1==2);
		} catch (GeographicalException e) {
			//Those are valid coordinates
			Assert.assertTrue(1==2);
		}

		//Should be created and fine
		Assert.assertTrue(p1!=null && p2!=null);

		try {
			p1.attackOtherPlayer(p2);
		} catch (CooldownException e) {
			Assert.assertTrue(1==2);
		} catch (CombatException e) {
			Assert.assertTrue(1==2);
		}

		double hpDiff= PlayerConstants.damageCaluculation(p1.getWeaponEquipped().getDamage(),p2.getArmour());

		Assert.assertEquals(Double.valueOf(p1.getHp()-hpDiff),p2.getHp());
	}



	@Test
	public void deathTest() throws FactoryException, CooldownException, CombatException, GeographicalException {
		IPlayer p1 = new Player("p1", new GeoPos(0.0,0.0));
		IPlayer p2 =new Player("p2", new GeoPos(0.0,0.0));


		p2.setHp(2.0);

		while (p2.getIsAlive()){
			p1.attackOtherPlayer(p2);
		}


		Assert.assertTrue(!(p2.getIsAlive()) && p2.getHp().doubleValue()<=0.0);

	}


	@Test
	public void respawnTest() throws FactoryException, InterruptedException {
		IPlayer p1;

		p1=new Player("p1");

		p1.setHp(1.0);

		p1.getAttacked(9001);

		Assert.assertTrue(!p1.getIsAlive());
		TimeUnit.SECONDS.sleep(PlayerConstants.RESPAWN_COOLDWON+1);

		Assert.assertTrue(p1.getIsAlive());
		Assert.assertTrue(p1.getHp().equals(PlayerConstants.MAX_HEALTH));

	}

}
