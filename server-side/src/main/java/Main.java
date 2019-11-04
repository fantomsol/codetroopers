import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.Lootbox.Lootbox;
import com.cth.codetroopers.pixelwars.serverside.Player.PlayerUtils.Avatar;
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
		World world = new World();

		Server server = new Server();
		server.startServer();


		new ServerModelMediator(server, world);


		IPlayer p2 = null;
		IPlayer p3 = null;
		IPlayer p4 = null;
		IPlayer p1 = null;
		IPlayer tester = null;
		try {


			p1 = new Player("Siboan", new GeoPos(57.689288, 11.972061));

			p2 = new Player("Karma", new GeoPos(57.688656, 11.977313));

			p3 = new Player("Kyle", new GeoPos(57.689442, 11.979899));

			p4 = new Player("Jim", new GeoPos(57.689235, 11.978043));


			world.addLootbox(new Lootbox(new GeoPos(57.692545, 11.981632), 250, 3, 1));
			world.addLootbox(new Lootbox(new GeoPos(57.687028, 11.978112), 250, 5, 0));

			tester = new Player("Pixel", new GeoPos(57.690148, 11.973070));

		} catch (GameException e) {
			e.printStackTrace();
		}


		p1.setAvatar(Avatar.SIBOAN);
		p2.setAvatar(Avatar.KARMA);
		p3.setAvatar(Avatar.KYLE);
		p4.setAvatar(Avatar.JIM);

		p1.setHp(50.0);
		p2.setHp(30.0);
		p3.setHp(10.0);

		tester.setAvatar(Avatar.LOTUS);

		tester.grantGold(1000);

		p1.setExp(500);
		p2.setExp(3000);
		p3.setExp(6000);
		p4.setExp(9500);

		p1.goOnline();
		p2.goOnline();
		p3.goOnline();
		p4.goOnline();

		world.registerPlayer(tester);
		world.registerPlayer(p1);
		world.registerPlayer(p2);
		world.registerPlayer(p3);
		world.registerPlayer(p4);
	}
}
