package com.cth.codetroopers.pixelwars.serverside.ServerController.EventObjects;

/**
 * Created by latiif on 5/10/17.
 */
public class SellItemEvent {
	public String playerId;
	public Integer itemId;
	public String itemType;

	public SellItemEvent(){

	}

	public SellItemEvent(String playerId, Integer itemId, String itemType) {
		this.playerId = playerId;
		this.itemId = itemId;
		this.itemType = itemType;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
}
