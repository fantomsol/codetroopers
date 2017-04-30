package GameModel.Item.Weapons;

/**
 * Created by latiif on 3/22/17.
 */
public class WeaponsFactory {

	public static Weapon createWeapon(Integer id){
		if (id==WeaponsDirectory.PISTOL){
			return new Pistol();
		}

		if (id==WeaponsDirectory.SNIPER){
			return new Sniper();
		}

		if (id==WeaponsDirectory.ASSAULT_RIFLE){
			return new AssaultRifle();
		}

		throw new IllegalArgumentException("Weapons with id:" + id+" cannot be found");
	}

}
