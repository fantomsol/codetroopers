package GameModel.Item.Armours;

/**
 * Created by Hugo on 2017-05-04.
 */
public class Kevlar implements Armour {
    private final Integer id= 4;
    private final String name="Kevlar";
    private final Integer value = 250;
    private final Integer cost = 600;


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
