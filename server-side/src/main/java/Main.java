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
		IPlayer p3=null;
		IPlayer p4= null;
		IPlayer p1=null;
		try {
			p1= new Player("Siboan",new GeoPos(57.689459,11.976648));

			p2 = new Player("Karma",new GeoPos(57.688656,11.977313));

			p3 = new Player("Kyle",new GeoPos(57.689442, 11.979899));


			p4= new Player("Jim",new GeoPos(57.689235, 11.978043));


			p1.setAvatar(Avatar.SIBOAN);
			p2.setAvatar(Avatar.KARMA);
			p3.setAvatar(Avatar.KYLE);
			p4.setAvatar(Avatar.JIM);


			p1.goOnline();
			p2.goOnline();
			p3.goOnline();


			world.registerPlayer(p1);
			world.registerPlayer(p2);
			world.registerPlayer(p3);
			world.registerPlayer(p4);

		} catch (GameException e) {
			e.printStackTrace();
		}



	}
}
