package GameModel.ServerController;

import GameModel.Player.IPlayer;
import Mediator.ServerModelMediator;
import com.corundumstudio.socketio.SocketIOClient;

/**
 * Created by latiif on 4/30/17.
 */
public interface IServer {
	void updateNearbyPlayers(IPlayer IPlayer);
	void updatePlayer(IPlayer IPlayer);

	void setMediator(ServerModelMediator serverModelMediator);

	void playerSignin(IPlayer p, SocketIOClient socketIOClient);
}
