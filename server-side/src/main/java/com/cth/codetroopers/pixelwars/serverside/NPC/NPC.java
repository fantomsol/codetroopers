package com.cth.codetroopers.pixelwars.serverside.NPC;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Player.Avatar.Avatar;

public class NPC implements INPC {

	private Avatar avatar = Avatar.SIBOAN;

	private GeoPos geoPos;
	private Boolean isAlive;
	private String id;
	private String message;

	public NPC(String id, String message) {
		this.id = id;

		this.message = message;
	}

	public void talk(){

	}

	public void updatePos(final GeoPos newPos) {
		geoPos = newPos;
	}

	public void setIsAlive(boolean life) {
		this.isAlive = life;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar a) {
		avatar = a;
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

}
