package GameModel.Player;

/**
 * Created by latiif on 5/15/17.
 */
public abstract class Cooldown extends Thread implements Runnable {
	protected final int cooldownDuration;
	protected final IPlayer player;

	public Cooldown(int cooldownDuration, GameModel.Player.IPlayer iPlayer) {
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
