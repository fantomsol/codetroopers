package GameModel;

import GameModel.Player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by latiif on 3/22/17.
 */
public class World {

	private List<Player> players;

	public World(){
		players=new ArrayList<>();
	}



	public Player getPlayer(Player p){
		try {
			return players.get(players.indexOf(p));
		}
		catch (IndexOutOfBoundsException e){
			return null;
		}
	}


	public void registerPlayer(Player player){
		players.add(player);
	}

}
