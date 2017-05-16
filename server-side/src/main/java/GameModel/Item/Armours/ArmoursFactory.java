package GameModel.Item.Armours;

import GameModel.GameUtils.Exception;

/**
 * Created by latiif on 3/22/17.
 */
public class ArmoursFactory {


	private ArmoursFactory(){

	}




	//Make sure to keep up to date
	public static IArmour createArmour(Integer id) throws Exception {

		if (id.intValue()==ArmoursDirectory.SHIELD_OF_VALOR){
			return new ShieldOfValor();
		}

		if(id.intValue()==ArmoursDirectory.RIGHTEOUS_GLORY){
			return new RighteousGlory();
		}
		if(id.intValue()==ArmoursDirectory.BODY_ARMOUR){
			return new BodyArmour();
		}
		if(id.intValue()==ArmoursDirectory.KEVLAR){
			return new Kevlar();
		}
		if(id.intValue()==ArmoursDirectory.HELMET){
			return new Helmet();
		}


		throw new Exception("Invalid ID for an armour","Item with id:" + id+" cannot be found");
	}

}
