package Mediator;

import GameModel.Lootbox.ILootbox;
import GameModel.Player.GeoPos;
import GameModel.Player.IPlayer;
import com.corundumstudio.socketio.SocketIOClient;

import java.util.List;

/**
 * Created by latiif on 4/30/17.
 */
public interface IMediator {
	void updateNearbyPlayers(IPlayer IPlayer);
	void updatePlayer(IPlayer IPlayer);

	void performAttack(String s1,String s2);
	void playerChangePos(String id, GeoPos pos);
	IPlayer getPlayerById(String id);


	void registerPlayer(String ID);

	void consumeLootbox(String  playerId, GeoPos geoPos);

	void updateLootbox(IPlayer player, List<ILootbox> lootboxes);

	void changeWeapon(String  playerId,Integer weaponID);

	void playerSignin(IPlayer p, SocketIOClient socketIOClient);
}
