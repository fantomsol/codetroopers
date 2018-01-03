package Mediator;

import com.cth.codetroopers.pixelwars.serverside.Beings.Being;
import com.cth.codetroopers.pixelwars.serverside.Beings.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Item.Item;
import com.cth.codetroopers.pixelwars.serverside.Lootbox.ILootbox;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.corundumstudio.socketio.SocketIOClient;
import com.cth.codetroopers.pixelwars.serverside.ServerController.IServer;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.World;

import java.util.List;

/**
 * Created by latiif on 4/30/17.
 */
public interface IMediator {
	void updateNearbyPlayers(IPlayer player);

	void updatePlayer(Being character);

	void performAttack(String c1, String c2);

	void playerChangePos(String id, Double latitude,Double longitude);

	Being getCharacterById(String id);

	void setWorld(World world);

	void setServer(IServer server);

	void registerPlayer(String ID);

	void sendPlayerInfo(String playerId);

	void getShopItems(String id);

	void changeRadarStatus(String id,boolean wantedStatus) throws GameException;

	void consumeLootbox(String  playerId, GeoPos geoPos);

	void updateLootbox(Being player, List<ILootbox> lootboxes);

	void changeWeapon(String  playerId,Integer weaponID);

	List<Item> getShopItems();

	void updatePlayerShopItems(Being p, List<Item> list);

	void buyItem(String id,Integer itemID,String itemType);

	void sellItem(String id,Integer itemID, String itemType);

	void playerSignin(String id, SocketIOClient socketIOClient);

	void changeAvatar(String playerId, String avatarId);

	void sendPlayerSpecificException(Being p, GameException gameException);

	void sendGameException(GameException gameException);
}
