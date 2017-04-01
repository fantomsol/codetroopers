package GameModel;

import GameModel.GameUtils.GeoDistance;
import GameModel.Player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by latiif on 3/22/17.
 */
public class World {

	private List<Player> players;

	public World(){
		players=new ArrayList<Player>();
	}


	public Player getPlayer(Player p){
		try {
			return players.get(players.indexOf(p));
		}
		catch (IndexOutOfBoundsException e){
			return null;
		}
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

	public void playerChangePos(final String id){
		Player player= getPlayerById(id);
		if (!player.isOnline()){
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
			
			if (pSeesOp){
				System.out.println(player.getID()+" sees "+oPlayer.getID());
			}

			if (opSeesP){
				System.out.println(oPlayer.getID()+" sees "+player.getID());
			}

		}
	}

}
