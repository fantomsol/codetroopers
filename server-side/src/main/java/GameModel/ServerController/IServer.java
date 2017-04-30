package GameModel.ServerController;

import GameModel.Player.Player;
import Mediator.ServerModelMediator;
import com.corundumstudio.socketio.SocketIOClient;

/**
 * Created by latiif on 4/30/17.
 */
public interface IServer {
	void updateNearbyPlayers(Player player);
	void updatePlayer(Player player);

	void setMediator(ServerModelMediator serverModelMediator);

	void playerSignin(Player p, SocketIOClient socketIOClient);
}
