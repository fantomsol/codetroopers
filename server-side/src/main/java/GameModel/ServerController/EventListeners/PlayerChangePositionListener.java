package GameModel.ServerController.EventListeners;

import GameModel.Player.GeoPos;
import GameModel.ServerController.EventObjects.PlayerChangePositionEvent;
import GameModel.World;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 4/1/17.
 */
public class PlayerChangePositionListener implements DataListener<PlayerChangePositionEvent>{

	private World thisWorld;

	public PlayerChangePositionListener(final  World world){
		thisWorld= world;
	}


	public void onData(SocketIOClient socketIOClient, PlayerChangePositionEvent playerChangePositionEvent, AckRequest ackRequest) throws Exception {
		thisWorld.playerChangePos(
				playerChangePositionEvent.getId(),
				new GeoPos(playerChangePositionEvent.getLat(),playerChangePositionEvent.getLang())
		);

		System.out.println(thisWorld.getPlayerById(playerChangePositionEvent.getId()));
	}
}
