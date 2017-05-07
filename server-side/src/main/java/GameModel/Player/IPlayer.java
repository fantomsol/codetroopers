package GameModel.Player;

import GameModel.Item.Armours.IArmour;
import GameModel.Item.Item;
import GameModel.Item.Weapons.IWeapon;
import GameModel.Lootbox.ILootbox;
import GameModel.Ranking.Ranks;

import java.util.List;

/**
 * Created by latiif on 5/6/17.
 */
public interface IPlayer {
	void setOfflineCooldownStops(Long time);

	void switchWeapon(Integer weaponID);

	void grantGold(Integer amount);

	void sellItem(Item item, Integer refund);

	void buyItem(Item item);

	Double getHp();

	Ranks getRank();

	Boolean getIsAlive();

	void updatePos(GeoPos newPos);

	void getAttacked(Integer damage);

	void attackOtherPlayer(IPlayer otherPlayer);

	void goOnline();

	void goOffline();

	Boolean isOnline();

	Integer getOfflineCooldown();

	void grantWeapon(IWeapon weapon);
	void grantArmour(IArmour armour);

	void setCanGoOffline(Boolean value);

	Boolean getCanGoOffline();

	String getID();

	Integer getExp();

	GeoPos getGeoPos();

	Integer getVision();

	Integer getGold();

	void setHp(Double hp);

	void setExp(Integer exp);

	List<IPlayer> getPlayersNearby();

	void addNearbyPlayer(IPlayer IPlayer);

	void consume(ILootbox lootbox);

	void removeNearbyPlayer(IPlayer IPlayer);
}
