package com.cth.codetroopers.pixelwars.serverside.WorldPackage;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.Player.PlayerUtils.Avatar;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoDistance;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.WeaponsDirectory;
import com.cth.codetroopers.pixelwars.serverside.Player.PlayerUtils.Exp;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.Lootbox.ILootbox;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Player.Player;
import com.cth.codetroopers.pixelwars.serverside.WorldPackage.Shop.Shop;
import Mediator.ServerModelMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by latiif on 3/22/17.
 */
public class World {

	private ServerModelMediator mediator;
	private Shop shop;

	private List<ILootbox> lootboxes;
	private Map<String, IPlayer> players;

	public World() {
		players = new HashMap<String, IPlayer>();
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
		IPlayer p = getPlayerById(playerId);
		p.switchWeapon(weaponID);
		mediator.updatePlayer(p);
	}

	public void addLootbox(ILootbox lootbox) {
		lootboxes.add(lootbox);
	}

	public IPlayer getPlayer(IPlayer p) {
		return players.get(p.getID());
	}

	public void performAttack(String attackerId, String attackeeId) throws GameException {
		IPlayer attacker = getPlayerById(attackerId);
		IPlayer target = getPlayerById(attackeeId);

		//cannot attack a ghost
		if (!target.getIsAlive()) {
			throw new GameException("Invalid target", attackeeId + " is dead players cannot be attacked");
		}

		if (target.getWeaponEquipped().getId().intValue() == WeaponsDirectory.WHITEFLAG) {
			attacker.setExp(Exp.getExpOnAttackingUnarmed(attacker.getExp()));
		}

		attacker.attackOtherPlayer(target);
		mediator.updateNearbyPlayers(attacker);
		mediator.updatePlayer(target);

		//after attacking, revive attacked player if dead
		if (!target.getIsAlive()) {
			revivePlayer(target);
		}
	}


	private void revivePlayer(final IPlayer p) {
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
				mediator.updatePlayer(p);
				mediator.updateNearbyPlayers(p);
			}
		});

		thread.start();
	}

	public void registerPlayer(IPlayer IPlayer) {
		players.put(IPlayer.getID(), IPlayer);
	}

	private void goOfflinePlayer(IPlayer player) {
		// Remove the player from other players' view
		for (IPlayer op : player.getPlayersNearby()) {
			op.removeNearbyPlayer(player);
			mediator.updateNearbyPlayers(op);
		}
		// someone who's offline can't see anyone
		player.getPlayersNearby().clear();
		mediator.updateNearbyPlayers(player);
		updateLootboxes(player);
	}

	public void playerChangePos(final String id, final GeoPos newPos) throws GameException {
		IPlayer player = getPlayerById(id);
		player.updatePos(newPos);

		if (!player.isOnline()) {
			goOfflinePlayer(player);
			mediator.updatePlayer(player);
			return;
		}

		for (IPlayer other : players.values()) {
			if (!other.isOnline() || player.equals(other)) continue;

			boolean playerShouldSeeOther = GeoDistance.getDistance(player.getGeoPos(), other.getGeoPos()) <= player.getVision();
			boolean otherShouldSeePlayer = GeoDistance.getDistance(player.getGeoPos(), other.getGeoPos()) <= other.getVision();
			boolean playerSeesOther = player.getPlayersNearby().contains(other);
			boolean otherSeesPlayer = other.getPlayersNearby().contains(player);

			if (playerShouldSeeOther) {
				// Add other if not already visible
				if (!playerSeesOther) player.addNearbyPlayer(other);
			} else {
				// Remove other if they left player's vision range
				if (playerSeesOther) player.removeNearbyPlayer(other);
			}

			if (otherShouldSeePlayer) {
				// Add player if not already visible
				if (!otherSeesPlayer) other.addNearbyPlayer(player);
			} else {
				// Remove player if they left other's range
				if (otherSeesPlayer) other.removeNearbyPlayer(player);
			}
			mediator.updateNearbyPlayers(other);
			updateLootboxes(other);
		}
		mediator.updatePlayer(player);
		mediator.updateNearbyPlayers(player);
		updateLootboxes(player);
	}


	public void updateLootboxes(IPlayer player) {
		List<ILootbox> visibleLootboxes = new ArrayList<ILootbox>();

		if (player.isOnline() && player.getIsAlive()) {
			for (ILootbox lootbox : lootboxes) {
				if (GeoDistance.getDistance(lootbox.getGeoPos(), player.getGeoPos()) <= player.getVision()) {
					visibleLootboxes.add(lootbox);
					player.addVisibleLootbox(lootbox);
				}
			}
		}

		System.out.println(player.getID() + " sees a total of " + visibleLootboxes.size() + " lootboxes");
		if (mediator != null) {
			mediator.updateLootbox(player, visibleLootboxes);
		}
	}

	public void setPlayerAvatar(IPlayer player, String avatarId) {
		player.setAvatar(Avatar.valueOf(avatarId));
	}


	public void consumeLootboxByGeoPos(String playerId, GeoPos pos) throws GameException {

		IPlayer player = getPlayerById(playerId);

		for (ILootbox lootbox : lootboxes) {
			if (lootbox.getGeoPos().equals(pos)) {
				//player.consume(lootbox);
				lootbox.consume(player);
				lootboxes.remove(lootbox);
				player.removeVisibleLootbox(lootbox);

				if (mediator != null) {
					mediator.playerChangePos(playerId, player.getGeoPos());
				}
				break;
			}
		}
	}

	public ServerModelMediator getMediator() {
		return mediator;
	}

	public List<ILootbox> getLootboxes() {
		return lootboxes;
	}

	public Map<String, IPlayer> getPlayers() {
		return players;
	}

	public IPlayer getPlayerById(final String id) throws GameException {
		IPlayer p = players.get(id);
		if (p != null) {
			return p;
		} else {
			throw new GameException("No such player", id + " is not registered");
		}
	}

	public Shop getShop() {
		return shop;
	}

}
