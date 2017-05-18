package GameModel.Lootbox;

import GameModel.GameUtils.Exceptions.EmptyLootbox;
import GameModel.GameUtils.Exceptions.FactoryException;
import GameModel.GameUtils.Exceptions.GameException;
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

	public void consume(IPlayer player) throws EmptyLootbox,FactoryException {
		player.grantGold(getGold());
		if (getWeapon()==null || getWeapon()==0){
			throw new EmptyLootbox("This lootbox has no weapons");
		}
		else {
			player.grantWeapon(WeaponsFactory.createWeapon(getWeapon()));
		}


		if (getArmour()==null || getArmour()==0){
			throw new EmptyLootbox("This lootbox has no armours");
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
