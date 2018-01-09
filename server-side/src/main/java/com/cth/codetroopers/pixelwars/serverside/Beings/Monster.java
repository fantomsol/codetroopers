package com.cth.codetroopers.pixelwars.serverside.Beings;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.CombatException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GeographicalException;
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

	public void move(GeoPos playerPos) throws GeographicalException {

		//Monsters will move in direction of the active player's position with a set speed.
		double distance = GeoDistance.getDistance(playerPos, this.geoPos);

		int speedvar = 5;
		double playerLon = playerPos.getLongitude();
		double playerLat = playerPos.getLongitude();
		double monsterLat = geoPos.getLatitude();
		double monsterLon = geoPos.getLongitude();

		if (monsterLat < playerLat){
			if (monsterLon < playerLon) {
				setGeoPos(monsterLat + speedvar, monsterLon + speedvar);
			}
			if (monsterLon > playerLon){
				setGeoPos(monsterLat + speedvar, monsterLon - speedvar);
			}
		}
		if (monsterLat > playerLat){
			if (monsterLon < playerLon) {
				setGeoPos(monsterLat - speedvar, monsterLon + speedvar);
			}
			if (monsterLon > playerLon){
				setGeoPos(monsterLat - speedvar, monsterLon - speedvar);
			}
		}
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public void setAlive(Boolean alive) {
		isAlive = alive;
	}

	public void setGeoPos (double lat, double lon) throws GeographicalException {
		geoPos = new GeoPos(lat, lon);
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
