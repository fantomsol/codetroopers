package GameModel.Item.Weapons;

import GameModel.GameUtils.WeaponCooldown;

/**
 * Created by latiif on 3/29/17.
 */
public abstract class Weapon implements WeaponInterface {

	private Boolean isOnCooldown;


	public Weapon(){
		setIsOnCooldown(false);
	}

	@Override
	public Integer fireWeapon() {
		if (getIsOnCooldown()){
			return 0;
		}else {
			new Thread(new WeaponCooldown(this)).start();

			return this.getDamage();
		}

	}


	@Override
	public Boolean getIsOnCooldown() {
		return new Boolean(isOnCooldown);
	}

	@Override
	public void setIsOnCooldown(Boolean value) {
		this.isOnCooldown=value;
	}


	@Override
	public String getItemType() {
		return this.getClass().getName();
	}
}
