package com.cth.codetroopers.pixelwars.serverside.ServerController;

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

	public Server() {
		map = new Hashtable<Object, SocketIOClient>();
	}

	public Map<Object, SocketIOClient> map;

	SocketIOServer socketIOServer;

	public SocketIOServer getServerSocket() {
		return socketIOServer;
	}


	public void startServer(String hostname, int port) {
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

				for (Map.Entry<Object, SocketIOClient> entry : map.entrySet()) {

					if (socketIOClient.equals(entry.getValue())) {
						//System.out.println("Removed data for player "+entry.getKey().getID());
						map.remove(entry.getKey());
						break;
					}
				}
			}
		});

		socketIOServer.start();
	}

	public void startServer() {
		startServer("127.0.0.1", 3000);
	}

	public void shutdown() {
		socketIOServer.stop();
	}

	//Sends data back to the player
	public void updateNearbyPlayers(final Object player, final List<Object> players) {
		if (map.containsKey(player)) {


			Object[] array = new Object[players.size()];

			for (int i = 0; i < array.length; i++) {
				array[i] = players.get(i);
			}

			map.get(player).sendEvent("nearby-players-update", array);
		}
	}

	public void updateLootbox(Object player, List<Object> lootboxes) {
		if (map.containsKey(player)) {

			Object[] array = new Object[lootboxes.size()];

			for (int i = 0; i < array.length; i++) {
				array[i] = lootboxes.get(i);
			}
			map.get(player).sendEvent("lootbox-update", array);
		}
	}

	public void updatePlayer(Object player) {
		if (map.containsKey(player)) {
			map.get(player).sendEvent("player-info", player);
			//System.out.println("sending player info to "+ player.getID());
		}
	}

	private ServerModelMediator mediator;

	public void setMediator(ServerModelMediator serverModelMediator) {

		mediator = serverModelMediator;
		new ServerEventListeners(mediator, getServerSocket());
	}

	public void playerSignin(Object p, SocketIOClient socketIOClient) {
		map.put(p, socketIOClient);

		//updateNearbyPlayers(p);
		updatePlayer(p);
	}

	public void sendShopList(Object p, List<Object> list) {
		if (map.containsKey(p)) {

			Object[] array = new Object[list.size()];

			for (int i = 0; i < array.length; i++) {
				array[i] = list.get(i);
			}
			map.get(p).sendEvent("shopitems-listed", array);
		}
	}

	public void sendException(Object exception) {
		for (Object p : map.keySet()) {
			sendException(p, exception);
		}
	}

	public void sendException(SocketIOClient socket, Object exception) {
		socket.sendEvent("server-side-exception", exception);
	}

	public void sendException(Object player, Object exception) {
		sendException(map.get(player), exception);
	}

	public <T> JsonArray list2JsonArray(List<T> list) {

		JsonArray res = new JsonArray();

		for (T o : list) {
			res.add(o.toString());
		}
		return res;
	}

}
