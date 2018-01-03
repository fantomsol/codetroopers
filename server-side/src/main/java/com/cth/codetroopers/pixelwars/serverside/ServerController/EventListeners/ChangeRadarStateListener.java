package com.cth.codetroopers.pixelwars.serverside.ServerController.EventListeners;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.ServerController.EventObjects.ChangeRadarStateEvent;
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
		try {
			mediator.changeRadarStatus(changeRadarStateEvent.getPlayerId(),changeRadarStateEvent.wantToGoOnline);
		} catch (GameException e) {
			mediator.sendPlayerSpecificException(mediator.getCharacterById(changeRadarStateEvent.getPlayerId()),e);
		}

		System.out.println("Radar Status Change requested");
	}
}
