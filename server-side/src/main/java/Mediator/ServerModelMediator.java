package Mediator;

import GameModel.GameUtils.Exceptions.GameException;
import GameModel.Item.Item;
import GameModel.Lootbox.ILootbox;
import GameModel.GameUtils.GeoPos;
import GameModel.Player.IPlayer;
import com.corundumstudio.socketio.SocketIOClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by latiif on 4/30/17.
 */
public class ServerModelMediator implements IMediator {

	private IServer server;
	private IWorld world;

	public ServerModelMediator(IServer server, IWorld world)
	{
		setServer(server);
		setWorld(world);
		world.setMediator(this);
		server.setMediator(this);


	}


	public void registerPlayer(String name){
		try {
			world.createNewPlayer(name);
		} catch (GameException e) {
			sendPlayerSpecificException(getPlayerById(name),e);
		}
	}

	public void getShopItems(String id) {
		IPlayer player= getPlayerById(id);
		if (player!=null) {
			updatePlayerShopItems(player,getShopItems());
		}
	}

	public void changeRadarStatus(String id, boolean wantToGoOnline) {
		IPlayer p=getPlayerById(id);

		if (wantToGoOnline) {
			p.goOnline();
		}
		else {
			p.goOffline();
		}

		playerChangePos(p.getID(),p.getGeoPos());
		updatePlayer(p);

	}

	public void updateNearbyPlayers(IPlayer player) {
		List<Object> playersRaw= new ArrayList<Object>();
		for (IPlayer p:player.getPlayersNearby()){
			playersRaw.add(p);
		}

		server.updateNearbyPlayers(player,playersRaw);
	}

	public void updatePlayer(IPlayer IPlayer) {
		server.updatePlayer(IPlayer);
	}

	public void performAttack(String s1, String s2) {
		try {
			world.performAttack(s1,s2);
		} catch (GameException e) {
			sendPlayerSpecificException(getPlayerById(s1),e);
		}
	}

	public void playerChangePos(String id, GeoPos pos) {
		try {
			world.playerChangePos(id,pos);
		} catch (GameException e) {
			sendPlayerSpecificException(getPlayerById(id),e);
		}
	}
	public void playerChangePos(String id, Double lat, Double longitude)
	{
		try {
			world.playerChangePos(id,new GeoPos(lat,longitude));
		} catch (GameException e) {
			sendPlayerSpecificException(getPlayerById(id),e);
		}
	}


	public IPlayer getPlayerById(String id) {
		try {
			return world.getPlayerById(id);
		} catch (GameException e) {
			sendException(e);
			return null;
		}
	}

	public void setWorld(IWorld world) {
		this.world=world;

	}

	public void setServer(IServer server) {
		this.server=server;
	}

	public void consumeLootbox(String playerId, GeoPos geoPos) {
		try {
			world.consumeLootboxByGeoPos(playerId,geoPos);
		} catch (GameException e) {
			sendPlayerSpecificException(getPlayerById(playerId),e);
		}

	}

	public void updateLootbox(IPlayer player, List<ILootbox> lootboxes) {
		List<Object> lootboxesRaw= new ArrayList<Object>();
		for (ILootbox lootbox:lootboxes){
			lootboxesRaw.add(lootbox);
		}
		server.updateLootbox(player,lootboxesRaw);
	}

	public 	void sendPlayerInfo(String playerId){
		IPlayer player= getPlayerById(playerId);
		if (player!=null) {
			server.updatePlayer(player);
		}
		else {

		}

	}

	public void changeWeapon(String playerId, Integer weaponID) {
		try {
			world.changeWeapon(playerId,weaponID);
		} catch (GameException e) {
			sendPlayerSpecificException(getPlayerById(playerId),e);
		}
	}

	public List<Item> getShopItems() {
		try {
			return world.getShop().getItems();
		} catch (GameException e) {
			sendException(e);
			return null;
		}
	}

	public void updatePlayerShopItems(IPlayer p, List<Item> list) {
		Object player = p;
		List<Object> items = new ArrayList();
		for (Item item:list){
			items.add(item);
		}

		server.sendShopList(player,items);
	}

	public void buyItem(String playerId, Integer itemID, String itemType) {
		IPlayer player=getPlayerById(playerId);
		if (player!=null){
			try {
				player.buyItem(world.getShop().getItem(itemID,itemType));
			} catch (GameException e) {
				sendPlayerSpecificException(player,e);
			}
			server.updatePlayer(player);
		}

	}

	public void sellItem(String playerId, Integer itemID, String itemType) {
		IPlayer player=getPlayerById(playerId);
		if (player!=null){
			try {
				world.getShop().sellItem(player,world.getShop().getItem(itemID,itemType));
			} catch (GameException e) {
				sendPlayerSpecificException(player,e);
			}
			server.updatePlayer(player);
		}
	}

	public void playerSignin(String id, SocketIOClient socketIOClient) {
		IPlayer p=getPlayerById(id);
		if (p!=null) {
			server.playerSignin(p, socketIOClient);
		}
		else {
			server.sendException(socketIOClient,new GameException("No such user","No such id "+id));
		}
	}

	public void changeAvatar(String playerId, String avatarId) {
		IPlayer p=getPlayerById(playerId);
		if (p!=null){
			world.setPlayerAvatar(p,avatarId);
			updatePlayer(p);
			updateNearbyPlayers(p);
		}
	}

	public void sendPlayerSpecificException(IPlayer p, GameException gameException) {
		server.sendException(p, gameException);
	}

	public void sendException(GameException gameException) {
		server.sendException(gameException);
	}
}
