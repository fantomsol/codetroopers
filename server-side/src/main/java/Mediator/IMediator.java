package Mediator;

import GameModel.Item.Item;
import GameModel.Lootbox.ILootbox;
import GameModel.GameUtils.GeoPos;
import GameModel.Player.IPlayer;
import GameModel.ServerController.IServer;
import GameModel.WorldPackage.World;
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

	void setWorld(World world);
	void setServer(IServer server);

	void registerPlayer(String ID);

	void consumeLootbox(String  playerId, GeoPos geoPos);

	void updateLootbox(IPlayer player, List<ILootbox> lootboxes);

	void changeWeapon(String  playerId,Integer weaponID);

	List<Item> getShopItems();

	void updatePlayerShopItems(IPlayer p,List<Item> list);

	void buyItem(IPlayer player,Integer itemID,String itemType);
	void sellItem(IPlayer player,Integer itemID, String itemType);

	void playerSignin(IPlayer p, SocketIOClient socketIOClient);
}
