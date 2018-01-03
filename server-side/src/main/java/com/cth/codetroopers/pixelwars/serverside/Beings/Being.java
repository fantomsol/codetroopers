package com.cth.codetroopers.pixelwars.serverside.Beings;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Beings.Avatar.Avatar;

// Replaces past interface IPlayer
public abstract class Being {
	protected Avatar avatar = Avatar.SIBOAN;

	protected GeoPos geoPos;
	protected Boolean isAlive;
	protected String id;

	public Being(String id) {
		this.id = id;
	}


	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Being)) {
			return false;
		}
		Being other = (Being) o;
		return this.id.equals(other.id);
	}


	public abstract void getAttacked(Integer damage);

	public void updatePos(final GeoPos newPos) {
		geoPos = newPos;
	}

	public void setIsAlive(boolean life) {
		this.isAlive = life;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar a) {
		avatar = a;
	}

	public GeoPos getGeoPos() {
		return geoPos;
	}

	public String getID() {
		return id;
	}

	public Boolean getIsAlive() {
		return isAlive;
	}

}
/*

public interface IPlayer {
	void setOfflineCooldownStops(Long time);

	void switchWeapon(Integer weaponID);

	void grantGold(Integer amount);

	void sellItem(Item item, Integer refund) throws NoItemException;

	void buyItem(Item item) throws InsufficientException, DuplicateItemException;

	Double getHp();

	Ranks getRank();

	Boolean getIsAlive();

	Integer getArmour();


	void updatePos(GeoPos newPos);

	void getAttacked(Integer damage);

	void attackPlayer(Being otherPlayer) throws CooldownException, CombatException;

	void attackMonster(Monster monster) throws CooldownException, CombatException;

	void goOnline();

	void goOffline() throws CooldownException;

	Boolean isOnline();

	Integer getOfflineCooldown();

	void grantWeapon(IWeapon weapon);

	void grantArmour(IArmour armour);

	void setCanGoOffline(Boolean value);

	IWeapon getWeaponEquipped();

	Boolean getCanGoOffline();

	String getID();

	Integer getExp();

	Integer getVision();

	Integer getGold();

	Avatar getAvatar();

	GeoPos getGeoPos();

	void setAvatar(Avatar a);

	void setHp(Double hp);

	void setExp(Integer exp);

	void setIsAlive(boolean life);

	List<Being> getPlayersNearby();

	void addNearbyPlayer(Being IPlayer);

	void removeNearbyPlayer(Being IPlayer);
}

* */