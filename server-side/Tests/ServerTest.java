import GameModel.Player.GeoPos;
import GameModel.Player.Player;
import GameModel.ServerController.Server;
import org.junit.Test;

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
}
