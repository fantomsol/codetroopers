package GameModel.WorldPackage;

import GameModel.Player.Experience.Exp;
import GameModel.GameUtils.GeoDistance;
import GameModel.Item.Weapons.WeaponsDirectory;
import GameModel.Lootbox.ILootbox;
import GameModel.GameUtils.GeoPos;
import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import GameModel.Shop.IShop;
import GameModel.Shop.Shop;
import Mediator.IWorld;
import Mediator.ServerModelMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by latiif on 3/22/17.
 */
public class World implements IWorld {


	private ServerModelMediator mediator;

	private IShop shop;

	private List<ILootbox> lootboxes;

	private Map<String,IPlayer> players;

	public void createNewPlayer(String name){
		players.put(name,new Player(name, new GeoPos(0.0,0.0)));
	}

	public void setMediator(ServerModelMediator mediator){
		this.mediator= mediator;
	}


	public void changeWeapon(String playerId, Integer weaponID){
		IPlayer p=getPlayerById(playerId);
		p.switchWeapon(weaponID);
		mediator.updatePlayer(p);
	}


	public IShop getShop(){
		return shop;
	}

	public World() {
		players = new HashMap<String, IPlayer>();
		lootboxes= new ArrayList<ILootbox>();
		shop= new Shop();

	}

	public void addLootbox(ILootbox lootbox){
		lootboxes.add(lootbox);
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

		if (attackee.getWeaponEquipped().getId().intValue()== WeaponsDirectory.WHITEFLAG){
			//Exp.getExpOnAttackingUnarmed(attacker);
			attacker.setExp(Exp.getExpOnAttackingUnarmed(attacker.getExp()));
		}

		attacker.attackOtherPlayer(attackee);

		if (mediator!=null) {
			mediator.updateNearbyPlayers(attacker);
			mediator.updatePlayer(attackee);
		}
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

			updateLootboxes(IPlayer);

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
			updateLootboxes(oIPlayer);
		}

		mediator.updatePlayer(IPlayer);
		mediator.updateNearbyPlayers(IPlayer);


		///Check for lootboxes

		updateLootboxes(IPlayer);

	}


	public void updateLootboxes(IPlayer player){
		List<ILootbox> visibleLootboxes= new ArrayList<ILootbox>();

		if (player.isOnline() && player.getIsAlive())
		{
			for (ILootbox lootbox: lootboxes){
				if (GeoDistance.getDistance(lootbox.getGeoPos(),player.getGeoPos())<=player.getVision()){
					visibleLootboxes.add(lootbox);
				}
			}
		}

		System.out.println(player.getID()+" sees a total of "+visibleLootboxes.size()+" lootboxes");
		mediator.updateLootbox(player,visibleLootboxes);
	}


	public void consumeLootboxByGeoPos(String playerId, GeoPos pos){

		IPlayer player=getPlayerById(playerId);

		for (ILootbox lootbox:lootboxes){
			if (lootbox.getGeoPos().equals(pos)){
				//player.consume(lootbox);
				lootbox.consume(player);
				lootboxes.remove(lootbox);

				mediator.playerChangePos(playerId,player.getGeoPos());

				return;
			}
		}


	}


}
