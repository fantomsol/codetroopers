import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.CooldownException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.FactoryException;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Player.Player;
import com.cth.codetroopers.pixelwars.serverside.Player.PlayerConstants;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;


/**
 * Created by latiif on 3/29/17.
 */
public class RadarTest {
	@Test
	public void goOnlineTest() throws InterruptedException, FactoryException {

		IPlayer p=new Player("p");
		p.goOnline();

		Assert.assertTrue(p.isOnline());
		Assert.assertTrue(!p.getCanGoOffline());

		//Add one second margin to make sure
		TimeUnit.SECONDS.sleep(PlayerConstants.START_COOLDOWN+1);


		try {
			p.goOffline();
		} catch (CooldownException e) {
			//Should be able to go offline
			Assert.assertTrue(1==2);
		}

		Assert.assertTrue(!p.isOnline());

		p.goOnline();

		Assert.assertTrue(p.isOnline());

	}

}


