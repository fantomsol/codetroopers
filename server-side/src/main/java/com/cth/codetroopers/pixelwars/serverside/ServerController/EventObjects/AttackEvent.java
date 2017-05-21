package com.cth.codetroopers.pixelwars.serverside.ServerController.EventObjects;

/**
 * Created by latiif on 4/9/17.
 */
public class AttackEvent {
	private String id,oId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getoId() {
		return oId;
	}

	public void setoId(String oId) {
		this.oId = oId;
	}

	public AttackEvent(String id, String oId) {
		this.id = id;
		this.oId = oId;
	}

	public AttackEvent(){

	}
}
