package com.cth.codetroopers.pixelwars.serverside.ServerController.EventObjects;

/**
 * Created by latiif on 5/16/17.
 */
public class ChangeAvatarEvent {
	public String playerId,avatarId;

	public ChangeAvatarEvent(String playerId, String avatarId) {
		this.playerId = playerId;
		this.avatarId = avatarId;
	}

	public ChangeAvatarEvent(){

	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(String avatarId) {
		this.avatarId = avatarId;
	}
}
