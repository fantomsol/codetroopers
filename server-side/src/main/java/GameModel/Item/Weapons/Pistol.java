package GameModel.Item.Weapons;

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
		return 10;
	}

	public Integer getCooldown() {
		return 1;
	}

	public Integer getCost() {
		return 100;
	}

}
