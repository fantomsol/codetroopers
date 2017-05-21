package com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions;

/**
 * Created by latiif on 5/18/17.
 */
public class CooldownException extends GameException {

	public CooldownException( String message) {
		super("Cooldown Exception", message);
	}
}
