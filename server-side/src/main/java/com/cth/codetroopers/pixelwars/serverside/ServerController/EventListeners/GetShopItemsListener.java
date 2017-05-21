package com.cth.codetroopers.pixelwars.serverside.ServerController.EventListeners;

import com.cth.codetroopers.pixelwars.serverside.ServerController.EventObjects.GetShopItemsEvent;
import Mediator.IMediator;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 5/10/17.
 */
public class GetShopItemsListener extends EventListener implements DataListener<GetShopItemsEvent> {
	public GetShopItemsListener(IMediator mediator) {
		super(mediator);
	}

	public void onData(SocketIOClient socketIOClient, GetShopItemsEvent getShopItemsEvent, AckRequest ackRequest) throws Exception {
		mediator.getShopItems(getShopItemsEvent.playerId);
	}
}
