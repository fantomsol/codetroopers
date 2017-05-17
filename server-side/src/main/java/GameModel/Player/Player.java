package GameModel.Player;


import GameModel.GameUtils.Exceptions.GameException;
import GameModel.GameUtils.GeoPos;
import GameModel.Player.Avatar.Avatar;
import GameModel.Player.Experience.Exp;
import GameModel.GameUtils.GeoDistance;
import GameModel.Item.Armours.IArmour;
import GameModel.Item.Armours.ArmoursDirectory;
import GameModel.Item.Armours.ArmoursFactory;
import GameModel.Item.Item;
import GameModel.Item.Weapons.Weapon;
import GameModel.Item.Weapons.IWeapon;
import GameModel.Item.Weapons.WeaponsDirectory;
import GameModel.Item.Weapons.WeaponsFactory;
import GameModel.Ranking.Rank;
import GameModel.Ranking.Ranks;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by latiif on 3/22/17.
 */
public class Player implements IPlayer {


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
	public void sellItem(Item item, Integer refund) throws GameException {
		if (!hasItem(item)){
			throw new GameException("Item not found", "Cannot sell an item you don't have");
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

	public void buyItem(Item item){
		if (item.getCost()>this.gold){
			return;
		}
		if (hasItem(item)){
			return;
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



	public Player(final String id) throws GameException {
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

	public Player(final String id, final GeoPos pos) throws GameException {
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
			this.rank=Rank.getRank(this.exp);
			(new RespawnCooldown(10, this)).start();
		}
	}



	public void attackOtherPlayer(final IPlayer otherPlayer) {
		if (!getIsAlive()) {
			return;
		}

		if (!otherPlayer.getIsAlive()) {
			return;
		}

		if (GeoDistance.getDistance(otherPlayer.getGeoPos(), this.geoPos) > this.weaponEquipped.getRange()) {
			return;
		}

		Integer damage = this.weaponEquipped.fireWeapon();
		if (damage == 0) {
			return;
		}


		otherPlayer.getAttacked(damage);
		if (!otherPlayer.getIsAlive()) {
			score.increaseKills();

			if (otherPlayer.getWeaponEquipped().getId().intValue() != WeaponsDirectory.WHITEFLAG) {
				{
					this.setExp(Exp.getExpOnKill(this.getExp(), otherPlayer.getExp()));
					otherPlayer.setExp(Exp.getExpOnKilled(otherPlayer.getExp()));
					this.rank = Rank.getRank(this.exp);
				}

				System.out.println(this.rank + "\n" + otherPlayer.getRank());
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

	public void goOffline(){
		if (!isOnline()){
			return;
		}

		if (getCanGoOffline()){
			onlineStatus=false;
			setCanGoOffline(false);
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
