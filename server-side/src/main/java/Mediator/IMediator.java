package Mediator;

import GameModel.Player.GeoPos;
import GameModel.Player.IPlayer;
import com.corundumstudio.socketio.SocketIOClient;

/**
 * Created by latiif on 4/30/17.
 */
public interface IMediator {
	void updateNearbyPlayers(IPlayer IPlayer);
	void updatePlayer(IPlayer IPlayer);

	void performAttack(String s1,String s2);
	void playerChangePos(String id, GeoPos pos);
	IPlayer getPlayerById(String id);

	void changeWeapon(String  playerId,Integer weaponID);

	void playerSignin(IPlayer p, SocketIOClient socketIOClient);
}
