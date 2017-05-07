package GameModel.Lootbox;

import GameModel.Player.GeoPos;
import GameModel.Player.IPlayer;

/**
 * Created by latiif on 5/7/17.
 */
public interface ILootbox {
	GeoPos getGeoPos();


	void consume(IPlayer player);

	Integer getGold();


	Integer getWeapon();
	Integer getArmour();


}
