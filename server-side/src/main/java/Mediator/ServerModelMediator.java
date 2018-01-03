package Mediator;

import com.corundumstudio.socketio.SocketIOClient;
import com.cth.codetroopers.pixelwars.serverside.Beings.Being;
import com.cth.codetroopers.pixelwars.serverside.Beings.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Beings.Player;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Item.Item;
import com.cth.codetroopers.pixelwars.serverside.Lootbox.ILootbox;
import com.cth.codetroopers.pixelwars.serverside.ServerController.IServer;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by latiif on 4/30/17.
 */
public class ServerModelMediator implements IMediator {

	private IServer server;
	private World world;

	public ServerModelMediator(IServer server, World world) {
		setServer(server);
		setWorld(world);
		world.setMediator(this);
		server.setMediator(this);
	}


	public void registerPlayer(String name) {
		try {
			world.createNewPlayer(name);
		} catch (GameException e) {
			sendPlayerSpecificException(getCharacterById(name), e);
		}
	}

	public void getShopItems(String id) {
		Being player = getCharacterById(id);
		if (player != null) {
			updatePlayerShopItems(player, getShopItems());
		}
	}

	public void changeRadarStatus(String id, boolean wantToGoOnline) throws GameException {
		Being c = getCharacterById(id);
		if (c instanceof Player) {
			Player p = (Player) c;

			if (wantToGoOnline) {
				p.goOnline();
			} else {
				p.goOffline();
			}

			playerChangePos(p.getID(), p.getGeoPos());
			updatePlayer(p);
		} else {
			throw new GameException("Only players can change radarstatus", "");
		}
	}

	public void updateNearbyPlayers(IPlayer player) {
		List<Object> playersRaw = new ArrayList<Object>();

		for (Player p : player.getPlayersNearby()) {
			playersRaw.add(p);
		}

		server.updateNearbyPlayers(player, playersRaw);
	}

	public void updatePlayer(Being being) {
		server.updatePlayer(being);
	}

	public void performAttack(String s1, String s2) {
		try {
			world.performAttack(s1, s2);
		} catch (GameException e) {

		}
	}

	public void playerChangePos(String id, GeoPos pos) {

		try {
			world.playerChangePos(id, pos);
		} catch (GameException e) {
			sendPlayerSpecificException(getCharacterById(id), e);
		}
	}

	public void playerChangePos(String id, Double lat, Double longitude) {
		try {
			world.playerChangePos(id, new GeoPos(lat, longitude));
		} catch (GameException e) {
			sendPlayerSpecificException(getCharacterById(id), e);
		}
	}


	public Being getCharacterById(String id) {
		try {
			return world.getCharacterById(id);
		} catch (GameException e) {
			sendGameException(e);
			return null;
		}
	}

	public void setWorld(World world) {
		this.world = world;

	}

	public void setServer(IServer server) {
		this.server = server;
	}

	public void consumeLootbox(String playerId, GeoPos geoPos) {
		try {
			world.consumeLootboxByGeoPos(playerId, geoPos);
		} catch (GameException e) {
			sendPlayerSpecificException(getCharacterById(playerId), e);
		}

	}

	public void updateLootbox(Being player, List<ILootbox> lootboxes) {
		List<Object> lootboxesRaw = new ArrayList<Object>();
		for (ILootbox lootbox : lootboxes) {
			lootboxesRaw.add(lootbox);
		}
		server.updateLootbox(player, lootboxesRaw);
	}

	public void sendPlayerInfo(String playerId) {
		Being player = getCharacterById(playerId);
		if (player != null) {
			server.updatePlayer(player);
		} else {

		}

	}

	public void changeWeapon(String playerId, Integer weaponID) {
		try {
			world.changeWeapon(playerId, weaponID);
		} catch (GameException e) {
			sendPlayerSpecificException(getCharacterById(playerId), e);
		}
	}

	public List<Item> getShopItems() {
		try {
			return world.getShop().getItems();
		} catch (GameException e) {
			sendGameException(e);
			return null;
		}
	}

	public void updatePlayerShopItems(Being p, List<Item> list) {
		Object player = p;
		List<Object> items = new ArrayList();
		for (Item item : list) {
			items.add(item);
		}

		server.sendShopList(player, items);
	}

	public void buyItem(String playerId, Integer itemID, String itemType) {
		Being c = getCharacterById(playerId);
		if (c instanceof Player) {
			Player player = (Player) c;
				try {
					player.buyItem(world.getShop().getItem(itemID, itemType));
				} catch (GameException e) {
					sendPlayerSpecificException(player, e);
				}
			server.updatePlayer(player);
		}
	}

	public void sellItem(String playerId, Integer itemID, String itemType) {
		Being c = getCharacterById(playerId);
		if (c instanceof Player) {
			Player player = (Player) c;

			if (player != null) {
				try {
					world.getShop().sellItem(player, world.getShop().getItem(itemID, itemType));
				} catch (GameException e) {
					sendPlayerSpecificException(player, e);
				}
				server.updatePlayer(player);
			}
		}
	}

	public void playerSignin(String id, SocketIOClient socketIOClient) {
		Being p = getCharacterById(id);
		if (p != null) {
			server.playerSignin(p, socketIOClient);
		} else {
			server.sendException(socketIOClient, new GameException("No such user", "No such id " + id));
		}
	}

	public void changeAvatar(String playerId, String avatarId) {
		Being c = getCharacterById(playerId);
		if (c instanceof Player) {
			Player p = (Player) c;
			world.setPlayerAvatar(p, avatarId);
			updatePlayer(p);
			updateNearbyPlayers(p);
		}
	}

	public void sendPlayerSpecificException(Being p, GameException gameException) {
		server.sendException(p, gameException);
	}

	public void sendGameException(GameException gameException) {
		server.sendException(gameException);
	}
}
