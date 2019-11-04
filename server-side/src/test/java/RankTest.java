import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.*;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Player.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lumo on 05/05/17.
 */
public class RankTest {

	Player nemo;
	Player fafne;
	//World world = new World();

	@Before
	public void initRankTest() throws GeographicalException, FactoryException {
		nemo = new Player("nemo", new GeoPos(0.0, 0.0));
		fafne = new Player("fafne", new GeoPos(0.0, 0.0));
		//world.registerPlayer(nemo);
		//world.registerPlayer(fafne);
		//world.setMediator(new ServerModelMediator(new ServerMock(), world));
	}

	@Test
	public void killEqual() throws CooldownException, CombatException {
		nemo.setHp(1.0);
		nemo.setExp(400);
		fafne.setHp(1.0);
		fafne.setExp(400);
		nemo.attackOtherPlayer(fafne);
		Assert.assertTrue(nemo.getExp() == 450);
		Assert.assertTrue(fafne.getExp() == 400 * 0.9);
	}

	/*@Test
	public void killUnArmed() throws GameException {
		fafne.grantWeapon(WeaponsFactory.createWeapon(WeaponsDirectory.WHITEFLAG));
		fafne.switchWeapon(WeaponsDirectory.WHITEFLAG);
		fafne.setHp(1.0);

		nemo.grantWeapon(WeaponsFactory.createWeapon(WeaponsDirectory.SHOTGUN));
		nemo.switchWeapon(WeaponsDirectory.SHOTGUN);

		int exp = nemo.getExp();

		world.performAttack("nemo", "fafne");

		Assert.assertTrue(!fafne.getIsAlive());
		Assert.assertTrue(nemo.getExp().equals(exp - 10));
	}*/


	@Test
	public void killStrong() throws FactoryException, CooldownException, CombatException, GeographicalException {
		IPlayer nemo = new Player("nemo", new GeoPos(0.0, 0.0));
		nemo.setHp(1.0);
		nemo.setExp(400);
		Player fafne = new Player("fafne", new GeoPos(0.0, 0.0));
		fafne.setHp(1.0);
		fafne.setExp(800);

		nemo.attackOtherPlayer(fafne);
		Assert.assertTrue(nemo.getExp() == 600);
		Assert.assertTrue(fafne.getExp() == 720);
	}


}
