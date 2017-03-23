package GameModel.Weapons;

/**
 * Created by latiif on 3/22/17.
 */
class Pistol implements Weapon {

	private final int ID=1;

	private final Integer damage=5;

	private final Integer range= 10;
	private final Integer cooldown=5;
	private final Integer cost=100;


	@Override
	public Integer getId() {
		return ID;
	}

	@Override
	public String getName() {
		return "Hand Pistol";
	}

	@Override
	public Integer getDamage() {
		return damage;
	}

	@Override
	public Integer getRange() {
		return range;
	}

	@Override
	public Integer getCooldown() {
		return cooldown;
	}

	@Override
	public Integer getCost() {
		return cost;
	}
}
