import GameModel.GameUtils.Exceptions.EmptyLootbox;
import GameModel.GameUtils.Exceptions.FactoryException;
import GameModel.GameUtils.Exceptions.GameException;
import GameModel.Lootbox.ILootbox;
import GameModel.Lootbox.Lootbox;
import GameModel.GameUtils.GeoPos;
import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import Mocks.PlayerMock;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by latiif on 5/7/17.
 */
public class LootboxTest {

	@Test
	public void goldIncrementTest(){
		IPlayer player=new PlayerMock();

		ILootbox lootbox= new Lootbox(null,250,1,1);

		int oldGold=player.getGold();

		try {
			lootbox.consume(player);
		} catch (EmptyLootbox e) {
			Assert.assertTrue(1==2);
		} catch (FactoryException e){
			Assert.assertTrue(1==2);
		}

		Assert.assertTrue(player.getGold()==oldGold+250);
	}

	@Test
	public void emptyLootboxTest(){
		IPlayer player= new PlayerMock();
		ILootbox lootbox1= new Lootbox(null,200,0,1);

		try {
			lootbox1.consume(player);
		} catch (FactoryException e) {
			//Shouldn't ask the factory to create a non existing weapon
			Assert.assertTrue(1==2);
		} catch (EmptyLootbox emptyLootbox) {
			//Should throw this exception
			Assert.assertTrue(true);
		}


		ILootbox lootbox2= new Lootbox(null,0,1,0);
		try {
			lootbox2.consume(player);
		} catch (FactoryException e) {
			//Shouldn't ask the factory to create a non existing armour
			Assert.assertTrue(1==2);
		} catch (EmptyLootbox emptyLootbox) {
			//Should throw this exception
			Assert.assertTrue(true);
		}
	}
}
