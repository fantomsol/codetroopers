package GameModel.Player;

import GameModel.Armours.Armour;
import GameModel.Armours.ArmoursDirectory;
import GameModel.Armours.ArmoursFactory;
import GameModel.Weapons.Weapon;
import GameModel.Weapons.WeaponsDirectory;
import GameModel.Weapons.WeaponsFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by latiif on 3/22/17.
 */
public class Player {
	private GeoPos geoPos;
	private Double hp;
	private Integer armour;
	private Integer xp;
	private Integer gold;
	private Integer vision;
	private List<Weapon> weapons;
	private List<Armour> armours;


	private Weapon weaponInHand;

	private final String id;



	private void updateArmourValue(){
		armour=0;
		for (Armour a:armours){
			armour+=a.getValue();
		}
	}



	public Player(final String id) {
		this.id=id;

		hp=new Double(100);
		gold = new Integer(1000);

		armours= new ArrayList<>();

		armours.add(ArmoursFactory.createArmour(ArmoursDirectory.SHIELD_OF_VALOR));
		updateArmourValue();

		weapons.add(WeaponsFactory.createWeapon(WeaponsDirectory.PISTOL));
		weaponInHand=weapons.get(0);

	}

	public Player(final String id, final GeoPos pos){
		this(id);
		this.geoPos= pos;
	}

	public void updatePos(final GeoPos newPos){
		geoPos=newPos;
	}






}
