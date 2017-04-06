import GameModel.Player.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by latiif on 3/28/17.
 */

public class BasicTest {

	@Test
	public void hitDamageTest(){

		Player p1= new Player("p1");
		Player p2 = new Player("p2");


		p1.attackOtherPlayer(p2);

		Assert.assertEquals(new Double(p1.getHp()-4),p2.getHp());
	}

	@Test
	public void untilKilledTest(){
		Player p1= new Player("p1");
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

		Thread.sleep(player.weaponInHand.getCooldown()*1000-100);

		Assert.assertTrue(player.weaponInHand.getIsOnCooldown());

	}

	@Test
	public void getsKilledTest(){
		Player p1,p2,p3;

		p1=new Player("p1");
		p2=new Player("p2");
		p3=new Player("p3");




	}
}
