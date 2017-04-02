package GameModel.ServerController.EventListeners;

import GameModel.ServerController.EventObjects.SigninEvent;
import GameModel.ServerController.Server;
import GameModel.World;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 4/1/17.
 */
public class SigninListener implements DataListener<SigninEvent> {

	private World thisWorld;

	public SigninListener(World world){
		thisWorld= world;
	}


	public void onData(SocketIOClient socketIOClient, SigninEvent signinEvent, AckRequest ackRequest) throws Exception {

		Server.map.put(thisWorld.getPlayerById(signinEvent.getId()),socketIOClient);
	}
}
