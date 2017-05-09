package GameModel.ServerController.EventListeners;

import GameModel.ServerController.EventObjects.SignupEvent;
import Mediator.IMediator;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 5/9/17.
 */
public class SignupListener extends EventListener implements DataListener<SignupEvent> {
	public SignupListener(IMediator mediator) {
		super(mediator);
	}

	public void onData(SocketIOClient socketIOClient, SignupEvent signupEvent, AckRequest ackRequest) throws Exception {
		mediator.registerPlayer(signupEvent.getId());
	}
}
