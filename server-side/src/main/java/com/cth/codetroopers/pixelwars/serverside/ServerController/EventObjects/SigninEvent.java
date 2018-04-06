package com.cth.codetroopers.pixelwars.serverside.ServerController.EventObjects;

/**
 * Created by latiif on 4/1/17.
 */
public class SigninEvent {

	String id;

	public SigninEvent() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SigninEvent(String id) {
		this.id = id;
	}
}
