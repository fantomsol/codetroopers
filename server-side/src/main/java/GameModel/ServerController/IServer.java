package GameModel.ServerController;

import GameModel.Lootbox.ILootbox;
import GameModel.Player.IPlayer;
import Mediator.ServerModelMediator;
import com.corundumstudio.socketio.SocketIOClient;

import java.util.List;

/**
 * Created by latiif on 4/30/17.
 */
public interface IServer {
	void updateNearbyPlayers(IPlayer IPlayer);
	void updatePlayer(IPlayer IPlayer);

	void setMediator(ServerModelMediator serverModelMediator);

	void updateLootbox(IPlayer player, List<ILootbox> lootboxes);
	void playerSignin(IPlayer p, SocketIOClient socketIOClient);
}
