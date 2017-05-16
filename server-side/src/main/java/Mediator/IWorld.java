package Mediator;

import GameModel.GameUtils.GeoPos;
import GameModel.Lootbox.ILootbox;
import GameModel.Player.IPlayer;
import GameModel.Shop.IShop;
import Mediator.ServerModelMediator;

/**
 * Created by latiif on 5/12/17.
 */
public interface IWorld {
	void createNewPlayer(String name);

	void setMediator(ServerModelMediator mediator);

	void changeWeapon(String playerId, Integer weaponID);

	IShop getShop();

	void addLootbox(ILootbox lootbox);

	IPlayer getPlayer(IPlayer p);

	void performAttack(String attackerId, String attackeeId);

	IPlayer getPlayerById(String id);

	void registerPlayer(IPlayer IPlayer);

	void playerChangePos(String id, GeoPos newPos);

	void updateLootboxes(IPlayer player);

	void setPlayerAvatar(IPlayer player, String avatarId);

	void consumeLootboxByGeoPos(String playerId, GeoPos pos);
}
