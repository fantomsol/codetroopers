package GameModel.ServerController.EventListeners;

import GameModel.ServerController.EventObjects.ChangeRadarStateEvent;
import Mediator.IMediator;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 4/11/17.
 */
public class ChangeRadarStateListener extends EventListener implements DataListener<ChangeRadarStateEvent> {


	public ChangeRadarStateListener(IMediator mediator) {
		super(mediator);
	}

	public void onData(SocketIOClient socketIOClient, ChangeRadarStateEvent changeRadarStateEvent, AckRequest ackRequest) throws Exception {
		mediator.changeRadarStatus(changeRadarStateEvent.getPlayerId(),changeRadarStateEvent.wantToGoOnline);

		System.out.println("Radar Status Change requested");
	}
}
