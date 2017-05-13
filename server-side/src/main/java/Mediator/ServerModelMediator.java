package Mediator;

import GameModel.Item.Item;
import GameModel.Lootbox.ILootbox;
import GameModel.GameUtils.GeoPos;
import GameModel.Player.IPlayer;
import com.corundumstudio.socketio.SocketIOClient;
import sun.swing.BakedArrayList;

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
		world.createNewPlayer(name);
	}

	public void updateNearbyPlayers(IPlayer IPlayer) {
		server.updateNearbyPlayers(IPlayer);
	}

	public void updatePlayer(IPlayer IPlayer) {
		server.updatePlayer(IPlayer);
	}

	public void performAttack(String s1, String s2) {
		world.performAttack(s1,s2);
	}

	public void playerChangePos(String id, GeoPos pos) {
		world.playerChangePos(id,pos);
	}

	public IPlayer getPlayerById(String id) {
		return world.getPlayerById(id);
	}

	public void setWorld(IWorld world) {
		this.world=world;

	}

	public void setServer(IServer server) {
		this.server=server;
	}

	public void consumeLootbox(String playerId, GeoPos geoPos) {
		world.consumeLootboxByGeoPos(playerId,geoPos);

	}

	public void updateLootbox(IPlayer player, List<ILootbox> lootboxes) {
		server.updateLootbox(player,lootboxes);
	}

	public void changeWeapon(String playerId, Integer weaponID) {
		world.changeWeapon(playerId,weaponID);
	}

	public List<Item> getShopItems() {
		return world.getShop().getItems();
	}

	public void updatePlayerShopItems(IPlayer p, List<Item> list) {
		Object player = p;
		List<Object> items = new ArrayList();
		for (Item item:list){
			items.add(item);
		}

		server.sendShopList(player,items);
	}

	public void buyItem(IPlayer player, Integer itemID, String itemType) {
		if (player!=null){
			player.buyItem(world.getShop().getItem(itemID,itemType));
			server.updatePlayer(player);
		}

	}

	public void sellItem(IPlayer player, Integer itemID, String itemType) {
		if (player!=null){
			world.getShop().sellItem(player,world.getShop().getItem(itemID,itemType));
			server.updatePlayer(player);
		}
	}

	public void playerSignin(IPlayer p, SocketIOClient socketIOClient) {
		server.playerSignin(p,socketIOClient);
	}
}
