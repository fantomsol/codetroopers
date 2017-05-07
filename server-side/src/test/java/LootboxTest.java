import GameModel.Lootbox.ILootbox;
import GameModel.Lootbox.Lootbox;
import GameModel.Player.GeoPos;
import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by latiif on 5/7/17.
 */
public class LootboxTest {

	@Test
	public void spawnTest(){
		IPlayer player=new Player("salma",new GeoPos(0.0,0.0));
		ILootbox lootbox= new Lootbox(new GeoPos(0.0,0.0),250,0,2);

		int oldGold=player.getGold();


		player.consume(lootbox);

		Assert.assertTrue(player.getGold()==oldGold+250);


	}
}
