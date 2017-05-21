package com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions;

/**
 * Created by latiif on 5/18/17.
 */
public class CombatException extends GameException {
	public CombatException(String message) {
		super("Combat Exception", message);
	}
}
