package com.cth.codetroopers.pixelwars.serverside.Beings;

/**
 * Created by latiif on 3/29/17.
 */
public class RadarCooldown extends Cooldown {

	protected RadarCooldown(int cooldownDuration, Player player) {
		super(cooldownDuration, player);
	}

	@Override
	protected void onCooldownStart() {
		player.setCanGoOffline(false);
		player.setOfflineCooldownStops(System.currentTimeMillis()+cooldownDuration);
	}

	@Override
	protected void onCooldownFinishes() {
		player.setOfflineCooldownStops(Long.valueOf(0));
		player.setCanGoOffline(true);
	}

}
