package GameModel.Item.Weapons;

/**
 * Created by latiif on 3/22/17.
 */
public class WeaponsFactory {

	public static Weapon createWeapon(Integer id){
		if (id==1){
			return new Pistol();
		}


		throw new IllegalArgumentException("Weapons with id:" + id+" cannot be found");
	}

}
