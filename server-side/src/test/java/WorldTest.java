import GameModel.GameUtils.GeoDistance;
import GameModel.Player.GeoPos;
import GameModel.Player.Player;
import GameModel.World;
import org.junit.Test;

/**
 * Created by latiif on 4/1/17.
 */
public class WorldTest {
	@Test
	public void registerTest(){
		Player p1,p2;

		p1= new Player("player1",new GeoPos(0.0,0.0));
		p2= new Player("player2",new GeoPos(50.0,9.0));

		p1.goOnline();
		p2.goOnline();

		World world= new World();


		world.registerPlayer(p1);
		world.registerPlayer(p2);


		//world.playerChangePos(p1.getID());


	}


	@Test
	public void getDistance(){
		GeoPos p1 = new GeoPos(0.0,0.0);
		GeoPos p2 = new GeoPos(0.00001,0.0);

		System.out.println(GeoDistance.getDistance(p1,p2));
	}
}
