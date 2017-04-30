package GameModel.ServerController.EventListeners;

import GameModel.ServerController.EventObjects.AttackEvent;
import GameModel.WorldPackage.World;
import Mediator.IMediator;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 4/9/17.
 */
public class AttackEventListener extends EventListener implements DataListener<AttackEvent> {


	public AttackEventListener(IMediator mediator) {
		super(mediator);
	}

	public void onData(SocketIOClient socketIOClient, AttackEvent attackEvent, AckRequest ackRequest) throws Exception {
		mediator.performAttack(attackEvent.getId(),attackEvent.getoId());

		System.out.println(attackEvent.getId() + " is attacking "+attackEvent.getoId());
	}
}
