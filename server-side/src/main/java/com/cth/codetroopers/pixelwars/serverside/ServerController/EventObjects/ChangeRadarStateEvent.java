package com.cth.codetroopers.pixelwars.serverside.ServerController.EventObjects;

/**
 * Created by latiif on 4/11/17.
 */
public class ChangeRadarStateEvent {
	public String playerId;
	public boolean wantToGoOnline;

	public ChangeRadarStateEvent() {

	}

	public ChangeRadarStateEvent(String playerId, boolean wantToGoOnline) {
		this.playerId = playerId;
		this.wantToGoOnline = wantToGoOnline;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public boolean isWantToGoOnline() {
		return wantToGoOnline;
	}

	public void setWantToGoOnline(boolean wantToGoOnline) {
		this.wantToGoOnline = wantToGoOnline;
	}

}
