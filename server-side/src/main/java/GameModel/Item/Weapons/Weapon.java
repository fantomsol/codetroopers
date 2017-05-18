package GameModel.Item.Weapons;

import GameModel.GameUtils.Exceptions.CooldownException;
import GameModel.GameUtils.Exceptions.GameException;

/**
 * Created by latiif on 3/29/17.
 */
public abstract class Weapon implements IWeapon {

	private Boolean isOnCooldown;


	public Weapon(){
		setIsOnCooldown(false);
	}

	public Integer fireWeapon() throws CooldownException {
		if (getIsOnCooldown()){
			throw new CooldownException("Wait untill the cooldown is finished");
		}else {
			new Thread(new WeaponCooldown(this)).start();

			return this.getDamage();
		}

	}

	public Boolean getIsOnCooldown() {
		return isOnCooldown;
	}

	public void setIsOnCooldown(Boolean value) {
		this.isOnCooldown=value;
	}

	public String getItemType() {
		return this.getClass().getName();
	}


}
