package GameModel.ServerController.EventListeners;

import GameModel.GameUtils.GeoPos;
import GameModel.ServerController.EventObjects.PlayerChangePositionEvent;
import Mediator.IMediator;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 4/1/17.
 */
public class PlayerChangePositionListener extends EventListener implements DataListener<PlayerChangePositionEvent>{


	public PlayerChangePositionListener(IMediator mediator) {
		super(mediator);
	}

	public void onData(SocketIOClient socketIOClient, PlayerChangePositionEvent playerChangePositionEvent, AckRequest ackRequest) throws Exception {
		mediator.playerChangePos(
				playerChangePositionEvent.getId(),
				new GeoPos(playerChangePositionEvent.getLat(),playerChangePositionEvent.getLang())
		);

		System.out.println(mediator.getPlayerById(playerChangePositionEvent.getId()));
	}
}
