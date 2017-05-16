package Mediator;

import GameModel.Item.Item;
import GameModel.Lootbox.ILootbox;
import GameModel.Player.IPlayer;
import Mediator.ServerModelMediator;
import com.corundumstudio.socketio.SocketIOClient;

import java.util.List;

/**
 * Created by latiif on 4/30/17.
 */
public interface IServer {
	void updateNearbyPlayers(Object player,List<Object> players);
	void updatePlayer(Object playerObject);

	void setMediator(ServerModelMediator serverModelMediator);

	void updateLootbox(Object player, List<Object> lootboxes);
	void playerSignin(Object p, SocketIOClient socketIOClient);

	void sendShopList(Object p, List<Object> list);

	void sendException(Object exception);
	void sendException(Object player,Object exception);
}
