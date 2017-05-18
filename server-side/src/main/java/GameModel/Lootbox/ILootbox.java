package GameModel.Lootbox;

import GameModel.GameUtils.Exceptions.EmptyLootbox;
import GameModel.GameUtils.Exceptions.FactoryException;
import GameModel.GameUtils.Exceptions.GameException;
import GameModel.GameUtils.GeoPos;
import GameModel.Player.IPlayer;

/**
 * Created by latiif on 5/7/17.
 */
public interface ILootbox {
	GeoPos getGeoPos();


	void consume(IPlayer player) throws FactoryException,EmptyLootbox;

	Integer getGold();


	Integer getWeapon();
	Integer getArmour();


}
