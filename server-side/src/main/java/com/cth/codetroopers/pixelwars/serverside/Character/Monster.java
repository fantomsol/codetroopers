package com.cth.codetroopers.pixelwars.serverside.Character;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.CombatException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoDistance;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;

public class Monster extends Being {

	private int hp;
	private int damage;
	private int range;
	private Boolean isAlive;

	public Monster(String id, int hp, int damage, int range){
		super(id);

		this.hp = hp;

		this.damage = damage;

		this.range = range;

		this.isAlive = true;
	}

	public void attackPlayer(Player player) throws CombatException {
		if (!getIsAlive()) {
			throw new CombatException("");
		}

		if (!player.getIsAlive()) {
			throw new CombatException("");
		}

		if (GeoDistance.getDistance(player.getGeoPos(), this.geoPos) > this.range) {
			throw new CombatException("");
		}

		player.getAttacked(this.damage);
	}

	public void getAttacked(final Integer damage) {
		hp -= damage;

		if (hp <= 0) {
			isAlive = false;
		}
	}

	public GeoPos getGeoPos() {
		return geoPos;
	}

	public Boolean getIsAlive() {
		return isAlive;
	}

	public Double getHp() {
		return Double.valueOf(hp);
	}
}
