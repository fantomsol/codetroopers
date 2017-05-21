package com.cth.codetroopers.pixelwars.serverside.ServerController.EventListeners;

import com.cth.codetroopers.pixelwars.serverside.ServerController.EventObjects.ConsumeLootboxEvent;
import Mediator.IMediator;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 5/8/17.
 */
public class ConsumeLootboxListener extends EventListener implements DataListener<ConsumeLootboxEvent> {
	public ConsumeLootboxListener(IMediator mediator) {
		super(mediator);
	}

	public void onData(SocketIOClient socketIOClient, ConsumeLootboxEvent consumeLootboxEvent, AckRequest ackRequest) throws Exception {
		mediator.consumeLootbox(consumeLootboxEvent.getId(),consumeLootboxEvent.getGeoPos());
	}
}
