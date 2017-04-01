package GameModel.ServerController;

import GameModel.Player.GeoPos;
import GameModel.Player.Player;
import GameModel.ServerController.EventListeners.PlayerChangePositionListener;
import GameModel.ServerController.EventObjects.PlayerChangePositionEvent;
import GameModel.World;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by latiif on 3/20/17.
 */
public class Server {

	static private SocketIOServer socketIOServer;
	static public World world= new World();

	static public void startServer(){
		Configuration config = new Configuration();
		config.setHostname("192.168.10.186");
		config.setPort(3000);


		socketIOServer = new SocketIOServer(config);

		socketIOServer.addConnectListener(new ConnectListener() {
			@Override
			public void onConnect(SocketIOClient socketIOClient) {
				System.out.println("Client Connected");
			}
		});





		socketIOServer.addEventListener(
				"position-changed",
				PlayerChangePositionEvent.class,
				new PlayerChangePositionListener(world)
		);

				/*
		socketIOServer.addEventListener("moved",PlayerChangePositionEvent.class, new DataListener<PlayerChangePositionEvent>() {
			@Override
			public void onData(SocketIOClient socketIOClient, PlayerChangePositionEvent s, AckRequest ackRequest) throws Exception {

				System.out.println(s.getLang());

				PlayerChangePositionEvent movedEvent=new PlayerChangePositionEvent(57.709167,11.971756);

				//socketIOServer.getBroadcastOperations().sendEvent("server-respond",movedEvent);


				socketIOClient.sendEvent("opponent",movedEvent);
				 movedEvent=new PlayerChangePositionEvent(57.709167,11.9716755);

				//socketIOServer.getBroadcastOperations().sendEvent("server-respond",movedEvent);


				socketIOClient.sendEvent("opponent",movedEvent);
			}
		});
		*/




		socketIOServer.start();


	}

	public static void main(String[] args) {

		startServer();

		Player p1=new Player("Llusx",new GeoPos(0.0,0.0));
		Player p2= new Player("test",new GeoPos(0.0,0.0));

		p1.goOnline();;
		p2.goOnline();

		world.registerPlayer(p1);
		world.registerPlayer(p2);



	}
}
