package GameModel.ServerController.EventListeners;

import GameModel.Player.Player;
import GameModel.ServerController.EventObjects.GetPlayerInfoEvent;
import GameModel.WorldPackage.World;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 4/1/17.
 */
public class GetPlayerInfoListener  implements DataListener<GetPlayerInfoEvent> {
	private World thisWorld;

	public GetPlayerInfoListener(final  World world){
		thisWorld= world;
	}

	public void onData(SocketIOClient socketIOClient, GetPlayerInfoEvent getPlayerInfoEvent, AckRequest ackRequest) throws Exception {
		Player p=thisWorld.getPlayerById(getPlayerInfoEvent.getId());

		socketIOClient.sendEvent("player-info",p);


	}
}
