package GameModel.Item.Weapons;

import GameModel.GameUtils.WeaponCooldown;

/**
 * Created by latiif on 3/29/17.
 */
public abstract class Weapon implements IWeapon {

	private Boolean isOnCooldown;


	public Weapon(){
		setIsOnCooldown(false);
	}

	public Integer fireWeapon() {
		if (getIsOnCooldown()){
			return 0;
		}else {
			new Thread(new WeaponCooldown(this)).start();

			return this.getDamage();
		}

	}

	public Boolean getIsOnCooldown() {
		return new Boolean(isOnCooldown);
	}

	public void setIsOnCooldown(Boolean value) {
		this.isOnCooldown=value;
	}

	public String getItemType() {
		return this.getClass().getName();
	}


}
