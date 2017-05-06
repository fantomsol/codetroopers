package Mediator;

import GameModel.Player.GeoPos;
import GameModel.Player.IPlayer;
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


	public void updateNearbyPlayers(IPlayer IPlayer) {
		server.updateNearbyPlayers(IPlayer);
	}

	public void updatePlayer(IPlayer IPlayer) {
		server.updatePlayer(IPlayer);
	}

	public void performAttack(String s1, String s2) {
		world.performAttack(s1,s2);
	}

	public void playerChangePos(String id, GeoPos pos) {
		world.playerChangePos(id,pos);
	}

	public IPlayer getPlayerById(String id) {
		return world.getPlayerById(id);
	}

	public void changeWeapon(String playerId, Integer weaponID) {
		world.changeWeapon(playerId,weaponID);
	}

	public void playerSignin(IPlayer p, SocketIOClient socketIOClient) {
		server.playerSignin(p,socketIOClient);
	}
}
