package GameModel.Item.Weapons;
import GameModel.GameUtils.Exceptions.GameException;

/**
 * Created by latiif on 3/22/17.
 */
public class WeaponsFactory {

	public static Weapon createWeapon(Integer id) throws GameException {
		if (id.intValue()==WeaponsDirectory.PISTOL){
			return new Pistol();
		}

		if (id.intValue()==WeaponsDirectory.SNIPER){
			return new Sniper();
		}

		if (id.intValue()==WeaponsDirectory.ASSAULT_RIFLE){
			return new AssaultRifle();
		}
		if (id.intValue()==WeaponsDirectory.SHOTGUN){
			return new Shotgun();
		}

		if (id.intValue()==WeaponsDirectory.WHITEFLAG){
			return new WhiteFlag();
		}

		throw new GameException("Invalid ID for a weapon","Weapons with id:" + id+" cannot be found");
	}

}
