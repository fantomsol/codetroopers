package com.cth.codetroopers.pixelwars.serverside.Character;

public class Npc extends Being {

	private String message;

	public Npc(String id, String message) {
		super(id);
		this.message = message;
	}

	public void getAttacked(Integer damage) {

	}
}
