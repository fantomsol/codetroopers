package GameModel.Item.Armours;

/**
 * Created by latiif on 3/22/17.
 */
public class ArmoursFactory {

	private ArmoursFactory(){}



	public static Armour createArmour(Integer id){

		if (id==ArmoursDirectory.SHIELD_OF_VALOR){
			return new ShieldOfValor();
		}

		if(id==ArmoursDirectory.RIGHTEOUS_GLORY){
			return new RighteousGlory();
		}


		throw new IllegalArgumentException("Armours with id:" + id+" cannot be found");
	}

}
