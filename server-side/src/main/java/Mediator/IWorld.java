package Mediator;

import GameModel.GameUtils.Exception;
import GameModel.GameUtils.GeoPos;
import GameModel.Lootbox.ILootbox;
import GameModel.Player.IPlayer;
import GameModel.Shop.IShop;
import Mediator.ServerModelMediator;

/**
 * Created by latiif on 5/12/17.
 */
public interface IWorld {
	void createNewPlayer(String name) throws Exception;

	void setMediator(ServerModelMediator mediator);

	void changeWeapon(String playerId, Integer weaponID) throws Exception;

	IShop getShop();

	void addLootbox(ILootbox lootbox);

	IPlayer getPlayer(IPlayer p);

	void performAttack(String attackerId, String attackeeId) throws Exception;

	IPlayer getPlayerById(String id) throws Exception;

	void registerPlayer(IPlayer IPlayer);

	void playerChangePos(String id, GeoPos newPos) throws Exception;

	void updateLootboxes(IPlayer player);

	void setPlayerAvatar(IPlayer player, String avatarId);

	void consumeLootboxByGeoPos(String playerId, GeoPos pos) throws Exception;
}
