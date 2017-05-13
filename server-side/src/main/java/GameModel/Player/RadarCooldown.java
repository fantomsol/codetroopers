package GameModel.Player;

import GameModel.Player.IPlayer;

/**
 * Created by latiif on 3/29/17.
 */
public class RadarCooldown extends Thread implements Runnable {

	private final int cooldownDuration;
	private IPlayer IPlayer;

	public RadarCooldown (IPlayer IPlayer) {
		this.IPlayer = IPlayer;
		this.cooldownDuration = IPlayer.getOfflineCooldown()*1000;
	}

	public RadarCooldown(IPlayer IPlayer, int cooldownDuration) {
		this.IPlayer = IPlayer;
		this.cooldownDuration = cooldownDuration*1000;
	}

	@Override
	public void run() {
		IPlayer.setCanGoOffline(false);
		IPlayer.setOfflineCooldownStops(System.currentTimeMillis()+cooldownDuration);

		try {
			Thread.sleep(cooldownDuration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		IPlayer.setIsAlive(true);
		IPlayer.setOfflineCooldownStops(Long.valueOf(0));
		IPlayer.setCanGoOffline(true);
	}
}
