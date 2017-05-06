import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by latiif on 3/29/17.
 */
public class RadarTest {
	@Test
	public void goOnlineTest() throws InterruptedException {
		IPlayer p=new Player("p");
		p.goOnline();

		Assert.assertTrue(p.isOnline());
		Assert.assertTrue(!p.getCanGoOffline());

		p.goOffline();

		Assert.assertTrue(p.isOnline());

		Thread.sleep(p.getOfflineCooldown()*1000);

		Assert.assertTrue(p.isOnline());

	}

	@Test
	public void goOfflineTest() throws InterruptedException {
		IPlayer p= new Player("p");

		p.goOnline();
		Thread.sleep(p.getOfflineCooldown()*1000+10);

		Assert.assertTrue(p.getCanGoOffline());
		p.goOffline();
		Assert.assertTrue(!p.isOnline());

	}

}


