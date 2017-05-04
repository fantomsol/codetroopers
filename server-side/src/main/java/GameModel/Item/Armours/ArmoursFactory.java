package GameModel.Item.Armours;

import java.util.List;

/**
 * Created by latiif on 3/22/17.
 */
public class ArmoursFactory {


	private ArmoursFactory(){

	}




	//Make sure to keep up to date
	public static Armour createArmour(Integer id){

		if (id==ArmoursDirectory.SHIELD_OF_VALOR){
			return new ShieldOfValor();
		}

		if(id==ArmoursDirectory.RIGHTEOUS_GLORY){
			return new RighteousGlory();
		}
		if(id==ArmoursDirectory.BODY_ARMOUR){
			return new BodyArmour();
		}
		if(id==ArmoursDirectory.KEVLAR){
			return new Kevlar();
		}


		throw new IllegalArgumentException("Item with id:" + id+" cannot be found");
	}

}
