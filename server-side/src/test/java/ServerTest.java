import GameModel.Player.GeoPos;
import GameModel.Player.Player;
import GameModel.ServerController.Server;
import com.eclipsesource.json.JsonArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by latiif on 4/1/17.
 */
public class ServerTest {

	@Test
	public void basicWorldTest(){
		Server.startServer();

		Player p1=new Player("Llusx",new GeoPos(0.0,0.0));
		Player p2= new Player("test",new GeoPos(0.0,0.0));

		p1.goOnline();;
		p2.goOnline();

		Server.world.registerPlayer(p1);
		Server.world.registerPlayer(p2);



	}

	@Test
	public void list2jsonarrayTest(){
		List<String> list= new ArrayList<String>();

		list.add("item1");
		list.add("item2");


		JsonArray jsonA = Server.list2JsonArray(list);

		Assert.assertTrue(jsonA!=null);
		Assert.assertEquals(2,jsonA.size());

	}
}
