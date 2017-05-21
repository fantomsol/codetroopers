package com.cth.codetroopers.pixelwars.serverside.ServerController.EventObjects;

/**
 * Created by latiif on 5/9/17.
 */
public class SignupEvent {
	String id;

	public SignupEvent(){

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SignupEvent(String id) {
		this.id = id;
	}
}
