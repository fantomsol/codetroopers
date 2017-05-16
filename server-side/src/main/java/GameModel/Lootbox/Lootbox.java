package GameModel.Lootbox;

import GameModel.GameUtils.Exception;
import GameModel.Item.Armours.ArmoursFactory;
import GameModel.Item.Weapons.WeaponsFactory;
import GameModel.GameUtils.GeoPos;
import GameModel.Player.IPlayer;

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

	public void consume(IPlayer player) throws Exception {
		player.grantGold(getGold());
		if (getWeapon()==null || getWeapon()==0){
		}
		else {
			player.grantWeapon(WeaponsFactory.createWeapon(getWeapon()));
		}


		if (getArmour()==null || getArmour()==0){
		}
		else {
			player.grantArmour(ArmoursFactory.createArmour(getArmour()));
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
