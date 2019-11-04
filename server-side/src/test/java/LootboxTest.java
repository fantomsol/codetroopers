import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.EmptyLootbox;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.FactoryException;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.Lootbox.ILootbox;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.Lootbox.Lootbox;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
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
			Assert.assertTrue(false);
		} catch (FactoryException e){
			Assert.assertTrue(false);
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
