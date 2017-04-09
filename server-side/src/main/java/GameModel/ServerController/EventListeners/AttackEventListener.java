package GameModel.ServerController.EventListeners;

import GameModel.ServerController.EventObjects.AttackEvent;
import GameModel.World;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 4/9/17.
 */
public class AttackEventListener implements DataListener<AttackEvent> {

	private World world;

	public AttackEventListener(final World world){
		this.world=world;
	}

	public void onData(SocketIOClient socketIOClient, AttackEvent attackEvent, AckRequest ackRequest) throws Exception {
		world.performAttack(attackEvent.getId(),attackEvent.getoId());

		System.out.println(attackEvent.getId() + " is attacking "+attackEvent.getoId());
	}
}
