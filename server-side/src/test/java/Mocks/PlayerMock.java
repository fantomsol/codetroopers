package Mocks;

import GameModel.GameUtils.Exceptions.GameException;
import GameModel.GameUtils.GeoPos;
import GameModel.Item.Armours.IArmour;
import GameModel.Item.Item;
import GameModel.Item.Weapons.IWeapon;
import GameModel.Player.Avatar.Avatar;
import GameModel.Player.IPlayer;
import GameModel.Ranking.Ranks;

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

	public void sellItem(Item item, Integer refund) throws GameException {

	}

	public void buyItem(Item item) throws GameException {

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

	public void updatePos(GeoPos newPos) {

	}

	public void getAttacked(Integer damage) {

	}

	public void attackOtherPlayer(IPlayer otherPlayer) throws GameException {

	}

	public void goOnline() {

	}

	public void goOffline() throws GameException {

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
