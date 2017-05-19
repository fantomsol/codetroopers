package Mocks;

import GameModel.Item.Weapons.Weapon;

/**
 * Created by latiif on 5/19/17.
 */
public class WeaponMock extends Weapon {
	public Integer getId() {
		return 9;
	}

	public String getName() {
		return "MOCKWEAPON";
	}

	public Integer getCost() {
		return 500;
	}

	public Integer getDamage() {
		return 100;
	}

	public Integer getRange() {
		return 20;
	}

	public Integer getCooldown() {
		return 2;
	}
}
