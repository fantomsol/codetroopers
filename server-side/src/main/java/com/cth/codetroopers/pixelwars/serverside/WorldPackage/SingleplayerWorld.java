package com.cth.codetroopers.pixelwars.serverside.WorldPackage;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.CombatException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.Beings.Monster;
import com.cth.codetroopers.pixelwars.serverside.Beings.Being;
import com.cth.codetroopers.pixelwars.serverside.Beings.Player;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;

// This is a world where monsters are generated
public class SingleplayerWorld extends World {

	@Override
	public void performAttack(String attackerID, String victimID) throws GameException {

		Being attacker = getCharacterById(attackerID);
		Being victim = getCharacterById(victimID);

		if (attacker instanceof Player && victim instanceof Monster) {
			playerAttack((Player) attacker, (Monster) victim);

		} else if (attacker instanceof Monster && victim instanceof Player) {
			monsterAttack((Monster) attacker, (Player) victim);

		} else {
			throw new GameException("Something strange", "");
		}

		if (mediator != null) {
			// TODO: Update monsters here
			// mediator.updateNearbyPlayers(attacker);
			mediator.updatePlayer(victim);
		}

	}

	protected void goOffline(Player player) {

	}

	public void playerChangePos(String id, GeoPos newPos) throws GameException {

	}

	private void monsterAttack(Monster attacker, Player victim) throws CombatException {
		attacker.attackPlayer(victim);
	}

	private void playerAttack(Player attacker, Monster victim) throws GameException {
		//cannot attack a ghost
		if (!victim.getIsAlive()) {
			throw new GameException("Invalid target", victim.getID() + " dead monsters cannot be attacked");
		}

		attacker.attackMonster(victim);

	}
}
