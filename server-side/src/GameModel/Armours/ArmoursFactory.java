package GameModel.Armours;

/**
 * Created by latiif on 3/22/17.
 */
public class ArmoursFactory {

	private ArmoursFactory(){}



	public static Armour createArmour(Integer id){

		if (id==1){
			return new ShieldOfValor();
		}


		throw new IllegalArgumentException("Armour with id:" + id+" cannot be found");
	}

}
