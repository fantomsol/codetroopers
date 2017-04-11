package GameModel.ServerController.EventListeners;

import GameModel.Player.Player;
import GameModel.ServerController.EventObjects.ChangeRadarStateEvent;
import GameModel.WorldPackage.World;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 4/11/17.
 */
public class ChangeRadarStateListener implements DataListener<ChangeRadarStateEvent> {

	private World thisWorld;
	public ChangeRadarStateListener(World world){

		thisWorld=world;
	}

	public void onData(SocketIOClient socketIOClient, ChangeRadarStateEvent changeRadarStateEvent, AckRequest ackRequest) throws Exception {
		Player p=thisWorld.getPlayerById(changeRadarStateEvent.getPlayerId());

		if (changeRadarStateEvent.wantToGoOnline) {
			p.goOnline();
		}
		else {
			p.goOffline();
		}

		thisWorld.playerChangePos(p.getID(),p.getGeoPos());
		thisWorld.getServer().updatePlayer(p);


		System.out.println("Radar Status Change requested");
	}
}
