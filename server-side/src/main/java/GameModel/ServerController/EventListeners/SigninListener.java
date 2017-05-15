package GameModel.ServerController.EventListeners;

import GameModel.ServerController.EventObjects.SigninEvent;
import Mediator.IMediator;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 4/1/17.
 */
public class SigninListener extends EventListener implements DataListener<SigninEvent> {


	public SigninListener(IMediator mediator) {
		super(mediator);
	}

	public void onData(SocketIOClient socketIOClient, SigninEvent signinEvent, AckRequest ackRequest) throws Exception {

		mediator.playerSignin(signinEvent.getId(), socketIOClient);
		}
		
}
