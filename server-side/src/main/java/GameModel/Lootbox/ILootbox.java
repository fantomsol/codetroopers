package GameModel.Lootbox;

import GameModel.GameUtils.Exception;
import GameModel.GameUtils.GeoPos;
import GameModel.Player.IPlayer;

/**
 * Created by latiif on 5/7/17.
 */
public interface ILootbox {
	GeoPos getGeoPos();


	void consume(IPlayer player) throws Exception;

	Integer getGold();


	Integer getWeapon();
	Integer getArmour();


}
