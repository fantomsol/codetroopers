package Mediator;

import com.cth.codetroopers.pixelwars.serverside.Item.Item;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.Lootbox.ILootbox;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.corundumstudio.socketio.SocketIOClient;
import com.cth.codetroopers.pixelwars.serverside.ServerController.IServer;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.World;

import java.util.List;

/**
 * Created by latiif on 4/30/17.
 */
public interface IMediator {
	void updateNearbyPlayers(IPlayer IPlayer);
	void updatePlayer(IPlayer IPlayer);

	void performAttack(String s1,String s2);
	void playerChangePos(String id, Double latitude,Double longitude);
	IPlayer getPlayerById(String id);

	void setWorld(World world);
	void setServer(IServer server);

	void registerPlayer(String ID);

	void sendPlayerInfo(String playerId);

	void getShopItems(String id);

	void changeRadarStatus(String id,boolean wantedStatus) throws GameException;

	void consumeLootbox(String  playerId, GeoPos geoPos);

	void updateLootbox(IPlayer player, List<ILootbox> lootboxes);

	void changeWeapon(String  playerId,Integer weaponID);

	List<Item> getShopItems();

	void updatePlayerShopItems(IPlayer p,List<Item> list);

	void buyItem(String id,Integer itemID,String itemType);
	void sellItem(String id,Integer itemID, String itemType);

	void playerSignin(String id, SocketIOClient socketIOClient);

	void changeAvatar(String playerId, String avatarId);

	void sendPlayerSpecificException(IPlayer p,GameException gameException);
	void sendException(GameException gameException);

	public IServer getServer();

	public World getWorld();
}
