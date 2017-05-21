package com.cth.codetroopers.pixelwars.serverside.WorldPackage;

import Mediator.ServerModelMediator;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GameException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Lootbox.ILootbox;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Shop.IShop;

/**
 * Created by latiif on 5/12/17.
 */
public interface IWorld {
	void createNewPlayer(String name) throws GameException;

	void setMediator(ServerModelMediator mediator);

	void changeWeapon(String playerId, Integer weaponID) throws GameException;

	IShop getShop();

	void addLootbox(ILootbox lootbox);

	IPlayer getPlayer(IPlayer p);

	void performAttack(String attackerId, String attackeeId) throws GameException;

	IPlayer getPlayerById(String id) throws GameException;

	void registerPlayer(IPlayer IPlayer);

	void playerChangePos(String id, GeoPos newPos) throws GameException;

	void updateLootboxes(IPlayer player);

	void setPlayerAvatar(IPlayer player, String avatarId);

	void consumeLootboxByGeoPos(String playerId, GeoPos pos) throws GameException;
}
