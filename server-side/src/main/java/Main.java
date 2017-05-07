import GameModel.Item.Weapons.WeaponsDirectory;
import GameModel.Item.Weapons.WeaponsFactory;
import GameModel.Player.GeoPos;
import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import GameModel.ServerController.Server;
import GameModel.WorldPackage.World;
import Mediator.ServerModelMediator;

/**
 * Created by latiif on 4/11/17.
 */
public class Main {
	public static void main(String[] args) {
		World world= new World();

		Server server= new Server();
		server.startServer();

		ServerModelMediator modelMediator= new ServerModelMediator(server,world);


		IPlayer p1= new Player("llusx",new GeoPos(0.0,0.0));
		IPlayer p2=new Player("sara",new GeoPos(0.0,0.0));

		world.registerPlayer(p1);
		world.registerPlayer(p2);

		p1.goOnline();
		p2.goOnline();
		p2.grantGold(2000);
		p2.buyItem(WeaponsFactory.createWeapon(WeaponsDirectory.SNIPER));

	}
}
