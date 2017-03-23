package GameModel.Armours;

/**
 * Created by latiif on 3/22/17.
 */
 class ShieldOfValor implements Armour {

 	private final Integer id= 1;
	private final String name="Shield of Valor";
	private final Integer value = 25;
	private final Integer cost = 100;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public Integer getCost() {
		return cost;
	}
}
