package GameModel.WorldPackage;

import GameModel.GameUtils.GeoDistance;
import GameModel.Player.GeoPos;
import GameModel.Player.Player;
import GameModel.ServerController.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by latiif on 3/22/17.
 */
public class World {

	private List<Player> players;

	private Server server;

	public Server getServer(){
		return server;
	}

	public World(){
		players=new ArrayList<Player>();


		server= new Server();

		server.startServer();

		ServerEventListeners serverEventListeners= new ServerEventListeners(this, server.getServerSocket());



	}


	public Player getPlayer(Player p){
		try {
			return players.get(players.indexOf(p));
		}
		catch (IndexOutOfBoundsException e){
			return null;
		}
	}

	public void performAttack(String attackerId, String attackeeId){
		Player attacker = getPlayerById(attackerId);
		Player attackee= getPlayerById(attackeeId);

		attacker.attackOtherPlayer(attackee);

		server.updatePlayer(attacker);
		server.updatePlayer(attackee);
	}

	public Player getPlayerById(final String id){
		for(Player player:players){
			if (player.getID().equals(id)){
				return player;
			}
		}

		return null;
	}


	public void registerPlayer(Player player){
		players.add(player);
	}

	public void playerChangePos(final String id, final GeoPos newPos){
		Player player= getPlayerById(id);
		player.updatePos(newPos);

		System.out.println(players.size());

		if (!player.isOnline()){

			//Remove the player from their nearby players' lists
			for (Player op:player.getPlayersNearby()){
				op.getPlayersNearby().remove(player);
				//update the other player's nearby list
				server.updateNearbyPlayers(op);
			}

			//someone who's offline cannot see anyone
			player.getPlayersNearby().clear();
			//update the player's nearby list
			server.updateNearbyPlayers(player);

			//no need to do anything else
			return;
		}

		for (Player oPlayer:players){
			if (!oPlayer.isOnline()){
				continue;
			}
			if (player.equals(oPlayer)){
				continue;
			}

			boolean pSeesOp = GeoDistance.getDistance(player.getGeoPos(), oPlayer.getGeoPos())<=player.getVision();
			boolean opSeesP = GeoDistance.getDistance(player.getGeoPos(), oPlayer.getGeoPos())<=oPlayer.getVision();; 


			//Player sees the other player
			if (pSeesOp){

				//The other player was already in the vicinity of player
				if (player.getPlayersNearby().contains(oPlayer)){

				}

				//The other player has just entered player's vision range
				if (!player.getPlayersNearby().contains(oPlayer)){
					player.addNearbyPlayer(oPlayer);
				}
			}else {
				//Player does not see oPlayer

				//if other player is no longer in player's vision range
				if (player.getPlayersNearby().contains(oPlayer)){
					player.removeNearbyPlayer(oPlayer);
				}
			}


			//Exact reverse logic

			//Player sees the other player
			if (opSeesP){

				//The other player was already in the vicinity of player
				if (oPlayer.getPlayersNearby().contains(player)){

				}

				//The other player has just entered player's vision range
				if (!oPlayer.getPlayersNearby().contains(player)){
					oPlayer.addNearbyPlayer(player);
				}
			}else {
				//Player does not see oPlayer

				//if other player is no longer in player's vision range
				if (oPlayer.getPlayersNearby().contains(player)){
					oPlayer.removeNearbyPlayer(player);
				}
			}
			server.updateNearbyPlayers(oPlayer);
		}

		server.updateNearbyPlayers(player);
	}



}
