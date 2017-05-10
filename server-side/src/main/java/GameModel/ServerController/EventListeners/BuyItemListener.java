package GameModel.ServerController.EventListeners;

import GameModel.ServerController.EventObjects.BuyItemEvent;
import Mediator.IMediator;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 5/10/17.
 */
public class BuyItemListener extends EventListener implements DataListener<BuyItemEvent> {
	public BuyItemListener(IMediator mediator) {
		super(mediator);
	}

	public void onData(SocketIOClient socketIOClient, BuyItemEvent buyItemEvent, AckRequest ackRequest) throws Exception {
		mediator.buyItem(mediator.getPlayerById(buyItemEvent.playerId),buyItemEvent.itemId,buyItemEvent.getItemType());
	}
}
