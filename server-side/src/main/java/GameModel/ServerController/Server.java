package GameModel.ServerController;

import GameModel.Lootbox.ILootbox;
import GameModel.Player.IPlayer;
import GameModel.Player.Player;

import Mediator.ServerModelMediator;
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
public class Server implements IServer {



	public Server(){
		map= new Hashtable<IPlayer, SocketIOClient>();


	}

	public  Map<IPlayer,SocketIOClient> map;

	 SocketIOServer socketIOServer;

	 public SocketIOServer getServerSocket(){
	 	return socketIOServer;
	 }


	 public void startServer(String hostname, int port){
		 Configuration config = new Configuration();
		 config.setHostname(hostname);
		 config.setPort(port);


		 socketIOServer = new SocketIOServer(config);

		 socketIOServer.addConnectListener(new ConnectListener() {
			 public void onConnect(SocketIOClient socketIOClient) {
				 System.out.println("Client Connected");
			 }
		 });

		 socketIOServer.addDisconnectListener(new DisconnectListener() {
			 public void onDisconnect(SocketIOClient socketIOClient) {


				 for (Map.Entry<IPlayer, SocketIOClient> entry : map.entrySet()) {

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

	 public void startServer(){
	 	startServer("127.0.0.1",3000);
	}


	public void shutdown(){
	 	socketIOServer.stop();
	}

	//Sends data back to the player
	public  void updateNearbyPlayers(final IPlayer IPlayer){
		if (map.containsKey(IPlayer)) {


			IPlayer[] array= new IPlayer[IPlayer.getPlayersNearby().size()];

			for(int i=0;i<array.length;i++){
				array[i]= IPlayer.getPlayersNearby().get(i);
			}

			map.get(IPlayer).sendEvent("nearby-players-update",array);
		}

	}


	public void updateLootbox(IPlayer player, ILootbox lootbox){
		if (map.containsKey(player)){
			map.get(player).sendEvent("lootbox",lootbox);
		}
	}

	public  void updatePlayer(IPlayer player){
		if (map.containsKey(player)){
			map.get(player).sendEvent("player-info", player);
			System.out.println("sending player info to "+ player.getID());
		}
	}

	private ServerModelMediator mediator;
	public void setMediator(ServerModelMediator serverModelMediator) {

		mediator=serverModelMediator;
		new ServerEventListeners(mediator,getServerSocket());
	}

	public void playerSignin(IPlayer p, SocketIOClient socketIOClient) {
		map.put(p,socketIOClient);

		updateNearbyPlayers(p);
		updatePlayer(p);
	}

	public <T> JsonArray list2JsonArray(List<T> list){

		JsonArray res= new JsonArray();

		for (T o: list){
			res.add(o.toString());
		}

		return res;
	}

}
