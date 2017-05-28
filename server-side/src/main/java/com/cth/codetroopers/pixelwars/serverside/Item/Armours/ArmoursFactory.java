package com.cth.codetroopers.pixelwars.serverside.Item.Armours;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.FactoryException;

/**
 * Created by latiif and Hugo on 3/22/17.
 */
public class ArmoursFactory {


	private ArmoursFactory(){

	}




	//Make sure to keep up to date
	public static IArmour createArmour(Integer id) throws FactoryException {

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


		throw new FactoryException("Item with id:" + id+" cannot be found");
	}

}
