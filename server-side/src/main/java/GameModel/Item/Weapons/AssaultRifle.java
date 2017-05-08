package GameModel.Item.Weapons;

/**
 * Created by Hugo on 2017-04-25.
 */
public class AssaultRifle extends Weapon {

    public Integer getId() {
        return 3;
    }

    public String getName() {
        return "Assault Rifle";
    }

    public Integer getDamage() {
        return 5;
    }

    public Integer getRange() {
        return 100;
    }

    public Integer getCooldown() {
        return 1;
    }

    public Integer getCost() {
        return 150;
    }

}
