package com.cth.codetroopers.pixelwars.serverside.Item.Weapons;

/**
 * Created by latiif on 3/22/17.
 */
class Pistol extends Weapon{




	public Integer getId() {
		return 1;
	}

	public String getName() {
		return "Hand Pistol";
	}

	public Integer getDamage() {
		return 5;
	}

	public Integer getRange() {
		return 75;
	}

	public Integer getCooldown() {
		return 2;
	}

	public Integer getCost() {
		return 100;
	}

}
