package Mediator;

import GameModel.Player.GeoPos;
import GameModel.Player.Player;
import com.corundumstudio.socketio.SocketIOClient;

/**
 * Created by latiif on 4/30/17.
 */
public interface IMediator {
	void updateNearbyPlayers(Player player);
	void updatePlayer(Player player);

	void performAttack(String s1,String s2);
	void playerChangePos(String id, GeoPos pos);
	Player getPlayerById(String id);

	void changeWeapon(String  playerId,Integer weaponID);

	void playerSignin(Player p, SocketIOClient socketIOClient);
}
