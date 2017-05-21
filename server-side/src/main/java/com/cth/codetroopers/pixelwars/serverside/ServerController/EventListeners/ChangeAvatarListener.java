package com.cth.codetroopers.pixelwars.serverside.ServerController.EventListeners;

import com.cth.codetroopers.pixelwars.serverside.ServerController.EventObjects.ChangeAvatarEvent;
import Mediator.IMediator;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 5/16/17.
 */
public class ChangeAvatarListener extends EventListener implements DataListener<ChangeAvatarEvent> {
	public ChangeAvatarListener(IMediator mediator) {
		super(mediator);
	}

	public void onData(SocketIOClient socketIOClient, ChangeAvatarEvent changeAvatarEvent, AckRequest ackRequest) throws Exception {
		mediator.changeAvatar(changeAvatarEvent.getPlayerId(),changeAvatarEvent.getAvatarId());
	}
}
