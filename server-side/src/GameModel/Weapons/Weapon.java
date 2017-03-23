package GameModel.Weapons;

/**
 * Created by latiif on 3/22/17.
 */
public interface Weapon {

	public Integer getId();

	public String getName();

	//Damage is a percentage
	public Integer getDamage();


	//Range is indicated by its radius
	public Integer getRange();


	//Cooldown is in seconds
	public Integer getCooldown();

	//Cost of the weapon
	public Integer getCost();

}
