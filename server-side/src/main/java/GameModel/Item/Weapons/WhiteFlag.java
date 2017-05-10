package GameModel.Item.Weapons;

/**
 * Created by lumo on 09/05/17.
 */
public class WhiteFlag extends Weapon {

    public Integer getId() {
        return 5;
    }

    public String getName() {
        return "White Flag";
    }

    public Integer getCost() {
        return 0;
    }

    public String getItemType() {
        return "Weapon";
    }

    public Integer fireWeapon() {
        return 0;
    }

    public Integer getDamage() {
        return 0;
    }

    public Integer getRange() {
        return 0;
    }

    public Integer getCooldown() {
        return 0;
    }

    public Boolean getIsOnCooldown() {
        return false;
    }

    public void setIsOnCooldown(Boolean value) {

    }
}
