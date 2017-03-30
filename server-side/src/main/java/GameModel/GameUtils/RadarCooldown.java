package GameModel.GameUtils;

import GameModel.Player.Player;

/**
 * Created by latiif on 3/29/17.
 */
public class RadarCooldown extends Thread implements Runnable {

	private final int cooldownDuration;
	private Player player;

	public RadarCooldown(Player player) {
		this.cooldownDuration = player.getOfflineCooldown()*1000;
		this.player=player;
	}


	@Override
	public void run() {
		player.setCanGoOffline(false);
		try {
			Thread.sleep(cooldownDuration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		player.setCanGoOffline(true);
	}
}
