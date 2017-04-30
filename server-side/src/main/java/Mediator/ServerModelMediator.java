package Mediator;

import GameModel.Player.GeoPos;
import GameModel.Player.Player;
import GameModel.ServerController.IServer;
import GameModel.WorldPackage.World;
import com.corundumstudio.socketio.SocketIOClient;

/**
 * Created by latiif on 4/30/17.
 */
public class ServerModelMediator implements IMediator {

	private IServer server;
	private World world;

	public ServerModelMediator(IServer server, World world)
	{
		this.world=world;
		this.server=server;

		world.setMediator(this);
		server.setMediator(this);

	}


	public void updateNearbyPlayers(Player player) {
		server.updateNearbyPlayers(player);
	}

	public void updatePlayer(Player player) {
		server.updatePlayer(player);
	}

	public void performAttack(String s1, String s2) {
		world.performAttack(s1,s2);
	}

	public void playerChangePos(String id, GeoPos pos) {
		world.playerChangePos(id,pos);
	}

	public Player getPlayerById(String id) {
		return world.getPlayerById(id);
	}

	public void playerSignin(Player p, SocketIOClient socketIOClient) {
		server.playerSignin(p,socketIOClient);
	}
}
