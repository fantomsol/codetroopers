package com.cth.codetroopers.pixelwars.serverside.Player.PlayerUtils;

import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;

/**
 * Created by latiif on 3/29/17.
 */
public class RadarCooldown extends Cooldown {

	public RadarCooldown(int cooldownDuration, IPlayer iPlayer) {
		super(cooldownDuration, iPlayer);
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
