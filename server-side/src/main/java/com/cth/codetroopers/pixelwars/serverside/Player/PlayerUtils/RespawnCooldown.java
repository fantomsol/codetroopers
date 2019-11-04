package com.cth.codetroopers.pixelwars.serverside.Player.PlayerUtils;

import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Player.PlayerConstants;

/**
 * Created by latiif on 5/15/17.
 */
public class RespawnCooldown extends Cooldown {
	public RespawnCooldown(int cooldownDuration, IPlayer iPlayer) {
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
		player.setIsAlive(true);
		player.setHp(PlayerConstants.MAX_HEALTH);
	}

}
