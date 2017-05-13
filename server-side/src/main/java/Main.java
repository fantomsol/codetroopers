import GameModel.Item.Weapons.WeaponsDirectory;
import GameModel.Item.Weapons.WeaponsFactory;
import GameModel.Lootbox.Lootbox;
import GameModel.GameUtils.GeoPos;
import GameModel.Player.Avatar.Avatar;
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

		//world.addLootbox(new Lootbox(new GeoPos(0.00001,0.0001),250,3,1));
		world.addLootbox(new Lootbox(new GeoPos(-0.00001,-0.0001),250,5,0));

		new ServerModelMediator(server,world);


		IPlayer p1= new Player("llusx",new GeoPos(0.0,0.0));
		IPlayer p2=new Player("sara",new GeoPos(0.0,0.0));

		world.registerPlayer(p1);
		world.registerPlayer(p2);

		p2.setAvatar(Avatar.KARMA);

		p1.setExp(5000);
		p2.setExp(20000);

		//p1.goOnline();
		p2.goOnline();
		p2.grantGold(2000);
		p2.buyItem(WeaponsFactory.createWeapon(WeaponsDirectory.SNIPER));

	}
}
