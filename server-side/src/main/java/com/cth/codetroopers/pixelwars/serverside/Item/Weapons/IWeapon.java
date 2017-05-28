package com.cth.codetroopers.pixelwars.serverside.Item.Weapons;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.CooldownException;
import com.cth.codetroopers.pixelwars.serverside.Item.Item;

/**
 * Created by latiif and Hugo on 3/22/17.
 */
public interface IWeapon extends Item {


	Integer fireWeapon() throws CooldownException;

	//Damage is a percentage
	Integer getDamage();


	//Range is indicated by its radius
	Integer getRange();


	//WeaponCooldown is in seconds
	Integer getCooldown();

	Boolean getIsOnCooldown();

	void setIsOnCooldown(Boolean value);

}
