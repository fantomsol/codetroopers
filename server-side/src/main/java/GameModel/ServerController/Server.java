package GameModel.ServerController;

import GameModel.Player.GeoPos;
import GameModel.Player.Player;
import GameModel.ServerController.EventListeners.AttackEventListener;
import GameModel.ServerController.EventListeners.GetPlayerInfoListener;
import GameModel.ServerController.EventListeners.PlayerChangePositionListener;
import GameModel.ServerController.EventListeners.SigninListener;
import GameModel.ServerController.EventObjects.AttackEvent;
import GameModel.ServerController.EventObjects.GetPlayerInfoEvent;
import GameModel.ServerController.EventObjects.PlayerChangePositionEvent;
import GameModel.ServerController.EventObjects.SigninEvent;
import GameModel.WorldPackage.World;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.eclipsesource.json.JsonArray;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * Created by latiif on 3/20/17.
 */
public class Server {

	public  Map<Player,SocketIOClient> map= new Hashtable<Player, SocketIOClient>();

	 SocketIOServer socketIOServer;

	 public SocketIOServer getServerSocket(){
	 	return socketIOServer;
	 }

	 public void startServer(){
		Configuration config = new Configuration();
		config.setHostname("127.0.0.1");
		config.setPort(3000);


		socketIOServer = new SocketIOServer(config);

		socketIOServer.addConnectListener(new ConnectListener() {
			public void onConnect(SocketIOClient socketIOClient) {
				System.out.println("Client Connected");
			}
		});

		socketIOServer.addDisconnectListener(new DisconnectListener() {
			public void onDisconnect(SocketIOClient socketIOClient) {


				for (Map.Entry<Player, SocketIOClient> entry : map.entrySet()) {

					if (socketIOClient.equals(entry.getValue())){
						System.out.println("Removed data for player "+entry.getKey().getID());
						map.remove(entry.getKey());
						break;
					}
				}

			}
		});



		socketIOServer.start();
	}


	//Sends data back to the player
	public  void updateNearbyPlayers(final Player player){
		if (map.containsKey(player)) {


			Player[] array= new Player[player.getPlayersNearby().size()];

			for(int i=0;i<array.length;i++){
				array[i]=player.getPlayersNearby().get(i);
			}

			map.get(player).sendEvent("nearby-players-update",array);
		}
	}


	public  void updatePlayer(Player player){
		if (map.containsKey(player)){
			map.get(player).sendEvent("player-info",player);
		}
	}

	public <T> JsonArray list2JsonArray(List<T> list){

		JsonArray res= new JsonArray();

		for (T o: list){
			res.add(o.toString());
		}

		return res;
	}

	public static void main(String[] args) {
/*
		startServer();

		Player p1=new Player("Llusx",new GeoPos(0.0,0.0));
		Player p2= new Player("test",new GeoPos(0.0,0.0));
		Player p3= new Player("Lucky",new GeoPos(0.0,0.0));
		Player p4= new Player("Jade",new GeoPos(0.0,0.0));


		p1.goOnline();
		//p2.goOnline();
		p4.goOnline();
		p4.updatePos(new GeoPos(38.63473,-90.29408));

		world.registerPlayer(p1);
		world.registerPlayer(p2);
		world.registerPlayer(p3);
		world.registerPlayer(p4);

*/
	}
}
