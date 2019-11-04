package Mocks;

import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.Weapon;

/**
 * Created by latiif on 5/19/17.
 */
public class WeaponMock extends Weapon {

	private Integer cost;

	public WeaponMock(){
		this.cost = 100;
	}
	public WeaponMock(int cost){
		this.cost = cost;
	}

	public Integer getId() {
		return 9;
	}

	public String getName() {
		return "MOCKWEAPON";
	}

	public Integer getCost() {
		return cost;
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
