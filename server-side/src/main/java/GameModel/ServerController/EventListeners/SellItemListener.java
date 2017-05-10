package GameModel.ServerController.EventListeners;

import GameModel.ServerController.EventObjects.SellItemEvent;
import Mediator.IMediator;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 5/10/17.
 */
public class SellItemListener extends EventListener implements DataListener<SellItemEvent> {
	public SellItemListener(IMediator mediator) {
		super(mediator);
	}

	public void onData(SocketIOClient socketIOClient, SellItemEvent sellItemEvent, AckRequest ackRequest) throws Exception {
		mediator.sellItem(mediator.getPlayerById(sellItemEvent.playerId),sellItemEvent.getItemId(),sellItemEvent.getItemType());
	}
}
