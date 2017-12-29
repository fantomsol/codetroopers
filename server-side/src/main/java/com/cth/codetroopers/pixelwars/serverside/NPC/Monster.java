package com.cth.codetroopers.pixelwars.serverside.NPC;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.*;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.CombatException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoDistance;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Player.Avatar.Avatar;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Monster implements INPC {

	private Avatar avatar;
	private GeoPos geoPos;
	private int hp;
	private int damage;
	private int range;
	private Boolean isAlive;
	private String id;

	public Monster(String id, int hp, int damage, int range){
		this.id = id;

		this.hp = hp;

		this.damage = damage;

		this.range = range;

		this.isAlive = true;
	}

	public void attackPlayer(IPlayer player) throws CombatException {
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

	public void updatePos(GeoPos newPos) {
		geoPos = newPos;
	}

	public void setAvatar(Avatar a) {
		avatar = a;
	}

	public void setIsAlive(boolean life) {
		isAlive = life;
	}

	public Avatar getAvatar() {
		return this.avatar;
	}

	public GeoPos getGeoPos() {
		return geoPos;
	}

	public String getID() {
		return id;
	}

	public Boolean getIsAlive() {
		return isAlive;
	}

	public Double getHp() {
		return Double.valueOf(hp);
	}
}
