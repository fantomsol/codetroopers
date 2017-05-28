package com.cth.codetroopers.pixelwars.serverside.Player;


import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.*;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Player.Avatar.Avatar;
import com.cth.codetroopers.pixelwars.serverside.Player.Experience.Exp;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoDistance;
import com.cth.codetroopers.pixelwars.serverside.Item.Armours.IArmour;
import com.cth.codetroopers.pixelwars.serverside.Item.Armours.ArmoursDirectory;
import com.cth.codetroopers.pixelwars.serverside.Item.Armours.ArmoursFactory;
import com.cth.codetroopers.pixelwars.serverside.Item.Item;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.Weapon;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.IWeapon;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.WeaponsDirectory;
import com.cth.codetroopers.pixelwars.serverside.Item.Weapons.WeaponsFactory;
import com.cth.codetroopers.pixelwars.serverside.Ranking.Rank;
import com.cth.codetroopers.pixelwars.serverside.Ranking.Ranks;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by latiif and Hugo on 3/22/17.
 */
public class Player implements IPlayer {
	public Integer getArmour() {
		return armour;
	}

	private Boolean onlineStatus=false;
	private Boolean canGoOffline=false;

	private Avatar avatar = Avatar.JIM;

	@JsonProperty
	private Long offlineCooldownStops=Long.valueOf(0);

	public void setOfflineCooldownStops(Long time) {
		this.offlineCooldownStops = time;
	}

	private GeoPos geoPos;
	private Double hp;
	private Integer armour;
	public Score score;

	private Integer exp;
	private Integer gold;
	private Integer vision=PlayerConstants.START_VISION;
	private Integer offlineCooldown=PlayerConstants.START_COOLDOWN;
	private Ranks rank = Ranks.PRIVATE;

	@JsonProperty
	private List<IWeapon> weapons = new ArrayList<IWeapon>();

	@JsonProperty
	private List<IArmour> armours= new ArrayList<IArmour>();

	private Boolean isAlive;


	public void switchWeapon(Integer weaponID){
		for (IWeapon weapon:weapons){
			if (weapon.getId().intValue()==weaponID.intValue()){
				weaponEquipped=weapon;
				return;
			}
		}
	}

	public void grantGold(Integer amount){
		this.gold+=amount;
	}


	private boolean hasItem(Item item){

		for(IWeapon weapon: weapons){
			if (weapon.getName().equals(item.getName())){
				return true;
			}
		}


		for (IArmour armour:armours){
			if (armour.getName().equals(item.getName())){
				return true;
			}
		}


		return false;
	}
	public void sellItem(Item item, Integer refund) throws NoItemException {
		if (!hasItem(item)){
			throw new NoItemException("Cannot sell an item you don't have");
		}

		grantGold(refund);

		if (item instanceof  Weapon){
			removeFromWeapons((Weapon) item);
		}
		if (item instanceof IArmour){
			removeFromArmours((IArmour) item);
		}
	}

	private void removeFromArmours(IArmour armour){
		for (IArmour a:armours){
			if (a.getName().equals(armour.getName())){
				armours.remove(a);
				break;
			}
		}
	}

	private void removeFromWeapons(Weapon weapon){

		boolean shouldSwitch=false;
		if (weaponEquipped.getName().equals(weapon.getName())){
			shouldSwitch=true;
		}

		for (IWeapon w:weapons){
			if (w.getName().equals(weapon.getName())){
				weapons.remove(w);
				break;
			}
		}

		if (shouldSwitch && weapons.size()>0){
			weaponEquipped=weapons.get(0);
		}

	}

	public void buyItem(Item item) throws InsufficientException, DuplicateItemException {
		if (item.getCost()>this.gold){
			throw new InsufficientException("Life is hard, deal with it!");
		}
		if (hasItem(item)){
			throw new DuplicateItemException("You already have the item");
		}



		this.gold-=item.getCost();
		if (item instanceof Weapon){//We know its a weapon
			Weapon weaponBought = (Weapon)item;
			weapons.add(weaponBought);
		}
		else if (item instanceof IArmour){
			IArmour armourBought = (IArmour) item;
			armours.add(armourBought);
			updateArmourValue();
		}

	}
	public IWeapon weaponEquipped;

	private final String id;



	private void updateArmourValue(){
		armour=0;
		for (IArmour a:armours){
			armour+=a.getValue();
		}
	}



	public Player(final String id) throws FactoryException {
		this.id=id;

		hp=PlayerConstants.MAX_HEALTH;

		gold = PlayerConstants.START_GOLD;

		exp=PlayerConstants.START_EXP;

		this.isAlive=Boolean.TRUE;

		score= new Score(0,0);


		armours= new ArrayList<IArmour>();
		//weapons= new ArrayList<Weapon>();

		armours.add(ArmoursFactory.createArmour(ArmoursDirectory.SHIELD_OF_VALOR));
		updateArmourValue();

		weapons.add(WeaponsFactory.createWeapon(WeaponsDirectory.PISTOL));
		weaponEquipped =weapons.get(0);


	}

	public Player(final String id, final GeoPos pos) throws GeographicalException,FactoryException {
		this(id);
		this.geoPos= pos;
	}

	public Double getHp(){
		return Double.valueOf(hp);
	}

	public Ranks getRank() {
		return this.rank;
	}

	public Boolean getIsAlive(){
		return isAlive;
	}

	public void updatePos(final GeoPos newPos){
		geoPos=newPos;
	}


	public void getAttacked(final Integer damage){
		Double actualDamage= PlayerConstants.damageCaluculation(damage,this.armour);
		hp-=actualDamage;

		if (hp<=0){
			isAlive=false;
			score.increaseDeaths();
			this.rank= Rank.getRank(this.exp);
			(new RespawnCooldown(PlayerConstants.RESPAWN_COOLDWON, this)).start();
		}
	}



	public void attackOtherPlayer(final IPlayer otherPlayer) throws CombatException,CooldownException {
		if (!getIsAlive()) {
			throw new CombatException("You cannot attack when you're dead");
		}

		if (!otherPlayer.getIsAlive()) {
			throw new CombatException("You cannot attack the dead");
		}

		if (GeoDistance.getDistance(otherPlayer.getGeoPos(), this.geoPos) > this.weaponEquipped.getRange()) {
			throw new CombatException("They are out of your reach");
		}

		Integer damage = this.weaponEquipped.fireWeapon();


		otherPlayer.getAttacked(damage);
		if (!otherPlayer.getIsAlive()) {
			score.increaseKills();

			if (otherPlayer.getWeaponEquipped().getId().intValue() != WeaponsDirectory.WHITEFLAG) {
				{
					this.setExp(Exp.getExpOnKill(this.getExp(), otherPlayer.getExp()));
					otherPlayer.setExp(Exp.getExpOnKilled(otherPlayer.getExp()));
					this.rank = Rank.getRank(this.exp);
				}
			}
		}
	}

	@Override
	public String toString(){

		StringBuilder sb= new StringBuilder();

		sb.append(this.id+" ").append(this.hp+" ").append(this.getIsAlive()).append("@").append(geoPos).append(" "+score);

		sb.append("sees: ");

		for(IPlayer p: playersNearby){
			sb.append(p.getID()).append('\n');
		}


		return sb.toString();
	}


	public void goOnline(){
		if (isOnline()){
			return;
		}
		onlineStatus=true;
		new RadarCooldown(PlayerConstants.START_COOLDOWN,this).start();
	}

	public void goOffline() throws CooldownException {
		if (!isOnline()){
			return;
		}

		if (getCanGoOffline()){
			onlineStatus=false;
			setCanGoOffline(false);
		}
		else {
			throw new CooldownException("You cannot go offline yet");
		}



	}

	public Boolean isOnline(){
		return onlineStatus;
	}

	public Integer getOfflineCooldown(){
		return this.offlineCooldown;
	}

	public void grantWeapon(IWeapon weapon) {
		weapons.add(weapon);
	}

	public void grantArmour(IArmour armour) {
		armours.add(armour);
		updateArmourValue();
	}

	public void setCanGoOffline(Boolean value){
		this.canGoOffline=value;
	}

	public IWeapon getWeaponEquipped() {
		return weaponEquipped;
	}

	public Boolean getCanGoOffline(){
		return canGoOffline;
	}

	public String getID(){
		return this.id;
	}

	public Integer getExp() {
		return exp;
	}

	public GeoPos getGeoPos(){
		return this.geoPos;
	}

	public Integer getVision(){
		return this.vision;
	}

	public Integer getGold(){
		return gold;
	}


	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar a) {
		avatar = a;
	}

	public void setHp(Double hp) {
		this.hp = hp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
		this.rank=Rank.getRank(this.exp);
	}

	@JsonIgnore
	private transient List<IPlayer> playersNearby= new ArrayList<IPlayer>();

	public List<IPlayer> getPlayersNearby(){
		return (this.playersNearby);
	}

	public void addNearbyPlayer(IPlayer IPlayer){
		this.playersNearby.add(IPlayer);
	}


	public void removeNearbyPlayer(IPlayer IPlayer){
		this.playersNearby.remove(IPlayer);
	}

	public void setIsAlive (boolean life) {
		this.isAlive = life;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Player)) {
			return false;
		}
		Player other = (Player) o;
		return this.id.equals(other.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}


}
