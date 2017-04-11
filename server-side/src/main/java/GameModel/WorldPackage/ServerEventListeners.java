package GameModel.WorldPackage;

import GameModel.ServerController.EventListeners.*;
import GameModel.ServerController.EventObjects.*;
import com.corundumstudio.socketio.SocketIOServer;

/**
 * Created by latiif on 4/11/17.
 */
class ServerEventListeners {

	private World world;

	ServerEventListeners (World world, SocketIOServer socketIOServer){

		this.world=world;
		addServerEventListeners(socketIOServer);

	}

	private void addServerEventListeners(SocketIOServer socketIOServer){
		socketIOServer.addEventListener(
				"attack",
				AttackEvent.class,
				new AttackEventListener(world)
		);

		socketIOServer.addEventListener(
				"signin",
				SigninEvent.class,
				new SigninListener(world)
		);


		socketIOServer.addEventListener(
				"position-changed",
				PlayerChangePositionEvent.class,
				new PlayerChangePositionListener(world)
		);


		socketIOServer.addEventListener(
				"get-player-info",
				GetPlayerInfoEvent.class,
				new GetPlayerInfoListener(world)
		);

		socketIOServer.addEventListener(
				"change-radar-status",
				ChangeRadarStateEvent.class,
				new ChangeRadarStateListener(world)
		);
	}

}
