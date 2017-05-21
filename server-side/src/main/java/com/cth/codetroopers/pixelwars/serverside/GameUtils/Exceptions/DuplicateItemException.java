package com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions;

/**
 * Created by latiif on 5/19/17.
 */
public class DuplicateItemException extends GameException {
	public DuplicateItemException(String message) {
		super("Duplicate Item", message);
	}
}
