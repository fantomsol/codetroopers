package GameModel.ServerController.EventListeners;

import GameModel.Player.IPlayer;
import GameModel.ServerController.EventObjects.GetPlayerInfoEvent;
import Mediator.IMediator;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 4/1/17.
 */
public class GetPlayerInfoListener extends EventListener implements DataListener<GetPlayerInfoEvent> {


	public GetPlayerInfoListener(IMediator mediator) {
		super(mediator);
	}

	public void onData(SocketIOClient socketIOClient, GetPlayerInfoEvent getPlayerInfoEvent, AckRequest ackRequest) throws Exception {
		IPlayer p=mediator.getPlayerById(getPlayerInfoEvent.getId());

		socketIOClient.sendEvent("player-info",p);


	}
}
