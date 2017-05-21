package Mocks;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.*;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Item.Armours.IArmour;
import com.cth.codetroopers.pixelwars.serverside.Item.Item;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.IWeapon;
import com.cth.codetroopers.pixelwars.serverside.Player.Avatar.Avatar;
import com.cth.codetroopers.pixelwars.serverside.Player.IPlayer;
import com.cth.codetroopers.pixelwars.serverside.Ranking.Ranks;

import java.util.List;

/**
 * Created by latiif on 5/18/17.
 */
public class PlayerMock implements IPlayer{
	public void setOfflineCooldownStops(Long time) {

	}

	public void switchWeapon(Integer weaponID) {

	}

	private int gold;
	public void grantGold(Integer amount) {
		gold+=amount;
	}

	public void sellItem(Item item, Integer refund) throws NoItemException {
		gold+=refund;
	}

	public void buyItem(Item item) throws InsufficientException, DuplicateItemException {
		if (gold-item.getCost()<0){
			throw new InsufficientException("NOT ENOUGH");
		}


		gold-=item.getCost();
	}

	public Double getHp() {
		return null;
	}

	public Ranks getRank() {
		return null;
	}

	public Boolean getIsAlive() {
		return null;
	}

	public Integer getArmour() {
		return null;
	}

	public void updatePos(GeoPos newPos) {

	}

	public void getAttacked(Integer damage) {

	}

	public void attackOtherPlayer(IPlayer otherPlayer) throws CombatException {

	}

	public void goOnline() {

	}

	public void goOffline() throws CooldownException {

	}

	public Boolean isOnline() {
		return null;
	}

	public Integer getOfflineCooldown() {
		return null;
	}

	public void grantWeapon(IWeapon weapon) {

	}

	public void grantArmour(IArmour armour) {

	}

	public void setCanGoOffline(Boolean value) {

	}

	public IWeapon getWeaponEquipped() {
		return null;
	}

	public Boolean getCanGoOffline() {
		return null;
	}

	public String getID() {
		return null;
	}

	public Integer getExp() {
		return null;
	}

	public GeoPos getGeoPos() {
		return null;
	}

	public Integer getVision() {
		return null;
	}

	public Integer getGold() {
		return gold;
	}

	public Avatar getAvatar() {
		return null;
	}

	public void setAvatar(Avatar a) {

	}

	public void setHp(Double hp) {

	}

	public void setExp(Integer exp) {

	}

	public void setIsAlive(boolean life) {

	}

	public List<IPlayer> getPlayersNearby() {
		return null;
	}

	public void addNearbyPlayer(IPlayer IPlayer) {

	}

	public void removeNearbyPlayer(IPlayer IPlayer) {

	}
}
