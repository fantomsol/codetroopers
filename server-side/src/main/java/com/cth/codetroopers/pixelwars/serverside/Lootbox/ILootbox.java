package com.cth.codetroopers.pixelwars.serverside.Lootbox;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.EmptyLootbox;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.FactoryException;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;

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
