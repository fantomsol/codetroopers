package GameModel.Item.Weapons;

/**
 * Created by latiif on 3/22/17.
 */
class Pistol extends Weapon implements WeaponInterface {



	@Override
	public Integer getId() {
		return 1;
	}

	@Override
	public String getName() {
		return "Hand Pistol";
	}


	@Override
	public Integer getDamage() {
		return 5;
	}

	@Override
	public Integer getRange() {
		return 10;
	}

	@Override
	public Integer getCooldown() {
		return 1;
	}

	@Override
	public Integer getCost() {
		return 100;
	}

}
