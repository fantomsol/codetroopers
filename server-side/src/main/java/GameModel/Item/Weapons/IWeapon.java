package GameModel.Item.Weapons;

import GameModel.GameUtils.Exceptions.CooldownException;
import GameModel.GameUtils.Exceptions.GameException;
import GameModel.Item.Item;

/**
 * Created by latiif on 3/22/17.
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
