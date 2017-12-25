package com.cth.codetroopers.pixelwars.serverside.WorldPackage;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.Player.Avatar.Avatar;
import com.cth.codetroopers.pixelwars.serverside.Player.Experience.Exp;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoDistance;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.WeaponsDirectory;
import com.cth.codetroopers.pixelwars.serverside.Lootbox.ILootbox;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Player.Player;
import com.cth.codetroopers.pixelwars.serverside.Shop.IShop;
import com.cth.codetroopers.pixelwars.serverside.Shop.Shop;
import Mediator.ServerModelMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by latiif on 3/22/17.
 */
public class World implements IWorld {


	private ServerModelMediator mediator;

	private IShop shop;

	private List<ILootbox> lootboxes;

	private Map<String,IPlayer> players;

	public void createNewPlayer(String name) throws GameException {
			players.put(name,new Player(name, new GeoPos(0.0,0.0)));

	}

	public void setMediator(ServerModelMediator mediator){
		this.mediator= mediator;
	}


	public void changeWeapon(String playerId, Integer weaponID) throws GameException {
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
		return players.get(p.getID());
	}

	public void performAttack(String attackerId, String attackeeId) throws GameException {
		IPlayer attacker = getPlayerById(attackerId);
		IPlayer attackee= getPlayerById(attackeeId);

		//cannot attack a ghost
		if (!attackee.getIsAlive()){
			throw new GameException("Invalid target",attackeeId +" is dead players cannot be attacked");
		}


		if (attackee.getWeaponEquipped().getId().intValue()== WeaponsDirectory.WHITEFLAG){
			//Exp.getExpOnAttackingUnarmed(attacker);
			attacker.setExp(Exp.getExpOnAttackingUnarmed(attacker.getExp()));
		}

		attacker.attackPlayer(attackee);

		if (mediator!=null) {
			mediator.updateNearbyPlayers(attacker);
			mediator.updatePlayer(attackee);
		}

		//after attacking, the attackee is dead
		if (!attackee.getIsAlive()){
			revivePlayer(attackee);
		}
	}


	private void revivePlayer(final IPlayer p){
		Thread thread=new Thread(new Runnable() {
			public void run() {
				while (true){
					try {
						TimeUnit.SECONDS.sleep(1);
						if (p.getIsAlive()){
							break;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("Just revived "+p);
				try {
					playerChangePos(p.getID(),p.getGeoPos());
				} catch (GameException e) {

				}
				if (mediator!=null) {
					mediator.updatePlayer(p);
					mediator.updateNearbyPlayers(p);
				}
			}
		});

		thread.start();
	}


	public IPlayer getPlayerById(final String id) throws GameException {
		IPlayer res=players.get(id);
		if (res!=null){
			return res;
		}
		else {
			throw new GameException("No such player",id + " is not registered");
		}
	}


	public void registerPlayer(IPlayer IPlayer){
		players.put(IPlayer.getID(), IPlayer);
	}

	private void goOffline(IPlayer player){
		//Remove the player from their nearby players' lists
		for (IPlayer op: player.getPlayersNearby()){
			op.getPlayersNearby().remove(player);
			//update the other player's nearby list
			mediator.updateNearbyPlayers(op);
		}

		//someone who's offline cannot see anyone
		player.getPlayersNearby().clear();
		//update the player's nearby list
		if (mediator!=null) {
			mediator.updateNearbyPlayers(player);
		}

		updateLootboxes(player);
	}

	public void playerChangePos(final String id, final GeoPos newPos) throws GameException {
		IPlayer player = getPlayerById(id);
		player.updatePos(newPos);

		if (!player.isOnline()){
			goOffline(player);
			mediator.updatePlayer(player);
			return;
		}

		for (IPlayer oPlayer :players.values()){
			if (!oPlayer.isOnline()){
				continue;
			}
			if (player.equals(oPlayer)){
				continue;
			}

			boolean pSeesOp = GeoDistance.getDistance(player.getGeoPos(), oPlayer.getGeoPos())<= player.getVision();
			boolean opSeesP = GeoDistance.getDistance(player.getGeoPos(), oPlayer.getGeoPos())<= oPlayer.getVision();


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
			updateLootboxes(oPlayer);
		}

		mediator.updatePlayer(player);
		mediator.updateNearbyPlayers(player);


		///Check for lootboxes

		updateLootboxes(player);

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
		if (mediator!=null) {
			mediator.updateLootbox(player, visibleLootboxes);
		}
	}

	public void setPlayerAvatar(IPlayer player, String avatarId) {
		player.setAvatar(Avatar.valueOf(avatarId));
	}


	public void consumeLootboxByGeoPos(String playerId, GeoPos pos) throws GameException {

		IPlayer player=getPlayerById(playerId);

		for (ILootbox lootbox:lootboxes){
			if (lootbox.getGeoPos().equals(pos)){
				//player.consume(lootbox);
				lootbox.consume(player);

				lootboxes.remove(lootbox);

				if (mediator!=null) {
					mediator.playerChangePos(playerId, player.getGeoPos());
				}

				break;
			}
		}


	}


}
