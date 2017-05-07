package GameModel.WorldPackage;

import GameModel.GameUtils.GeoDistance;
import GameModel.Lootbox.ILootbox;
import GameModel.Player.GeoPos;
import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import Mediator.ServerModelMediator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by latiif on 3/22/17.
 */
public class World {


	private ServerModelMediator mediator;

	private List<ILootbox> lootboxes;

	private Map<String,IPlayer> players;

	public void setMediator(ServerModelMediator mediator){
		this.mediator= mediator;
	}


	public void changeWeapon(String playerId,Integer weaponID){
		IPlayer p=getPlayerById(playerId);
		p.switchWeapon(weaponID);
		mediator.updatePlayer(p);
	}


	//Dependency is injected on constructor
	public World() {
		players = new HashMap<String, IPlayer>();

	}


	public IPlayer getPlayer(IPlayer p){
		try {
			return players.get(p.getID());
		}
		catch (IndexOutOfBoundsException e){
			return null;
		}
	}

	public void performAttack(String attackerId, String attackeeId){
		IPlayer attacker = getPlayerById(attackerId);
		IPlayer attackee= getPlayerById(attackeeId);

		attacker.attackOtherPlayer(attackee);

		mediator.updateNearbyPlayers(attacker);
		mediator.updatePlayer(attackee);
	}

	public IPlayer getPlayerById(final String id){
		return players.get(id);
	}


	public void registerPlayer(IPlayer IPlayer){
		players.put(IPlayer.getID(), IPlayer);
	}

	public void playerChangePos(final String id, final GeoPos newPos){
		IPlayer IPlayer = getPlayerById(id);
		IPlayer.updatePos(newPos);

		System.out.println(players.size());

		if (!IPlayer.isOnline()){

			//Remove the player from their nearby players' lists
			for (IPlayer op: IPlayer.getPlayersNearby()){
				op.getPlayersNearby().remove(IPlayer);
				//update the other player's nearby list
				mediator.updateNearbyPlayers(op);
			}

			//someone who's offline cannot see anyone
			IPlayer.getPlayersNearby().clear();
			//update the player's nearby list
			mediator.updateNearbyPlayers(IPlayer);

			//no need to do anything else
			return;
		}

		for (IPlayer oIPlayer :players.values()){
			if (!oIPlayer.isOnline()){
				continue;
			}
			if (IPlayer.equals(oIPlayer)){
				continue;
			}

			boolean pSeesOp = GeoDistance.getDistance(IPlayer.getGeoPos(), oIPlayer.getGeoPos())<= IPlayer.getVision();
			boolean opSeesP = GeoDistance.getDistance(IPlayer.getGeoPos(), oIPlayer.getGeoPos())<= oIPlayer.getVision();;


			//Player sees the other player
			if (pSeesOp){

				//The other player was already in the vicinity of player
				if (IPlayer.getPlayersNearby().contains(oIPlayer)){

				}

				//The other player has just entered player's vision range
				if (!IPlayer.getPlayersNearby().contains(oIPlayer)){
					IPlayer.addNearbyPlayer(oIPlayer);
				}
			}else {
				//Player does not see oPlayer

				//if other player is no longer in player's vision range
				if (IPlayer.getPlayersNearby().contains(oIPlayer)){
					IPlayer.removeNearbyPlayer(oIPlayer);
				}
			}


			//Exact reverse logic

			//Player sees the other player
			if (opSeesP){

				//The other player was already in the vicinity of player
				if (oIPlayer.getPlayersNearby().contains(IPlayer)){

				}

				//The other player has just entered player's vision range
				if (!oIPlayer.getPlayersNearby().contains(IPlayer)){
					oIPlayer.addNearbyPlayer(IPlayer);
				}
			}else {
				//Player does not see oPlayer

				//if other player is no longer in player's vision range
				if (oIPlayer.getPlayersNearby().contains(IPlayer)){
					oIPlayer.removeNearbyPlayer(IPlayer);
				}
			}
			mediator.updateNearbyPlayers(oIPlayer);
		}

		mediator.updatePlayer(IPlayer);
		mediator.updateNearbyPlayers(IPlayer);
	}



}
