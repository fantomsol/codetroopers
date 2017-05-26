package com.cth.codetroopers.pixelwars.serverside.Lootbox;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.EmptyLootbox;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.FactoryException;
import com.cth.codetroopers.pixelwars.serverside.Item.Armours.ArmoursFactory;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.WeaponsFactory;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;

/**
 * Created by latiif on 5/7/17.
 */
public class Lootbox implements ILootbox {

	private final GeoPos mGeoPos;
	private final Integer gold;
	private final Integer mWeapon;
	private final Integer mArmour;



	public Lootbox(GeoPos geoPos, Integer gold, Integer weapon,Integer armour){
		mGeoPos=geoPos;
		this.gold=gold;
		this.mWeapon=weapon;
		this.mArmour=armour;
	}


	public GeoPos getGeoPos() {
		return mGeoPos;
	}

	public void consume(IPlayer player) throws EmptyLootbox,FactoryException {
		player.grantGold(getGold());
		boolean noWeapon=false,noArmour=false;

		if (getWeapon()==null || getWeapon()==0){
			noWeapon=true;
		}
		else {
			player.grantWeapon(WeaponsFactory.createWeapon(getWeapon()));
		}


		if (getArmour()==null || getArmour()==0){
			noArmour=true;
		}
		else {
			player.grantArmour(ArmoursFactory.createArmour(getArmour()));
		}

		if (noArmour && noWeapon){
			throw new EmptyLootbox("No items to be looted in here");
		}

	}

	public Integer getGold() {
		return gold;
	}

	public Integer getWeapon() {
		return mWeapon;
	}

	public Integer getArmour() {
		return mArmour;
	}
}
