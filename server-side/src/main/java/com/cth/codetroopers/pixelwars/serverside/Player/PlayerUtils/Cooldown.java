package com.cth.codetroopers.pixelwars.serverside.Player.PlayerUtils;

import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;

/**
 * Created by latiif on 5/15/17.
 */
public abstract class Cooldown extends Thread implements Runnable {
	protected final int cooldownDuration;
	protected final IPlayer player;

	public Cooldown(int cooldownDuration, IPlayer iPlayer) {
		this.cooldownDuration = cooldownDuration*1000;
		player = iPlayer;
	}

	@Override
	public void run() {
		onCooldownStart();

		try {
			Thread.sleep(cooldownDuration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		onCooldownFinishes();
	}

	protected void onCooldownStart() {

	}

	protected void onCooldownFinishes(){

	}

}
