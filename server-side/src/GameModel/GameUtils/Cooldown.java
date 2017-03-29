package GameModel.GameUtils;

import GameModel.Item.Weapons.Weapon;
import GameModel.Item.Weapons.WeaponInterface;

/**
 * Created by latiif on 3/29/17.
 */
public class Cooldown implements Runnable {

	private final int cooldownDuration;
	private WeaponInterface weapon;

	public Cooldown(Weapon weapon) {
		this.cooldownDuration = weapon.getCooldown()*1000;
		this.weapon = weapon;
	}


	@Override
	public void run() {
		weapon.setIsOnCooldown(true);


		try {
			Thread.sleep(cooldownDuration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		weapon.setIsOnCooldown(false);
	}
}
