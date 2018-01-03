package com.cth.codetroopers.pixelwars.serverside.WorldPackage;

import com.cth.codetroopers.pixelwars.serverside.Beings.Being;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoDistance;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.WeaponsDirectory;
import com.cth.codetroopers.pixelwars.serverside.Beings.Experience.Exp;
import com.cth.codetroopers.pixelwars.serverside.Beings.Player;


// This is a world with live opponents
public class MultiplayerWorld extends World {

	public void performAttack(String attackerID, String attackedID) throws GameException {
		Being attacker = getCharacterById(attackerID);
		Being attacked = getCharacterById(attackedID);

		if (attacker instanceof Player && attacked instanceof Player){
			Player shooter = (Player) attacker;
			Player victim = (Player) attacked;

			//cannot attack a ghost
			if (!victim.getIsAlive()) {
				throw new GameException("Invalid target", victim.getID() + " is dead players cannot be attacked");
			}


			if (victim.getWeaponEquipped().getId().intValue() == WeaponsDirectory.WHITEFLAG) {
				//Exp.getExpOnAttackingUnarmed(attacker);
				shooter.setExp(Exp.getExpOnAttackingUnarmed(shooter.getExp()));
			}

			shooter.attackPlayer(victim);

			if (mediator != null) {
				mediator.updateNearbyPlayers(shooter);
				mediator.updatePlayer(victim);
			}

			//after attacking, the victim is dead
			if (!victim.getIsAlive()) {
				revivePlayer(victim);
			}
		}
	}

	protected void goOffline(Player player) {
		//Remove the player from their nearby players' lists
		for (Player op : player.getPlayersNearby()) {
			op.getPlayersNearby().remove(player);

			//update the other player's nearby list
			mediator.updateNearbyPlayers(op);
		}

		//someone who's offline cannot see anyone
		player.getPlayersNearby().clear();

		//update the player's nearby list
		if (mediator != null) {
			mediator.updateNearbyPlayers(player);
		}

		updateLootboxes(player);
	}

	public void playerChangePos(String id, GeoPos newPos) throws GameException {
		Player player = (Player) getCharacterById(id);
		player.updatePos(newPos);

		if (!player.isOnline()) {
			goOffline(player);
			mediator.updatePlayer(player);
			return;
		}

		for (Player oPlayer : players.values()) {
			if (!oPlayer.isOnline()) {
				continue;
			}
			if (player.equals(oPlayer)) {
				continue;
			}

			boolean pSeesOp = GeoDistance.getDistance(player.getGeoPos(), oPlayer.getGeoPos()) <= player.getVision();
			// Don't think this is needed from both sides, as this function should be called by both players.
			// boolean opSeesP = GeoDistance.getDistance(player.getGeoPos(), oPlayer.getGeoPos()) <= oPlayer.getVision();


			//Being sees the other player
			if (pSeesOp) {

				//If the other player was already in the vicinity of player, do nothing

				//The other player has just entered player's vision range
				if (!player.getPlayersNearby().contains(oPlayer)) {
					player.addNearbyPlayer(oPlayer);
				}
			} else {
				//Being does not see oPlayer

				//if other player is no longer in player's vision range
				if (player.getPlayersNearby().contains(oPlayer)) {
					player.removeNearbyPlayer(oPlayer);
				}
			}

			/*
			//Exact reverse logic

			//Being sees the other player
			if (opSeesP) {

				//The other player was already in the vicinity of player, do nothing

				//The other player has just entered player's vision range
				if (!oPlayer.getPlayersNearby().contains(player)) {
					oPlayer.addNearbyPlayer(player);
				}
			} else {
				//Being does not see oPlayer

				//if other player is no longer in player's vision range
				if (oPlayer.getPlayersNearby().contains(player)) {
					oPlayer.removeNearbyPlayer(player);
				}
			}
			*/

			mediator.updateNearbyPlayers(oPlayer);
			updateLootboxes(oPlayer);
		}

		mediator.updatePlayer(player);
		mediator.updateNearbyPlayers(player);


		///Check for lootboxes

		updateLootboxes(player);


	}

}
