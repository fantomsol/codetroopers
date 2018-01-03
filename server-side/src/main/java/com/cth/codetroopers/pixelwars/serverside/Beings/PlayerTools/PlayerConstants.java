package com.cth.codetroopers.pixelwars.serverside.Beings.PlayerTools;



/**
 * Created by latiif on 4/1/17.
 */
public class PlayerConstants {

	public final static Double MAX_HEALTH = 100.0;

	private final static Integer START_GOLD = 100;
	private final static Integer START_EXP = 200;

	//Vision in meters
	final static Integer START_VISION = 250;


	//Offline cooldown in seconds
	public final static Integer START_COOLDOWN = 2;

	//Respawn cooldown
	public final static Integer RESPAWN_COOLDWON=5;

	public final static Double damageCaluculation(final Integer damage, final Integer armour){
		return (damage*100.0)/(100+armour);
	}

	public static Double getMaxHealth() {
		return MAX_HEALTH;
	}

	public static Integer getStartGold() {
		return START_GOLD;
	}

	public static Integer getStartExp() {
		return START_EXP;
	}

	public static Integer getStartVision() {
		return START_VISION;
	}

	public static Integer getStartCooldown() {
		return START_COOLDOWN;
	}

	public static Integer getRespawnCooldwon() {
		return RESPAWN_COOLDWON;
	}

}
