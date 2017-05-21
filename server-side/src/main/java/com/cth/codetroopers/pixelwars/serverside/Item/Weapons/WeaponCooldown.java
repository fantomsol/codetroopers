package com.cth.codetroopers.pixelwars.serverside.Item.Weapons;

/**
 * Created by latiif on 3/29/17.
 */
public class WeaponCooldown implements Runnable {

	private final int cooldownDuration;
	private IWeapon weapon;

	public WeaponCooldown(Weapon weapon) {
		this.cooldownDuration = weapon.getCooldown()*1000;
		this.weapon = weapon;
	}

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
