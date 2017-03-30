import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.eclipsesource.json.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.unix.Socket;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by latiif on 3/20/17.
 */
public class Server {

	ObjectMapper mapper;
	static private SocketIOServer socketIOServer;

	static private void startServer(){
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




		socketIOServer.addEventListener("moved",MovedEvent.class, new DataListener<MovedEvent>() {
			@Override
			public void onData(SocketIOClient socketIOClient, MovedEvent s, AckRequest ackRequest) throws Exception {

				System.out.println(s.getLang());

				MovedEvent movedEvent=new MovedEvent(57.709167,11.971756);

				//socketIOServer.getBroadcastOperations().sendEvent("server-respond",movedEvent);


				socketIOClient.sendEvent("opponent",movedEvent);
				 movedEvent=new MovedEvent(57.709167,11.9716755);

				//socketIOServer.getBroadcastOperations().sendEvent("server-respond",movedEvent);


				socketIOClient.sendEvent("opponent",movedEvent);
			}
		});




		socketIOServer.start();


	}

	public static void main(String[] args) {

		System.out.println("Running");
		startServer();



	}
}
