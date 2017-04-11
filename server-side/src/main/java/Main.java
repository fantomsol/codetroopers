import GameModel.Player.GeoPos;
import GameModel.Player.Player;
import GameModel.WorldPackage.World;

/**
 * Created by latiif on 4/11/17.
 */
public class Main {
	public static void main(String[] args) {
		World world= new World();

		Player p1= new Player("llusx",new GeoPos(0.0,0.0));
		Player p2=new Player("sara",new GeoPos(0.0,0.0));

		world.registerPlayer(p1);
		world.registerPlayer(p2);

		p1.goOnline();
		p2.goOnline();

		p2.updatePos(new GeoPos(38.63473,-90.29408));
	}
}
