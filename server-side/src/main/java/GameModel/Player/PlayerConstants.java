package GameModel.Player;

import java.util.function.DoubleToLongFunction;

/**
 * Created by latiif on 4/1/17.
 */
public class PlayerConstants {

	final static Double MAX_HEALTH = new Double(100);
	final static Integer START_GOLD = new Integer(1000);

	//Vision in meters
	final static Integer START_VISION = new Integer(10);


	//Offline cooldown in seconds
	final static Integer START_COOLDOWN = new Integer(2);


	final static Double damageCaluculation(final Integer damage, final Integer armour){
		return new Double(damage*100)/(100+armour);
	}

}
