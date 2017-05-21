package com.cth.codetroopers.pixelwars.serverside.Item.Weapons;

/**
 * Created by Hugo on 2017-04-25.
 */
public class Sniper extends Weapon{

    public Integer getId() {
        return 2;
    }

    public String getName() {
        return "Sniper Rifle";
    }

    public Integer getDamage() {
        return 10;
    }

    public Integer getRange() {
        return 200;
    }

    public Integer getCooldown() {
        return 3;
    }

    public Integer getCost() {
        return 200;
    }
}
