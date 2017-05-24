import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.WeaponsDirectory;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.WeaponsFactory;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Player.Avatar.Avatar;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Player.Player;
import com.cth.codetroopers.pixelwars.serverside.ServerController.Server;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.World;
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
		//world.addLootbox(new Lootbox(new GeoPos(-0.00001,-0.0001),250,5,0));

		new ServerModelMediator(server,world);


		IPlayer p2= null;
		IPlayer p1=null;
		try {
			p1= new Player("llusx",new GeoPos(0.0,0.0));

			p2 = new Player("sara",new GeoPos(0.0,0.0));

			p1.setHp(10.0);

			p2.setAvatar(Avatar.KARMA);

			p1.setExp(5500);
			p2.setExp(7500);

			p1.goOnline();
			p2.goOnline();



			p2.grantGold(2000);
			p2.buyItem(WeaponsFactory.createWeapon(WeaponsDirectory.SNIPER));


			world.registerPlayer(p1);
			world.registerPlayer(p2);

		} catch (GameException e) {
			e.printStackTrace();
		}



	}
}
