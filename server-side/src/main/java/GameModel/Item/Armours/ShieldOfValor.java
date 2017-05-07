package GameModel.Item.Armours;

/**
 * Created by latiif on 3/22/17.
 */
 class ShieldOfValor implements IArmour {

 	private final Integer id= 1;
	private final String name="Shield of Valor";
	private final Integer value = 25;
	private final Integer cost = 100;


	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getValue() {
		return value;
	}

	public Integer getCost() {
		return cost;
	}

	public String getItemType() {
		return "armour";
	}
}
