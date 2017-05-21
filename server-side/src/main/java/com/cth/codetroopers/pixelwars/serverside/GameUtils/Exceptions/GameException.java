package com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions;

/**
 * Created by latiif on 5/16/17.
 */
public class GameException extends Throwable {

	private String name;
	private String message;

	public GameException(String name, String message){
		this.name=name;
		this.message=message;
	}

	public String getName(){
		return this.name;
	}

	public String getMessage(){
		return this.message;
	}

	@Override
	public String toString(){
		StringBuilder sb= new StringBuilder();

		sb.append(getName()+": "+getMessage());

		return sb.toString();
	}

}
