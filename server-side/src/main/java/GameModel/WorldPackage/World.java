package GameModel.WorldPackage;

import GameModel.GameUtils.GeoDistance;
import GameModel.Player.GeoPos;
import GameModel.Player.Player;
import Mediator.ServerModelMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by latiif on 3/22/17.
 */
public class World {


	private ServerModelMediator mediator;


	private Map<String,Player> players;

	public void setMediator(ServerModelMediator mediator){
		this.mediator= mediator;
	}


	public void changeWeapon(String playerId,Integer weaponID){
		Player p=getPlayerById(playerId);
		p.switchWeapon(weaponID);
		mediator.updatePlayer(p);
	}


	//Dependency is injected on constructor
	public World() {
		players = new HashMap<String, Player>();

	}


	public Player getPlayer(Player p){
		try {
			return players.get(p.getID());
		}
		catch (IndexOutOfBoundsException e){
			return null;
		}
	}

	public void performAttack(String attackerId, String attackeeId){
		Player attacker = getPlayerById(attackerId);
		Player attackee= getPlayerById(attackeeId);

		attacker.attackOtherPlayer(attackee);

		mediator.updateNearbyPlayers(attacker);
		mediator.updatePlayer(attackee);
	}

	public Player getPlayerById(final String id){
		return players.get(id);
	}


	public void registerPlayer(Player player){
		players.put(player.getID(),player);
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
				mediator.updateNearbyPlayers(op);
			}

			//someone who's offline cannot see anyone
			player.getPlayersNearby().clear();
			//update the player's nearby list
			mediator.updateNearbyPlayers(player);

			//no need to do anything else
			return;
		}

		for (Player oPlayer:players.values()){
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
			mediator.updateNearbyPlayers(oPlayer);
		}

		mediator.updatePlayer(player);
		mediator.updateNearbyPlayers(player);
	}



}
