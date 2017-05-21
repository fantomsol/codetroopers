package com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions;

/**
 * Created by latiif on 5/19/17.
 */
public class NoItemException extends GameException {
	public NoItemException(String message) {
		super("Item not found", message);
	}
}
