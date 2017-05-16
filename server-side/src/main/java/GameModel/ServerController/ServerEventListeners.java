package GameModel.ServerController;

import GameModel.ServerController.EventListeners.*;
import GameModel.ServerController.EventObjects.*;
import Mediator.IMediator;
import com.corundumstudio.socketio.SocketIOServer;

/**
 * Created by latiif on 4/11/17.
 */
class ServerEventListeners {

	private IMediator mediator;

	ServerEventListeners (IMediator mediator,SocketIOServer socketIOServer){

		this.mediator=mediator;
		addServerEventListeners(socketIOServer);

	}

	private void addServerEventListeners(SocketIOServer socketIOServer){
		socketIOServer.addEventListener(
				"attack",
				AttackEvent.class,
				new AttackEventListener(mediator)
		);

		socketIOServer.addEventListener(
				"signin",
				SigninEvent.class,
				new SigninListener(mediator)
		);


		socketIOServer.addEventListener(
				"position-changed",
				PlayerChangePositionEvent.class,
				new PlayerChangePositionListener(mediator)
		);


		socketIOServer.addEventListener(
				"get-player-info",
				GetPlayerInfoEvent.class,
				new GetPlayerInfoListener(mediator)
		);

		socketIOServer.addEventListener(
				"change-radar-status",
				ChangeRadarStateEvent.class,
				new ChangeRadarStateListener(mediator)
		);

		socketIOServer.addEventListener(
				"change-weapon",
				ChangeWeaponEvent.class,
				new ChangeWeaponListener(mediator)
		);

		socketIOServer.addEventListener(
				"consume-lootbox",
				ConsumeLootboxEvent.class,
				new ConsumeLootboxListener(mediator)
		);

		socketIOServer.addEventListener(
				"signup",
				SignupEvent.class,
				new SignupListener(mediator)
		);

		socketIOServer.addEventListener(
				"get-shopitems",
				GetShopItemsEvent.class,
				new GetShopItemsListener(mediator)
		);

		socketIOServer.addEventListener(
				"sell-item",
				SellItemEvent.class,
				new SellItemListener(mediator)
		);

		socketIOServer.addEventListener(
				"buy-item",
				BuyItemEvent.class,
				new BuyItemListener(mediator)
		);

		socketIOServer.addEventListener(
				"change-avatar",
				ChangeAvatarEvent.class,
				new ChangeAvatarListener(mediator)
		);
	}

}
