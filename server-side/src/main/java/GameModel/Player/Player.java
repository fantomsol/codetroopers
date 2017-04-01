package GameModel.Player;


import GameModel.GameUtils.RadarCooldown;
import GameModel.Item.Armours.Armour;
import GameModel.Item.Armours.ArmoursDirectory;
import GameModel.Item.Armours.ArmoursFactory;
import GameModel.Item.Weapons.WeaponInterface;
import GameModel.Item.Weapons.WeaponsDirectory;
import GameModel.Item.Weapons.WeaponsFactory;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by latiif on 3/22/17.
 */
public class Player {


	private Boolean onlineStatus=false;
	private Boolean canGoOffline=false;

	private GeoPos geoPos;
	private Double hp;
	private Integer armour;
	private Integer xp;
	private Integer gold;
	private Integer vision=PlayerConstants.START_VISION;
	private Integer offlineCooldown=PlayerConstants.START_VISION;
	private List<WeaponInterface> weapons;
	private List<Armour> armours;
	private Boolean isAlive;


	public WeaponInterface weaponInHand;

	private final String id;



	private void updateArmourValue(){
		armour=0;
		for (Armour a:armours){
			armour+=a.getValue();
		}
	}



	public Player(final String id) {
		this.id=id;

		hp=PlayerConstants.MAX_HEALTH;

		gold = PlayerConstants.START_GOLD;

		this.isAlive=Boolean.TRUE;


		armours= new ArrayList<>();
		weapons= new ArrayList<>();

		armours.add(ArmoursFactory.createArmour(ArmoursDirectory.SHIELD_OF_VALOR));
		updateArmourValue();

		weapons.add(WeaponsFactory.createWeapon(WeaponsDirectory.PISTOL));
		weaponInHand=weapons.get(0);


	}

	public Player(final String id, final GeoPos pos){
		this(id);
		this.geoPos= pos;
	}

	public Double getHp(){
		return new Double(this.hp);
	}

	public Boolean getIsAlive(){
		return new Boolean(isAlive);
	}

	public void updatePos(final GeoPos newPos){
		geoPos=newPos;
	}


	public void getAttacked(final Integer damage){
		Double actualDamage= PlayerConstants.damageCaluculation(damage,this.armour);
		hp-=actualDamage;

		if (hp<=0){
			isAlive=false;
		}
	}

	public void attackOtherPlayer(final Player otherPlayer){
		if (!getIsAlive()){
			return;
		}

		if (!otherPlayer.getIsAlive()){
			return;
		}
		Integer damage= this.weaponInHand.fireWeapon();
		if (damage==0){
			return;
		}

		otherPlayer.getAttacked(damage);
		if (!otherPlayer.isAlive){
		}
	}

	@Override
	public String toString(){

		StringBuilder sb= new StringBuilder();

		sb.append(this.id+" ").append(this.hp+" ").append(this.getIsAlive());

		return sb.toString();
	}


	public void goOnline(){
		if (isOnline()){
			return;
		}
		onlineStatus=true;
		new RadarCooldown(this).start();
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
		return new Boolean(onlineStatus);
	}

	public Integer getOfflineCooldown(){
		return this.offlineCooldown;
	}

	public void setCanGoOffline(Boolean value){
		this.canGoOffline=value;
	}

	public Boolean getCanGoOffline(){
		return new Boolean(canGoOffline);
	}

	public String getID(){
		return this.id;
	}

	public GeoPos getGeoPos(){
		return this.geoPos;
	}

	public Integer getVision(){
		return this.vision;
	}
}
