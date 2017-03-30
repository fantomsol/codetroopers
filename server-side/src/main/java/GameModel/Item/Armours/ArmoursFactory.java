package GameModel.Item.Armours;

/**
 * Created by latiif on 3/22/17.
 */
public class ArmoursFactory {

	private ArmoursFactory(){}



	public static Armour createArmour(Integer id){

		if (id==1){
			return new ShieldOfValor();
		}


		throw new IllegalArgumentException("Armours with id:" + id+" cannot be found");
	}

}
