package GameModel.WorldPackage;

import GameModel.ServerController.EventListeners.AttackEventListener;
import GameModel.ServerController.EventListeners.GetPlayerInfoListener;
import GameModel.ServerController.EventListeners.PlayerChangePositionListener;
import GameModel.ServerController.EventListeners.SigninListener;
import GameModel.ServerController.EventObjects.AttackEvent;
import GameModel.ServerController.EventObjects.GetPlayerInfoEvent;
import GameModel.ServerController.EventObjects.PlayerChangePositionEvent;
import GameModel.ServerController.EventObjects.SigninEvent;
import com.corundumstudio.socketio.SocketIOServer;

/**
 * Created by latiif on 4/11/17.
 */
public class ServerEventListeners {

	private World world;

	ServerEventListeners (World world){

		this.world=world;

	}

	public void addServerEventListeners(SocketIOServer socketIOServer){
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
	}

}
