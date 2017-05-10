package GameModel.ServerController.EventListeners;

import GameModel.Player.IPlayer;
import GameModel.ServerController.EventObjects.GetShopItemsEvent;
import Mediator.IMediator;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 5/10/17.
 */
public class GetShopItemsListener extends EventListener implements DataListener<GetShopItemsEvent> {
	GetShopItemsListener(IMediator mediator) {
		super(mediator);
	}

	public void onData(SocketIOClient socketIOClient, GetShopItemsEvent getShopItemsEvent, AckRequest ackRequest) throws Exception {
		IPlayer player= mediator.getPlayerById(getShopItemsEvent.getPlayerId());
		if (player!=null) {
			mediator.updatePlayerShopItems(player,mediator.getShopItems());
		}
	}
}
