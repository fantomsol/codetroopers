package com.cth.codetroopers.pixelwars.serverside.WorldPackage;

import com.cth.codetroopers.pixelwars.serverside.Character.Being;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.Character.Avatar.Avatar;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoDistance;
import com.cth.codetroopers.pixelwars.serverside.Lootbox.ILootbox;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Character.Player;
import com.cth.codetroopers.pixelwars.serverside.Shop.IShop;
import com.cth.codetroopers.pixelwars.serverside.Shop.Shop;
import Mediator.ServerModelMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by latiif on 3/22/17.
 */
public abstract class World {


	protected ServerModelMediator mediator;
	private IShop shop;
	private List<ILootbox> lootboxes;
	protected Map<String, Player> players;
	protected Map<String, Being> characters;

	public World() {
		players = new HashMap<String, Player>();
		characters = new HashMap<String, Being>();
		lootboxes = new ArrayList<ILootbox>();
		shop = new Shop();
	}

	public void createNewPlayer(String name) throws GameException {
		players.put(name, new Player(name, new GeoPos(0.0, 0.0)));
	}

	public void setMediator(ServerModelMediator mediator) {
		this.mediator = mediator;
	}

	public void changeWeapon(String playerId, Integer weaponID) throws GameException {
		Player p = (Player) getCharacterById(playerId);
		p.switchWeapon(weaponID);
		mediator.updatePlayer(p);
	}


	public IShop getShop() {
		return shop;
	}

	public void addLootbox(ILootbox lootbox) {
		lootboxes.add(lootbox);
	}

	public Being getCharacter(Being p) {
		return players.get(p.getID());
	}

	public abstract void performAttack(String attackerID, String victimID) throws GameException;

	protected void revivePlayer(final Player p) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						TimeUnit.SECONDS.sleep(1);
						if (p.getIsAlive()) {
							break;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("Just revived " + p);
				try {
					playerChangePos(p.getID(), p.getGeoPos());
				} catch (GameException e) {

				}
				if (mediator != null) {
					mediator.updatePlayer(p);
					mediator.updateNearbyPlayers(p);
				}
			}
		});

		thread.start();
	}


	public Being getCharacterById(final String id) throws GameException {
		Being res = characters.get(id);
		if (res != null) {
			return res;
		} else {
			throw new GameException("No such player", id + " is not registered");
		}
	}


	public void registerPlayer(Player player) {
		players.put(player.getID(), player);
	}

	protected abstract void goOffline(Player player);

	public abstract void playerChangePos(final String id, final GeoPos newPos) throws GameException;


	public void updateLootboxes(Player player) {
		List<ILootbox> visibleLootboxes = new ArrayList<ILootbox>();

		if (player.isOnline() && player.getIsAlive()) {
			for (ILootbox lootbox : lootboxes) {
				if (GeoDistance.getDistance(lootbox.getGeoPos(), player.getGeoPos()) <= player.getVision()) {
					visibleLootboxes.add(lootbox);
				}
			}
		}

		System.out.println(player.getID() + " sees a total of " + visibleLootboxes.size() + " lootboxes");
		if (mediator != null) {
			mediator.updateLootbox(player, visibleLootboxes);
		}
	}

	public void setPlayerAvatar(Being player, String avatarId) {
		player.setAvatar(Avatar.valueOf(avatarId));
	}


	public void consumeLootboxByGeoPos(String playerId, GeoPos pos) throws GameException {

		Being character = getCharacterById(playerId);
		if (character instanceof Player){
			Player player = (Player) character;

			for (ILootbox lootbox : lootboxes) {
				if (lootbox.getGeoPos().equals(pos)) {
					//player.consume(lootbox);
					lootbox.consume(player);

					lootboxes.remove(lootbox);

					if (mediator != null) {
						mediator.playerChangePos(playerId, player.getGeoPos());
					}
					break;
				}
			}
		} else throw new GameException("Lootbox can't be consumed by npc", "");
	}
}

/*
 * Saving the IWorld interface here in case I'll need it for reference, otherwise I plan to use World as an abstract
 * class instead as a better template for worlds.

 public interface IWorld {
	void createNewPlayer(String name) throws GameException;

	void setMediator(ServerModelMediator mediator);

	void changeWeapon(String playerId, Integer weaponID) throws GameException;

	IShop getShop();

	void addLootbox(ILootbox lootbox);

	Being getCharacter(Being p);

	void performAttack(String attackerId, String attackeeId) throws GameException;

	Being getCharacterById(String id) throws GameException;

	void registerPlayer(Being Being);

	void playerChangePos(String id, GeoPos newPos) throws GameException;

	void updateLootboxes(Being player);

	void setPlayerAvatar(Being player, String avatarId);

	void consumeLootboxByGeoPos(String playerId, GeoPos pos) throws GameException;
 }
*/